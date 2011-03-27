package com.eutools.eueditor.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.eutools.eueditor.Activator;

public class RuntimeInstallationPerferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	public RuntimeInstallationPerferencePage() {
		super();
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Add, remove or edit Euphoria installations.  The checked installation is the Euphoria installation which will be used by defuault for new Euphoria projects.");
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		
	}
	protected Control createContents(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout(2, false);
		comp.setLayout(gridLayout);

		TableViewer tableViewerCategories =	new TableViewer(comp,
							SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);

		final Table tblRuntimes = tableViewerCategories.getTable();
		tblRuntimes.setLayoutData(new GridData(GridData.FILL_BOTH));
		tblRuntimes.setLinesVisible(true);
		tblRuntimes.setLayout(new GridLayout());
		tblRuntimes.setHeaderVisible(true);

		//Define the names, and number, of columns in the editor
		String[] categoryColumns = new String[] { "Name", "Location", "Version"};
		{
			final TableColumn colName = new TableColumn(tblRuntimes, SWT.LEAD, 0);
			colName.setWidth(100);
			colName.setText(categoryColumns[0]);
		}
		
		{
			final TableColumn colLocation= new TableColumn(tblRuntimes, SWT.LEAD, 1);
			colLocation.setWidth(75);
			colLocation.setText(categoryColumns[1]);
		}
		
		{
			final TableColumn colVersion = new TableColumn(tblRuntimes, SWT.LEAD, 2);
			colVersion.setWidth(75);
			colVersion.setText(categoryColumns[2]);
		}

		GridLayout buttonLayout = new GridLayout();
		Composite bComp = new Composite(comp, SWT.NONE);
		bComp.setLayout(buttonLayout);

		Button addButton = new Button(bComp, SWT.PUSH);
		addButton.setText("&Add");
		addButton.setSize(75, 25);

		Button deleteButton = new Button(bComp, SWT.PUSH);
		deleteButton.setText("&Remove");
		deleteButton.setSize(75, 25);
		
		Button editButton = new Button(bComp, SWT.PUSH);
		editButton.setText("&Edit");
		editButton.setSize(75, 25);

		Button defaultButton = new Button(bComp, SWT.PUSH);
		defaultButton.setText("&Default");
		defaultButton.setSize(75, 25);
		
		TableItem item = new TableItem(tblRuntimes, SWT.NONE);
		String [] rowData = {"Euphoria 3", "C:\\euphoria", "3.1"};
		item.setText(rowData);

		TableItem item2 = new TableItem(tblRuntimes, SWT.NONE);
		String [] rowData2 = {"Euphoria 4 (default)", "C:\\EU4", "4.0.0"};
		item2.setText(rowData2);

		tableViewerCategories.setColumnProperties(categoryColumns);
		return comp;
	}
}
