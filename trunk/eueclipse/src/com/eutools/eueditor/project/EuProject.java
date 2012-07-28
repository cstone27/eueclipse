package com.eutools.eueditor.project;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;

import com.eutools.eueditor.EUCore;
import com.eutools.eueditor.wizards.EuProjectBasicWizardPage;

public class EuProject extends Wizard implements IWorkbenchWizard{
	private IWorkbench workbench;
	private ISelection selection;
	private EuProjectBasicWizardPage basicPage;
	@Override
	public boolean performFinish() {
		String projectName = basicPage.getProjectName();
		if (projectName != null && projectName != ""){
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = root.getProject(projectName);
			try {
				project.create(null);
				project.open(null);
				IFile eucfg = project.getFile("eu.cfg");
				String contents = new String ("# Configuration File");
				eucfg.create(new ByteArrayInputStream(contents.getBytes()), true, null);
				IProjectDescription description = project.getDescription();
				description.setNatureIds(new String[] {EUCore.NATURE_ID});
		        project.setDescription(description, null);
		        workbench.showPerspective("com.eutools.eueditor.EuphoriaPerspective", PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			} catch (CoreException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}
	
	@Override
	public void addPages(){
		super.addPages();
		basicPage = new EuProjectBasicWizardPage(selection);
		addPage(basicPage);
	}

}
