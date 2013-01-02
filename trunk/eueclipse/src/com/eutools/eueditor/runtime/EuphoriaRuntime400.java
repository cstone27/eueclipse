package com.eutools.eueditor.runtime;

public class EuphoriaRuntime400 extends EuphoriaRuntimeBase implements IEuphoriaRuntime {

	public EuphoriaRuntime400() {
		super();
	}
	
	@Override
	public RuntimeVersion getVersion() {
		RuntimeVersion v = new RuntimeVersion(version.getMajor(), version.getMinor(), version.getRevision());
		return v;
	}

	@Override
	public int interpret(String fileName, String[] interpreterCommandLine,
			String[] programCommandLine) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bind(String fileName, String[] interpreterCommandLing) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int shroud(String fileName, String[] interpreterCommandLing) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int translate(String fileName, String[] interpreterCommandLing) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compile(String fileName, String[] interpreterCommandLing) {
		// TODO Auto-generated method stub
		return 0;
	}

}
