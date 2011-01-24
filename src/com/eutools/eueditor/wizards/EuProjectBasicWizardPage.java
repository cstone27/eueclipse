package com.eutools.eueditor.wizards;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class EuProjectBasicWizardPage extends WizardPage {
	ISelection selection;
	Text projectText;
	
	public EuProjectBasicWizardPage(ISelection selection) {
		super("basicPageEuProject");
		this.selection = selection;
		setTitle("Euphoria Project");
		setDescription("Creates a Euphoria Project");
	}

	public EuProjectBasicWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
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
		label.setText("&Project Name:");
		projectText =  new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		projectText.setLayoutData(gd);
		setControl(container);
	}
	public String getProjectName(){
		if (projectText != null){
			return projectText.getText();
		}
		else{
			return null;
		}
	}

}
