package com.eutools.eueditor.launch;

import org.eclipse.core.resources.IFile;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;

public class EuLaunchShortcut implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		// TODO Auto-generated method stub
		System.out.println("Bind selected, mode: " + mode);
		if (selection instanceof IStructuredSelection){
			Object [] selections = ((IStructuredSelection) selection).toArray();
			if (selections != null && selections.length > 0){
				for (int i=0; i<selections.length; i++){
					if (selections[i] instanceof IFile){
						IFile file = (IFile)selections[i];
						
					}
				}
			}
			System.out.println("Structured Selection");
		}
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		// TODO Auto-generated method stub
		System.out.println("Bind selected for editor, mode: " + mode);
	}

}
