package com.eutools.eueditor.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import com.eutools.eueditor.preferences.PreferenceConstants;

public class EUScanner extends RuleBasedScanner {
	private static String [] builtins = {
	    "?","abort","and_bits","append","arctan","atom","c_func","c_proc","call",
	    "call_func","call_proc","clear_screen","close","command_line","compare",
	    "cos","date","delete","delete_routine","equal","find","find_from","floor",
	    "get_key","get_pixel","getc","getenv","gets","hash","head","include_paths",
	    "insert","integer","length","log","machine_func","machine_proc","match",
	    "match_from","mem_copy","mem_set","not_bits","object","open","option_switches",
	    "or_bits","peek","peek2s","peek2u","peek4s","peek4u","peek_string","peeks",
	    "pixel","platform","poke","poke2","poke4","position","power","prepend","print",
	    "printf","profile","profile_time","puts","rand","remainder","remove","repeat",
	    "replace","routine_id","sequence","sin","splice","sprintf","sqrt","system",
	    "system_exec","tail","tan","task_clock_start","task_clock_stop","task_create",
	    "task_list","task_schedule","task_self","task_status","task_suspend","task_yield",
	    "time","trace","warning","xor_bits"};
	
	private static String [] keywords = {
		"and", "as","break","by","case","constant","continue","do",
		"else","elsedef","elsif","elsifdef","end","entry","enum","exit",
		"export","fallthru","for","function","global","goto","if",
		"ifdef","include","label","loop","namespace","not","or","override",
		"procedure","public","retry","return","routine","switch","then","to",
		"type","until","while","with","without","xor"};

	public EUScanner(){
		super();
	}
	public EUScanner(ColorManager manager) {
		this();
		IToken procInstr	= new Token(new TextAttribute(manager.getColor(PreferenceConstants.COMMENT_COLOR_PREF)));
		IToken builtinInstr = new Token(new TextAttribute(manager.getColor(PreferenceConstants.BUILTIN_COLOR_PREF)));
		IToken stringInstr 	= new Token(new TextAttribute(manager.getColor(PreferenceConstants.STRING_COLOR_PREF)));
		IToken keywordInstr = new Token(new TextAttribute(manager.getColor(PreferenceConstants.KEYWORD_COLOR_PREF)));
		
		WordRule rule = new WordRule(new IWordDetector(){
			public boolean isWordStart(char c){
				return Character.isJavaIdentifierStart(c);
			}
			public boolean isWordPart(char c){
				return Character.isJavaIdentifierPart(c);
			}
		});

		for (int i = 0; i < builtins.length; i++){
			rule.addWord(builtins[i], builtinInstr);
		}
		for (int i = 0; i < keywords.length; i++){
			rule.addWord(keywords[i], keywordInstr);
		}
		IRule[] rules = new IRule[5];
		//Add rule for comments
		rules[0] = new EndOfLineRule("--", procInstr);
		rules[1] = new MultiLineRule("/*", "*/", procInstr);
		// Add generic whitespace rule.
		rules[2] = new WhitespaceRule(new EUWhitespaceDetector());
		rules[3] = rule;
		rules[4] = new SingleLineRule("\"", "\"", stringInstr, '\\');
		setRules(rules);
	}
	public static String[] getBuiltins() {
		return builtins;
	}
	public void setBuiltins(String[] builtins) {
		EUScanner.builtins = builtins;
	}
	public static String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		EUScanner.keywords = keywords;
	}
	public IToken nextToken(){
		IToken tok = super.nextToken();
		if (tok != null && tok.getData() != null){
//			System.out.println("EUScanner next token: " + tok.getData().toString());
		}
		else{
//			System.out.println("EUScanner next token: null");
		}
		return tok;
	}
}
