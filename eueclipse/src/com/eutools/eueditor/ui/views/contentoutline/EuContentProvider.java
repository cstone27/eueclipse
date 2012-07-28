package com.eutools.eueditor.ui.views.contentoutline;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import com.eutools.eueditor.Activator;

public class EuContentProvider extends BaseWorkbenchContentProvider {

	public EuContentProvider() {
		super();
	}
	public Object[] getElements(Object element){
		IDocument doc = getCurrentDocument();
		if (doc != null){
			String content = doc.get();
			if (content != null && content.length() > 0){
				System.out.println("Doc contents\n");
				System.out.println(content);
			}
		}
		OutlineRoot [] roots = null;
		if (element instanceof OutlineRoot){
			OutlineRoot el = (OutlineRoot)element;
			roots = el.getChildrenAsArray();
		}
		if (roots == null){
			roots = new OutlineRoot[1];
			OutlineRoot root = new OutlineRoot();
			root.setId(1000);
			roots[0] = root;
			for (OutlineRoot child:Activator.outline.getChildrenAsArray()){
				root.addChild(child);
			}
		}
		return roots;
	}
	
	private IDocument getCurrentDocument(){
		IDocument doc = null;
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null){
			IWorkbenchPage activePage = window.getActivePage();
			if (activePage != null){
				IEditorPart editorPart = activePage.getActiveEditor();
				if (editorPart != null){
					try{
						ITextEditor textEditor = (ITextEditor) editorPart;
						doc = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
					} catch(ClassCastException e){
						System.out.println("editorPart is not an ITextEditor");
					}
				}
			}
		}
		return doc;
	}
	
	public Object[] getChildren(Object element){
		if (element instanceof OutlineRoot){
			return ((OutlineRoot) element).getChildrenAsArray();
		}
		else{
			return super.getChildren(element);
		}
	}
	
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        super.inputChanged(viewer, oldInput, newInput);
    	System.out.println("ContentProvider input changed");
    }
}
