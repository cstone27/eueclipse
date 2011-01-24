package com.eutools.eueditor.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.eutools.eueditor.Activator;

public class RuntimeInstallationPerferencePage extends
		FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public RuntimeInstallationPerferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Add, remove or edit Euphoria installations.  The checked installation is the Euphoria installation which will be used by defuault for new Euphoria projects.");
		
	}

	@Override
	protected void createFieldEditors() {
//		addField(new DirectoryFieldEditor(PreferenceConstants.P_EUDIR, 
//				"&EUDIR:", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		super.initialize();
	}

}
