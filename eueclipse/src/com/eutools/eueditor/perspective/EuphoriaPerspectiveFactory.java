package com.eutools.eueditor.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class EuphoriaPerspectiveFactory implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
        // Get the editor area.
        String editorArea = layout.getEditorArea();

        // Top left: Resource Navigator view and Bookmarks view placeholder
        IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.25f,
                editorArea);
        topLeft.addView(IPageLayout.ID_RES_NAV);
        topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);

        // Bottom left: Outline view and Property Sheet view
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.50f,
                "topLeft");
        bottomLeft.addView(IPageLayout.ID_OUTLINE);
        bottomLeft.addView(IPageLayout.ID_PROP_SHEET);

        // Bottom right: Task List view
        layout.addView(IPageLayout.ID_TASK_LIST, IPageLayout.BOTTOM, 0.66f, editorArea);

	}

}
