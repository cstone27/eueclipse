package com.eutools.eueditor.launch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;

public class EuBindLaunchConfigurationDelegate extends LaunchConfigurationDelegate {
	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		System.out.println("Launch Config Delegate");
		Runtime r = Runtime.getRuntime();
		String fileName = configuration.getAttribute(LaunchConfigurationProperty.FILE_NAME, "");
		String projectName = configuration.getAttribute(LaunchConfigurationProperty.PROJECT_NAME, "");
		String filePath = new String();
		String configFile = null;
		File workingDirectory = null;
		
		if (fileName.length() == 0|| projectName.length() == 0){
			System.err.println("File name or project name is empty");
			return;
		}
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = workspace.getProject(projectName);
		if (project.exists()){
			IFile file = project.getFile(fileName);
			IPath workspacePath = workspace.getLocation();
			if (file.exists()){
				IPath fileFullPath = file.getFullPath();
				if (fileFullPath != null){
					filePath = String.format("%s%s", workspacePath.toOSString(), fileFullPath.toOSString());
					workingDirectory = new File(workspacePath.toOSString() + project.getFullPath().toOSString());
				}
			}
			configFile = getConfigFile(configuration);
			if (configFile != null){
				IFile cFile = project.getFile(configFile);
				if (cFile.exists()){
					configFile = String.format("\"%s%s\"", workspacePath.toOSString(), cFile.getFullPath().toOSString());
				}
				else{
					System.err.println("Invalid config file \"" + configFile + "\"");
					configFile = null;
				}
			}
		}
		try {
			if (filePath != null && filePath.length() > 0){
				System.out.println("Running file " + filePath);
				String runCmd = "eui -BATCH ";
				if (configFile != null){
					runCmd += " -C " + configFile + " ";
				}
				runCmd += "\"" + filePath + "\"";
				@SuppressWarnings("unchecked")
				Map<String, String> launchEnv = configuration.getAttribute(ILaunchManager.ATTR_ENVIRONMENT_VARIABLES, (Map<String, String>)null);
				final Process p; 

				if (launchEnv != null && launchEnv.size() > 0){
					Map<String, String> curEnv = System.getenv();
					List<String> envList = new ArrayList<String>();
					if (configuration.getAttribute(ILaunchManager.ATTR_APPEND_ENVIRONMENT_VARIABLES, true)){
						Iterator<String> iter = curEnv.keySet().iterator();
						while (iter.hasNext()){
							String key = iter.next();
							envList.add(key + "=" + curEnv.get(key));
						}
					}
					Iterator<String> iter = launchEnv.keySet().iterator();
					while (iter.hasNext()){
						String key = iter.next();
						envList.add(key + "=" + launchEnv.get(key));
					}
					String[] env = new String[envList.size()];
					for(int i=0;i<envList.size();i++){
						env[i] = envList.get(i);
					}
					System.out.println("Run Command: " + runCmd);
					p = r.exec(runCmd, env, workingDirectory);
				}
				else{
					System.out.println("Run Command: " + runCmd);
					p = r.exec(runCmd, null, workingDirectory);
				}
				
				DebugPlugin.newProcess(launch, p, "Euphoria Process");
				DebugPlugin dp = DebugPlugin.getDefault();
				dp.addDebugEventListener(new IDebugEventSetListener() {
					
					@Override
					public void handleDebugEvents(DebugEvent[] events) {
						// TODO Auto-generated method stub
						if (events != null && events.length > 0){
							for (int i=0; i<events.length; i++){
								DebugEvent event = events[i];
								if (event.getKind() == DebugEvent.TERMINATE){
									int code = p.exitValue();
									System.out.println(String.format("Exit Code: %s", code));
									if (code != 0){
										// TODO Get ex.err file
									}
								}
							}
						}
					}
				});
			}
			else{
				System.err.println("Unable to find path for file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getConfigFile(ILaunchConfiguration configuration){
		if (configuration == null){
			return null;
		}
		try {
			String filename = configuration.getAttribute(LaunchConfigurationProperty.CONFIG_FILE, "");
			if (filename.equals("")){
				return null;
			}
			else{
				return filename;
			}
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
