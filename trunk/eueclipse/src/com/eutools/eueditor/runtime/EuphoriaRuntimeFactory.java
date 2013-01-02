package com.eutools.eueditor.runtime;

import com.eutools.eueditor.preferences.runtime.RuntimeSettings;

public class EuphoriaRuntimeFactory {
	/**
	 * Returns a Euphoria runtime based on the provided settings.
	 * 
	 * @param settings The settings
	 * @return The Euphoria runtime
	 */
	public static IEuphoriaRuntime getEuphoriaRuntime(RuntimeSettings settings) {
		IEuphoriaRuntime runtime = null;
		if (settings != null){
			RuntimeVersion version = settings.getVersion();
			if (version != null){
				runtime = getEuRuntime(version.getMajor(), version.getMinor(), version.getRevision());
			}
		}
		return runtime;
	}
	
	/**
	 * Get a Euphoria runtime by configuration name
	 * @param name
	 * @return
	 */
	public static IEuphoriaRuntime getEuRuntime(String name){
		return null;
	}
	
	/**
	 * Get a Euphoria runtime by version.  The method searches the configured runtimes and returns the
	 * first runtime which matches the version specified.
	 * @param major
	 * @param minor
	 * @param revision
	 * @return
	 */
	public static IEuphoriaRuntime getEuRuntime(int major, int minor, int revision){
		String pkgName = EuphoriaRuntimeBase.class.getPackage().toString();
		String clsName = String.format("EuRuntime%s%s%s", major,minor,revision);
		
		IEuphoriaRuntime r;
		try {
			@SuppressWarnings("unchecked")
			Class<IEuphoriaRuntime> c = (Class<IEuphoriaRuntime>) Class.forName(pkgName + "." + clsName);
			r = (IEuphoriaRuntime)c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (ClassCastException e){
			e.printStackTrace();
			return null;
		}
		return r;
	}
}
