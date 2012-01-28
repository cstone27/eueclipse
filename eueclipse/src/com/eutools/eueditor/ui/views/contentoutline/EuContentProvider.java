package com.eutools.eueditor.ui.views.contentoutline;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;

import com.eutools.eueditor.Activator;

public class EuContentProvider extends BaseWorkbenchContentProvider {

	public EuContentProvider() {
		super();
	}
	public Object[] getElements(Object element){
//		String[] roots = new String[3];
//		roots[0] = "One";
//		roots[1] = "Two";
//		roots[2] = "Three";
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
