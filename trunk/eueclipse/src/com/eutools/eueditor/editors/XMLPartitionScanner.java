package com.eutools.eueditor.editors;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * 
 * @author Chris Stone
 *
 */
public class XMLPartitionScanner extends RuleBasedPartitionScanner {
	/**
	 * XML Comment Token.
	 */
	public static final String XML_COMMENT = "__xml_comment";
	
	/**
	 * XML Tag Token.
	 */
	public static final String XML_TAG = "__xml_tag";

	/**
	 * 
	 */
	public XMLPartitionScanner() {

		IToken xmlComment = new Token(XML_COMMENT);
		IToken tag = new Token(XML_TAG);

		IPredicateRule[] rules = new IPredicateRule[2];

		rules[0] = new MultiLineRule("<!--", "-->", xmlComment);
		rules[1] = new TagRule(tag);

		setPredicateRules(rules);
	}
}
