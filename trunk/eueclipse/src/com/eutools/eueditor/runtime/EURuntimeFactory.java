package com.eutools.eueditor.runtime;

public class EURuntimeFactory {
	
	/**
	 * Get a Euphoria runtime by configuration name
	 * @param name
	 * @return
	 */
	public IEURuntime getEuRuntime(String name){
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
	public IEURuntime getEuRuntime(int major, int minor, int revision){
		String pkgName = this.getClass().getPackage().toString();
		String clsName = String.format("EuRuntime%s%s%s", major,minor,revision);
		
		IEURuntime r;
		try {
			@SuppressWarnings("unchecked")
			Class<IEURuntime> c = (Class<IEURuntime>) Class.forName(pkgName + "." + clsName);
			r = (IEURuntime)c.newInstance();
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
