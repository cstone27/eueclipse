package com.eutools.eueditor.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.contentassist.*;

import com.eutools.eueditor.preferences.PreferenceConstants;

public class EUConfiguration extends SourceViewerConfiguration {
	private EUDoubleClickStrategy doubleClickStrategy;
	private EUTagScanner tagScanner;
	private EUScanner scanner;
	private ColorManager colorManager;

	public EUConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			EUPartitionScanner.EU_COMMENT,
			EUPartitionScanner.EU_TAG };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new EUDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected EUScanner getEuScanner() {
		if (scanner == null) {
			scanner = new EUScanner(colorManager);
			scanner.setDefaultReturnToken(new Token(new TextAttribute(colorManager.getColor(PreferenceConstants.DEFAULT_COLOR_PREF))));
		}
		return scanner;
	}
	protected EUTagScanner getTagScanner() {
		if (tagScanner == null) {
			tagScanner = new EUTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(new Token(new TextAttribute(colorManager.getColor(PreferenceConstants.DEFAULT_COLOR_PREF))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getTagScanner());
		reconciler.setDamager(dr, EUPartitionScanner.EU_TAG);
		reconciler.setRepairer(dr, EUPartitionScanner.EU_TAG);

		dr = new DefaultDamagerRepairer(getEuScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		reconciler.setDamager(dr, EUPartitionScanner.EU_COMMENT);
		reconciler.setRepairer(dr, EUPartitionScanner.EU_COMMENT);

		return reconciler;
	}
	   public IContentAssistant getContentAssistant(ISourceViewer sv) {
		      ContentAssistant ca = new ContentAssistant();
		      IContentAssistProcessor pr = new CompletionProcessor();
		      ca.setContentAssistProcessor(pr, "EU_DOC");
		      ca.setContentAssistProcessor(pr, IDocument.DEFAULT_CONTENT_TYPE);
		      ca.setInformationControlCreator(getInformationControlCreator(sv));
		      return ca;
		   }


}