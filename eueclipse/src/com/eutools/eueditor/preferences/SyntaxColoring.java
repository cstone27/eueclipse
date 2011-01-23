package com.eutools.eueditor.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.eutools.eueditor.Activator;

public class SyntaxColoring extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public SyntaxColoring() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Euphoria editor syntax coloring preferences");
	}


	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}


	@Override
	protected void createFieldEditors() {
		// TODO Constants in PreferenceConstants
		addField(new ColorFieldEditor(PreferenceConstants.BUILTIN_COLOR_PREF, "Builtins", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.KEYWORD_COLOR_PREF, "Keywords", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.STRING_COLOR_PREF, "Strings", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COMMENT_COLOR_PREF, "Comments", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.DEFAULT_COLOR_PREF, "Default", getFieldEditorParent()));
	}
	

}
