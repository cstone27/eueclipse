package com.eutools.eueditor.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;

import com.eutools.eueditor.Activator;
import com.eutools.eueditor.editors.IEUColorConstants;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_BOOLEAN, true);
		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
		store.setDefault(PreferenceConstants.P_STRING, "Default value");
		initializeSyntaxColorPreferences(store);
	}

	private void initializeSyntaxColorPreferences(IPreferenceStore store){
		store.setDefault(PreferenceConstants.BUILTIN_COLOR_PREF, StringConverter.asString(IEUColorConstants.EU_BUILTIN));
		store.setDefault(PreferenceConstants.COMMENT_COLOR_PREF, StringConverter.asString(IEUColorConstants.EU_COMMENT));
		store.setDefault(PreferenceConstants.DEFAULT_COLOR_PREF, StringConverter.asString(IEUColorConstants.DEFAULT));
		store.setDefault(PreferenceConstants.KEYWORD_COLOR_PREF, StringConverter.asString(IEUColorConstants.EU_KEYWORD));
		store.setDefault(PreferenceConstants.STRING_COLOR_PREF, StringConverter.asString(IEUColorConstants.STRING));
	}
}
