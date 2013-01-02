/**
 * 
 */
package com.eutools.eueditor.preferences.runtime;

import org.eclipse.jface.preference.IPreferenceStore;

import com.eutools.eueditor.Activator;
import com.eutools.eueditor.preferences.PreferenceConstants;

/**
 * Global (default) Euphoria runtime settings.
 * 
 * @author Chris Stone
 * @since 0.0.4
 */
public class GlobalRuntimeSettings extends RuntimeSettings {
	private IPreferenceStore store = null;
	
	protected GlobalRuntimeSettings() {
		store = Activator.getDefault().getPreferenceStore();
		if (store != null){
			this.additionalArgs = store.getString(PreferenceConstants.P_ADDITIONAL_COMMAND_ARGS);
			this.additionalIncludes = null; // TODO Get additional includes and parse
			this.binderExecuteable = store.getString(PreferenceConstants.P_EUPHORIA_BINDER);
			this.configFile = store.getString(PreferenceConstants.P_CONFIGFILE);
			this.euphoriaDir = store.getString(PreferenceConstants.P_EUDIR);
			this.euphoriaIncludes = null; // TODO Get Euphoria includes directory(s)
			this.interpreterExecutable = store.getString(PreferenceConstants.P_EUPHORIA_INTERPRETER);
			this.translatorExecuteable = store.getString(PreferenceConstants.P_EUPHORIA_TRANSLATOR);
		}
	}
}
