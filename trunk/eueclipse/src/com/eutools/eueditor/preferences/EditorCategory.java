package com.eutools.eueditor.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class EditorCategory extends PreferencePage implements
		IWorkbenchPreferencePage {

	public EditorCategory() {
		// TODO Auto-generated constructor stub
	}

	public EditorCategory(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public EditorCategory(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		GridLayout layout = new GridLayout();
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(layout);
		return comp;
	}

}
