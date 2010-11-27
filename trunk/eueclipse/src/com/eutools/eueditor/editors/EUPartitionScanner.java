package com.eutools.eueditor.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.*;

public class EUPartitionScanner extends RuleBasedPartitionScanner {
	public final static String EU_COMMENT = "__eu_comment";
	public final static String EU_TAG = "__eu_tag";

	public EUPartitionScanner() {
		ColorManager manager = new ColorManager();
		//IToken xmlComment = new Token(EU_COMMENT);
		//IToken tag = new Token(EU_TAG);
		IToken procInstr =
			new Token(
				new TextAttribute(
					manager.getColor(IEUColorConstants.EU_COMMENT)));
		IPredicateRule[] rules = new IPredicateRule[1];

		//rules[0] = new MultiLineRule("<!--", "-->", xmlComment);
		rules[0] = new SingleLineRule("--", null, procInstr);
		//rules[1] = new TagRule(tag);

		setPredicateRules(rules);
	}
}
