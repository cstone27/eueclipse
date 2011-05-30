package com.eutools.eueditor.preferences;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.eutools.eueditor.preferences.runtime.RuntimeOverviewPreference;

public class InstalledRuntimesComponent extends Composite {

	private Group buttonGroup = null;
	private Button addButton = null;
	private Button removeButton = null;
	private Button makeDefaultButton = null;
	private Button editButton = null;
	private Table runtimeTable = null;
	private TableViewer tableViewer = null;
	public InstalledRuntimesComponent(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		GridData gridData2 = new GridData();
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		runtimeTable = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		runtimeTable.setHeaderVisible(true);
		runtimeTable.setLayoutData(gridData2);
		runtimeTable.setLinesVisible(true);
		runtimeTable
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						System.out.println("widgetSelected()");
					}
				});
		TableColumn nameTableColumn = new TableColumn(runtimeTable, SWT.NONE);
		nameTableColumn.setWidth(200);
		nameTableColumn.setText("Name");
		TableColumn versionTableColumn = new TableColumn(runtimeTable, SWT.NONE);
		versionTableColumn.setWidth(60);
		versionTableColumn.setText("Version");
		TableColumn defaultTableColumn = new TableColumn(runtimeTable, SWT.NONE);
		defaultTableColumn.setWidth(60);
		defaultTableColumn.setText("Default");
		tableViewer = new TableViewer(runtimeTable);
		createButtonGroup();
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setBackground(new Color(Display.getCurrent(), 212, 208, 200));
		setSize(new Point(300, 200));
			this.setLayout(gridLayout);
			gridLayout.numColumns = 2;
		initializeListViewer();
	}

	private void initializeListViewer() {
		TableItem item = new TableItem(runtimeTable, SWT.NONE);
		RuntimeOverviewPreference pref = new RuntimeOverviewPreference();
		pref.setRuntimeDefault("default");
		pref.setRuntimeName("Euphoria 4.0");
		pref.setRuntimeVersion("4.0");
		String [] rowData = {pref.getRuntimeName(), pref.getRuntimeVersion(), pref.getRuntimeDefault()};
		item.setText(rowData);
		
		pref = new RuntimeOverviewPreference();
		pref.setRuntimeDefault("");
		pref.setRuntimeName("Euphoria 3");
		pref.setRuntimeVersion("3.1");
		rowData = new String[]{pref.getRuntimeName(), pref.getRuntimeVersion(), pref.getRuntimeDefault()};
		item = new TableItem(runtimeTable, SWT.NONE);
		item.setText(rowData);
	}

	/**
	 * This method initializes buttonGroup	
	 *
	 */
	private void createButtonGroup() {
		GridData gridData1 = new GridData();
		gridData1.widthHint = 85;
		gridData1.verticalAlignment = GridData.FILL;
		gridData1.horizontalAlignment = GridData.BEGINNING;
		RowData rowData3 = new org.eclipse.swt.layout.RowData();
		rowData3.height = 23;
		rowData3.width = 75;
		RowData rowData2 = new org.eclipse.swt.layout.RowData();
		rowData2.height = 23;
		rowData2.width = 75;
		RowData rowData1 = new org.eclipse.swt.layout.RowData();
		rowData1.height = 23;
		rowData1.width = 75;
		RowData rowData = new org.eclipse.swt.layout.RowData();
		rowData.height = 23;
		rowData.width = 75;
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = org.eclipse.swt.SWT.VERTICAL;
		buttonGroup = new Group(this, SWT.NONE);
		buttonGroup.setLayout(rowLayout);
		buttonGroup.setLayoutData(gridData1);
		addButton = new Button(buttonGroup, SWT.NONE);
		addButton.setText("Add");
		addButton.setLayoutData(rowData);
		removeButton = new Button(buttonGroup, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setLayoutData(rowData1);
		editButton = new Button(buttonGroup, SWT.NONE);
		editButton.setText("Edit");
		editButton.setLayoutData(rowData2);
		makeDefaultButton = new Button(buttonGroup, SWT.NONE);
		makeDefaultButton.setText("Default");
		makeDefaultButton.setLayoutData(rowData3);
	}

}
