package com.eutools.eueditor.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class EUEditor extends TextEditor {

	private ColorManager colorManager;

	public EUEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new EUConfiguration(colorManager));
		setDocumentProvider(new EUDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
