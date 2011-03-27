package com.eutools.eueditor.runtime;

import java.util.Map;

public interface IEURuntime {
	
	/**
	 * Returns a copy of the {@link RuntimeVersion} of the Euphoria runtime
	 */
	public abstract RuntimeVersion getVersion();
	
	/**
	 * Executes the specified Euphoria source file with the specified command line parameters.
	 * 
	 * @param fileName - The source file to execute
	 * @param interpreterCommandLine - Command line parameters to pass to the interpreter
	 * @param programCommandLine - Command line parameters to pass to the interpreted program
	 * @return
	 */
	public int interpret(String fileName, String[] interpreterCommandLine, String[] programCommandLine);
	
	/**
	 * Binds the specified Euphoria source file.
	 * 
	 * @return
	 */
	public int bind(String fileName, String[] interpreterCommandLing);
	
	/**
	 * Shrouds the specified Euphoria source file
	 * @param fileName
	 * @param interpreterCommandLing
	 * @return
	 */
	public int shroud(String fileName, String[] interpreterCommandLing);
	
	/**
	 * Translates the specified source file to C code.
	 * 
	 * @param fileName
	 * @param interpreterCommandLing
	 * @return
	 */
	public int translate(String fileName, String[] interpreterCommandLing);

	/**
	 * Translates and compiles the specified Euphoria file.
	 * 
	 * @param fileName
	 * @param interpreterCommandLing
	 * @return
	 */
	public int compile(String fileName, String[] interpreterCommandLing);

	/**
	 * Set a runtime property
	 * 
	 * @param name
	 * @param value
	 * @return - If the property already exists, returns the existing value.
	 * Otherwise returns <code>null</code>
	 */
	public String setProperty(String name, String value);

	/**
	 * Retrieves the value of a runtime property
	 * 
	 * @param name
	 * @return
	 */
	public String getProperty(String name);

	/**
	 * Sets a list of runtime properties.  Any properties which already exist will be
	 * overwritten.
	 * 
	 * @param properties - A Map of property names to property values.
	 */
	public void setProperties(Map<String, String> properties);

	/**
	 * Get a Map of runtime properties.  The function returns a copy of the internal property map.
	 * 
	 * @return
	 */
	public Map<String, String> getProperties();
}
