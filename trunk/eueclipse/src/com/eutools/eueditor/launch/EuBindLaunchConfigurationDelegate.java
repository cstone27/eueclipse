package com.eutools.eueditor.launch;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;

public class EuBindLaunchConfigurationDelegate extends
		LaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		System.out.println("Launch Config Delegate");
		Runtime r = Runtime.getRuntime();
		try {
			Process p = r.exec("eui /home/chris/runtime-EclipseApplication/eutest/highlight.ex");
			DebugPlugin.newProcess(launch, p, "Euphoria Process");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
