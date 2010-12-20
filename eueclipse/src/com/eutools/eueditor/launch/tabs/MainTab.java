package com.eutools.eueditor.launch.tabs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.internal.resources.Resource;
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

public class MainTab extends AbstractLaunchConfigurationTab {

	Text projectText = null;
	Text fileText = null;
	Button projectButton = null;
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
				// TODO Auto-generated method stub
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
						d.open();
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
		projectText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (working != null){
					working.setAttribute("runProjectName", projectText.getText());
				}
				setDirty(true);
			}
		});
		fileText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (working != null){
					working.setAttribute("runFileName", fileText.getText());
				}
				setDirty(true);
			}
		});
		setDirty(false);
		setControl(container);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Main";
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		// TODO Auto-generated method stub
		try {
			projectText.setText(configuration.getAttribute("runProjectName", "Select a project..."));
			fileText.setText(configuration.getAttribute("runProjectFile", "Select a file..."));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		configuration.setAttribute("runProjectName", projectText.getText());
		configuration.setAttribute("runFileName", fileText.getText());
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		working = configuration;
	}

}
