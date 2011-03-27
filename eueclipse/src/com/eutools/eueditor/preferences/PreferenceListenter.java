package com.eutools.eueditor.preferences;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;

public class PreferenceListenter implements IEclipsePreferences.IPreferenceChangeListener {

	@Override
	public void preferenceChange(PreferenceChangeEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Preference " + event.getKey() + " was changed to " + event.getNewValue());
	}

}
