/**
 * 
 */
package com.eutools.eueditor.preferences.runtime;

import com.eutools.eueditor.runtime.RuntimeVersion;

/**
 * DAO object for Euphoria runtime settings
 * 
 * @author Chris Stone
 * @since 0.0.4
 *
 */
public abstract class RuntimeSettings {
	protected RuntimeVersion version;
	protected String euphoriaDir;
	protected String euphoriaIncludes;
	protected String[] additionalIncludes;
	protected String interpreterExecutable;
	protected String binderExecuteable;
	protected String translatorExecuteable;
	protected String configFile;
	protected String additionalArgs;
	
	/**
	 * Gets an instance of either global or project level runtime settings. The the scope of 
	 * the settings is <code>null</code>, the global settings will be returned.
	 * 
	 * @param The scope of the settings
	 * @return The runtime settings
	 */
	public RuntimeSettings getInstance(RuntimeSettingsTypes settingType){
		RuntimeSettings settings = null;
		if (RuntimeSettingsTypes.PROJECT.equals(settingType)){
			settings = new ProjectRuntimeSettings();
		}
		else{
			settings = new GlobalRuntimeSettings();
		}
		return settings;
	}
	
	public String getEuphoriaDir() {
		return euphoriaDir;
	}
	public void setEuphoriaDir(String euphoriaDir) {
		this.euphoriaDir = euphoriaDir;
	}
	public String getEuphoriaIncludes() {
		return euphoriaIncludes;
	}
	public void setEuphoriaIncludes(String euphoriaIncludes) {
		this.euphoriaIncludes = euphoriaIncludes;
	}
	public String[] getAdditionalIncludes() {
		return additionalIncludes;
	}
	public void setAdditionalIncludes(String[] additionalIncludes) {
		this.additionalIncludes = additionalIncludes;
	}
	public String getInterpreterExecutable() {
		return interpreterExecutable;
	}
	public void setInterpreterExecutable(String interpreterExecutable) {
		this.interpreterExecutable = interpreterExecutable;
	}
	public String getBinderExecuteable() {
		return binderExecuteable;
	}
	public void setBinderExecuteable(String binderExecuteable) {
		this.binderExecuteable = binderExecuteable;
	}
	public String getTranslatorExecuteable() {
		return translatorExecuteable;
	}
	public void setTranslatorExecuteable(String translatorExecuteable) {
		this.translatorExecuteable = translatorExecuteable;
	}
	public String getConfigFile() {
		return configFile;
	}
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	public String getAdditionalArgs() {
		return additionalArgs;
	}
	public void setAdditionalArgs(String additionalArgs) {
		this.additionalArgs = additionalArgs;
	}

	public RuntimeVersion getVersion() {
		return version;
	}

	public void setVersion(RuntimeVersion version) {
		this.version = version;
	}
}
