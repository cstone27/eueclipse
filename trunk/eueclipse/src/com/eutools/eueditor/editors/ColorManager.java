package com.eutools.eueditor.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.eutools.eueditor.Activator;

public class ColorManager {

	protected Map<RGB, Color> fColorTable = new HashMap<RGB, Color>(10);
	
	public ColorManager(){
		
	}
	
	public void dispose() {
		Iterator<Color> e = fColorTable.values().iterator();
		while (e.hasNext())
			 (e.next()).dispose();
	}
	public Color getColor(RGB rgb) {
		Color color = fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}
	public Color getColor(String pref_name){
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		RGB rgb = StringConverter.asRGB(store.getString(pref_name));
		Color color = new Color(Display.getCurrent(), rgb);
		fColorTable.put(rgb, color);
		return color;
	}
}
