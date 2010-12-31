package com.eutools.eueditor.launch.tabs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;

import com.eutools.eueditor.builder.EuphoriaNature;
import com.eutools.eueditor.launch.LaunchConfigurationProperty;

public class MainTab extends AbstractLaunchConfigurationTab {

	// Controls
	Text projectText = null;
	Text fileText = null;
	Text configText = null;
	
	Button projectButton = null;
	Button fileButton = null;
	Button configFileButton = null;
	
	ILaunchConfigurationWorkingCopy working;
	
	public MainTab() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Project:");
		projectText =  new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		projectText.setLayoutData(gd);
		projectButton = new Button(container, SWT.PUSH);
		projectButton.setText("Browse...");
		Listener listener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == projectButton){
					IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
					List<IResource> resources = new ArrayList<IResource>();
					if (projects != null && projects.length > 0){
						for (int i=0; i<projects.length; i++){
							IProject p = projects[i];
							if (p.isAccessible()){
								try {
									if (p.hasNature(EuphoriaNature.NATURE_ID)){
										resources.add((IResource)p);
									}
								} catch (CoreException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
					if (resources.size() > 0){
						IResource[] res = resources.toArray(new IResource[resources.size()]);
						ResourceListSelectionDialog d = new ResourceListSelectionDialog(getShell(), res);
						d.setBlockOnOpen(true);
						d.open();
						Object[] selections = d.getResult();
						if (selections != null && selections.length > 0){
							if (selections[0] instanceof IProject){
								IProject selection = (IProject)selections[0];
								projectText.setText(selection.getName());
								setDirty(true);
								updateLaunchConfigurationDialog();
							}
						}
					}
					else{
						System.err.println("No Euphoria projects found");
					}
				}
			}
		};
		projectButton.addListener(SWT.Selection, listener);
		Label fileLabel = new Label(container, SWT.NULL);
		fileLabel.setText("File: ");
		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		fileText.setLayoutData(gd);
		fileButton = new Button(container, SWT.PUSH);
		fileButton.setText("Browse...");
		
		Label configLabel = new Label(container, SWT.NULL);
		configLabel.setText("Config File: ");
		configText = new Text(container, SWT.BORDER | SWT.SINGLE);
		configText.setLayoutData(gd);
		configFileButton = new Button(container, SWT.PUSH);
		configFileButton.setText("Browse...");

		projectText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String projectName = projectText.getText();
				if (projectName == null || projectName.length() == 0){
					updateLaunchConfigurationDialog();
					return;
				}
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				if (project != null){
					if (project.exists()){
						if (working != null){
							working.setAttribute(LaunchConfigurationProperty.PROJECT_NAME, projectText.getText());
							setDirty(true);					
						}
					}
				}
				updateLaunchConfigurationDialog();				
			}
		});
		fileText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (working != null){
					System.out.println("working is valid");
					working.setAttribute(LaunchConfigurationProperty.FILE_NAME, fileText.getText());
				}
				else{
					System.err.println("Working is NULL");
				}
				setDirty(true);
				updateLaunchConfigurationDialog();
			}
		});
		
		configText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				System.out.println("Config text modified");
				if (working != null){
					System.out.println("Working is not null");
					working.setAttribute(LaunchConfigurationProperty.CONFIG_FILE, configText.getText());
				}
				else{
					System.err.println("Working is NULL");
				}
				setDirty(true);
				updateLaunchConfigurationDialog();
			}
		});
		setDirty(false);
		setControl(container);
	}

	@Override
	public String getName() {
		return "Main   ";
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			projectText.setText(configuration.getAttribute(LaunchConfigurationProperty.PROJECT_NAME, "Select a project..."));
			fileText.setText(configuration.getAttribute(LaunchConfigurationProperty.FILE_NAME, "Select a file..."));
			configText.setText(configuration.getAttribute(LaunchConfigurationProperty.CONFIG_FILE, ""));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isValid(ILaunchConfiguration launchConfig) {
		System.out.println("Validating configuration");
		try {
			String projectName = launchConfig.getAttribute(LaunchConfigurationProperty.PROJECT_NAME, "");
			String fileName = launchConfig.getAttribute(LaunchConfigurationProperty.FILE_NAME, "");
			String configFileName = launchConfig.getAttribute(LaunchConfigurationProperty.CONFIG_FILE, "");
			if (projectName.length() > 0){
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				if (project.exists()){
					if (fileName != null && fileName.length() > 0){
						IFile file = project.getFile(fileName);
						if (file.exists()){
							setErrorMessage(null);
							if (configFileName != null && configFileName.length() > 0){
								file = project.getFile(configFileName);
								if (file.exists()){
									setErrorMessage(null);
									System.out.println("Config valid, has config file");
									return true;
								}
								else{
									setErrorMessage("Select an existing config file");
								}
							}
							System.out.println("Config valid, no config file");
							return true;
						}
						else{
							setErrorMessage("Select an existing file");
						}
					}
					else{
						setErrorMessage("Select an existing file");						
					}
				}
				else{
					setErrorMessage("Select an existing Euphoria project");
				}
			}
			else{
				setErrorMessage("Select an existing Euphoria project");
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		System.err.println("Config not valid");
		return false;
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		configuration.setAttribute(LaunchConfigurationProperty.PROJECT_NAME, projectText.getText());
		configuration.setAttribute(LaunchConfigurationProperty.FILE_NAME, fileText.getText());
		configuration.setAttribute(LaunchConfigurationProperty.CONFIG_FILE, configText.getText());
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		working = configuration;
	}

}
