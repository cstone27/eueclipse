package com.eutools.eueditor.editors;
import java.util.List;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

public class EUModel {
	public static EUModel getModel(IDocument doc){
		return new EUModel();
	}
	public void getContentProposals(String prefix, String indent, int offset, List<ICompletionProposal> result){
		if (prefix != null && prefix.length() > 0){
			String [] keywords = EUScanner.getKeywords();
			String [] builtins = EUScanner.getBuiltins();
			if (keywords != null && keywords.length > 0){
				for (int i=0; i<keywords.length; i++){
					if (keywords[i].startsWith(prefix)){
						CompletionProposal prop = new CompletionProposal(keywords[i], offset - prefix.length(), prefix.length(), keywords[i].length());
						result.add(prop);
					}
				}
			}
			if (builtins != null && builtins.length > 0){
				for (int i=0; i<builtins.length; i++){
					if (builtins[i].startsWith(prefix)){
						CompletionProposal prop = new CompletionProposal(builtins[i], offset - prefix.length(), prefix.length(), builtins[i].length());
						result.add(prop);
					}
				}
			}
		}
	}
}
