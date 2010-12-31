package com.eutools.eueditor.launch;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

import com.eutools.eueditor.launch.tabs.MainTab;

public class EuBindLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {
	public static int MAIN_LOOKUP_TAB_INDEX = 0;
	public static int ENVIRONMENT_TAB_INDEX = 1;
	public static int COMMON_TAB_INDEX = 2;
	ILaunchConfigurationTab[] tabs;
	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		tabs = new ILaunchConfigurationTab[3];
		tabs[EuBindLaunchConfigurationTabGroup.MAIN_LOOKUP_TAB_INDEX] = new MainTab();
		tabs[EuBindLaunchConfigurationTabGroup.ENVIRONMENT_TAB_INDEX] = new EnvironmentTab();
		tabs[EuBindLaunchConfigurationTabGroup.COMMON_TAB_INDEX] = new CommonTab();
		setTabs(tabs);
	}
	public ILaunchConfigurationTab[] getConfigurationTabs(){
		return tabs;
	}
}
