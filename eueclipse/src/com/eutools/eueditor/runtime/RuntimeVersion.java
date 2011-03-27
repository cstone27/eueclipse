package com.eutools.eueditor.runtime;

public class RuntimeVersion {
	private int major;
	private int minor;
	private int revision;
	
	public RuntimeVersion(){
		major = 0;
		minor = 0;
		revision = 0;
	}
	public RuntimeVersion(int major, int minor, int revision){
		this();
		this.major = major;
		this.minor = minor;
		this.revision = revision;
	}

	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public int getRevision() {
		return revision;
	}
	public void setRevision(int revision) {
		this.revision = revision;
	}
}
