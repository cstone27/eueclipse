package com.eutools.eueditor.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class EUPartitionScanner extends RuleBasedPartitionScanner {
	public final static String EU_COMMENT = "__eu_comment";
	public final static String EU_TAG = "__eu_tag";

	public EUPartitionScanner() {
		IToken procInstr = new Token(EU_COMMENT);
		IPredicateRule[] rules = new IPredicateRule[2];
		rules[0] = new SingleLineRule("--", null, procInstr);
		rules[1] = new MultiLineRule("/*", "*/", procInstr);
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
