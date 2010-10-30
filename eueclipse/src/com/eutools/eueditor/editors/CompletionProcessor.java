package com.eutools.eueditor.editors;

import java.util.ArrayList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.*;


public class CompletionProcessor implements IContentAssistProcessor {
    private final IContextInformation[] NO_CONTEXTS = new IContextInformation[0];
    private final char[] PROPOSAL_ACTIVATION_CHARS = new char[] { 's','f','p','n','m', };
    private ICompletionProposal[] NO_COMPLETIONS = new ICompletionProposal[0];

    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
       try {
          IDocument document = viewer.getDocument();
          ArrayList<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
          String prefix = lastWord(document, offset);
          String indent = null;//lastIndent(document, offset);
          EUModel model = EUModel.getModel(document);
          model.getContentProposals(prefix, indent, offset, result);
          //result.add("puts");
          //result.add("sequence");
          return (ICompletionProposal[]) result.toArray(new ICompletionProposal[result.size()]);
       } catch (Exception e) {
          // ... log the exception ...
          return NO_COMPLETIONS;
       }
    }
    private String lastWord(IDocument doc, int offset) {
       try {
          for (int n = offset-1; n >= 0; n--) {
            char c = doc.getChar(n);
            if (!Character.isJavaIdentifierPart(c))
              return doc.get(n + 1, offset-n-1);
          }
       } catch (BadLocationException e) {
          // ... log the exception ...
       }
       return "";
    }
    private String lastIndent(IDocument doc, int offset) {
       try {
          int start = offset-1; 
          while (start >= 0 && doc.getChar(start)!= '\n') start--;
          int end = start;
          while (end < offset && Character.isSpaceChar(doc.getChar(end))) end++;
          return doc.get(start+1, end-start-1);
       } catch (BadLocationException e) {
          e.printStackTrace();
       }
       return "";
    }
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) { 
       return NO_CONTEXTS;
    }
    public char[] getCompletionProposalAutoActivationCharacters() {
       return PROPOSAL_ACTIVATION_CHARS;
    }
    // ... remaining methods are optional ...
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
