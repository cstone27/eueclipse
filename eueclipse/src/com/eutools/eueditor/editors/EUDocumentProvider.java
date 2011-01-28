package com.eutools.eueditor.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.part.FileEditorInput;

public class EUDocumentProvider extends FileDocumentProvider {
	private IDocument document = null;
	
	public EUDocumentProvider(){
		super();
	}

	protected IDocument createDocument(Object element) throws CoreException {
		document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new EUPartitionScanner(),
					new String[] {
						EUPartitionScanner.EU_STRING,
						EUPartitionScanner.EU_COMMENT });
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}
	public IDocument getDocument(Object element){
		return document;
	}
	public void changed(Object element){
		super.changed(element);
		if (element instanceof IResource){
			
		}
		else if (element instanceof IFile){
			IFile file = (IFile)element;
			System.out.println("File Changed: " + file.getName());
		}
		else if (element instanceof FileEditorInput){
			FileEditorInput file = (FileEditorInput)element;
			System.out.println("File Editor Changed: " + file.getFile().getName());
		}
	}
}