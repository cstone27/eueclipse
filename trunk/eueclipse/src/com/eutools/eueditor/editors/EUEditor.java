package com.eutools.eueditor.editors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.editors.text.TextEditor;

import com.eutools.eueditor.ui.views.contentoutline.EuContentOutlinePage;

public class EUEditor extends TextEditor implements IAdaptable {

	private ColorManager colorManager;
	private EuContentOutlinePage contentOutlinePage = null;
	
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
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter){
		return super.getAdapter(adapter);
	}
	public void updateContentOutline(){
		if (contentOutlinePage != null){
			
		}
	}
}
