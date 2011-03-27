package com.eutools.eueditor.runtime;

public enum RuntimePropertyName {
	BATCH("-BATCH"),
	COPYRIGHT("-COPYRIGHT"),
	CONFIG_FILE("-C"),
	CONSOLE("-CON"),
	DEFINE_WORD("-D"),
	DEBUG("-DEBUG"),
	WIN_LIBRARY("-DLL"),
	NIX_LIBRARY("-SO"),
	EUDIR("-EUDIR"),
	DISPLAY_HELP("-H"),
	INCLUDE_PATH("-I"),
	TRANSLATOR_LIBRARY("-LIB"),
	TRANSLATION_PLATFORM("-PLAT"),
	STRICT("-STRICT"),
	TEST("-TEST"),
	VERSION("-VERSION"),
	WARNING_NAME("-W"),
	WARNING_FILE("-WF"),
	EXCLUDE_WARNING("-X");
	
	RuntimePropertyName(String name){
		this.name = name;
	}
	private String name;
	
	public String toString(){
		return this.name;
	}
}
