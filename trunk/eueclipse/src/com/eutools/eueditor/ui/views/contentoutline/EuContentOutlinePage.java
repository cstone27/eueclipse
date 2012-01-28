package com.eutools.eueditor.ui.views.contentoutline;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import com.eutools.eueditor.Activator;


public class EuContentOutlinePage extends ContentOutlinePage {
	public static final String ID = IPageLayout.ID_OUTLINE;
	
	public EuContentOutlinePage() {
		// TODO Auto-generated constructor stub
	}
	public void init(IPageSite pageSite){
		super.init(pageSite);
//		pageSite.setSelectionProvider(this);
//		ISelection sel = (ISelection)pageSite.getWorkbenchWindow().getSelectionService().getSelection();
//		IStructuredSelection ss = (IStructuredSelection)sel;
//		Object o = ss.getFirstElement();
		
		
	}
	public void createControl(Composite parent) {
		super.createControl(parent);
		TreeViewer tv = getTreeViewer();
		if (tv != null){
			tv.setContentProvider(new EuContentProvider());
			tv.setLabelProvider(new EuLabelProvider());
//			tv.setInput(new String [] {"One", "Two"});
			tv.setInput(Activator.outline);
		}
	}
}
