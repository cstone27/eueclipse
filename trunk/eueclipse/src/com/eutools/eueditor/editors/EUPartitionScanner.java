package com.eutools.eueditor.editors;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class EUPartitionScanner extends RuleBasedPartitionScanner {
	public final static String EU_COMMENT = "__eu_comment";
	public final static String EU_STRING = "__eu_string";

	public EUPartitionScanner() {
		IToken procInstr = new Token(EU_COMMENT);
		IToken stringInstr = new Token(EU_STRING);

		IPredicateRule[] rules = new IPredicateRule[4];
		char esc = (char)0;
		rules[0] = new SingleLineRule("--", null, procInstr);
		rules[1] = new MultiLineRule("/*", "*/", procInstr, esc, true); 
		
		// Add rule for double quotes
		rules[2] = new SingleLineRule("\"", "\"", stringInstr, '\\');
		// Add a rule for single quotes
		rules[3] = new SingleLineRule("'", "'", stringInstr, '\\');

		
		setPredicateRules(rules);
		

	}
	public IToken nextToken(){
		IToken tok = super.nextToken();
		if (tok != null && tok.getData() != null){
//			System.out.println("Partition Scanner next token: " + tok.getData().toString());
		}
		else{
//			System.out.println("Partition Scanner next token: null");
		}
		return tok;
	}
}
