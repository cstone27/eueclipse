package com.eutools.eueditor.ui.views.contentoutline;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;

public class EuContentProvider extends BaseWorkbenchContentProvider {

	public EuContentProvider() {
		super();
	}
	public Object[] getElements(Object element){
		String[] roots = new String[3];
		roots[0] = "One";
		roots[1] = "Two";
		roots[2] = "Three";
		return roots;
	}
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        super.inputChanged(viewer, oldInput, newInput);
    	System.out.println("ContentProvider input changed");
    }
}
