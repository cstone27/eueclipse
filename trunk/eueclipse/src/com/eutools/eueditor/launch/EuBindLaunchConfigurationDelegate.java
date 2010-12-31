package com.eutools.eueditor.launch;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;

public class EuBindLaunchConfigurationDelegate extends
		LaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		System.out.println("Launch Config Delegate");
		Runtime r = Runtime.getRuntime();
		String fileName = configuration.getAttribute(LaunchConfigurationProperty.FILE_NAME, "");
		String projectName = configuration.getAttribute(LaunchConfigurationProperty.PROJECT_NAME, "");
		String filePath = new String();
		
		if (fileName.length() == 0|| projectName.length() == 0){
			System.err.println("File name or project name is empty");
			return;
		}
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = workspace.getProject(projectName);
		if (project.exists()){
			IFile file = project.getFile(fileName);
			if (file.exists()){
				IPath workspacePath = workspace.getLocation();
				IPath fileFullPath = file.getFullPath();
				if (fileFullPath != null){
					filePath = String.format("%s%s", workspacePath.toOSString(), fileFullPath.toOSString());
				}
			}
		}
		try {
			if (filePath != null && filePath.length() > 0){
				System.out.println("Running file " + filePath);
				String runCmd = String.format("eui \"%s\"", filePath);
				System.out.println("Run Command: " + runCmd);
				Process p = r.exec(runCmd);
				DebugPlugin.newProcess(launch, p, "Euphoria Process");
			}
			else{
				System.err.println("Unable to find path for file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
