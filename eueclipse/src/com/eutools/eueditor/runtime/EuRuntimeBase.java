package com.eutools.eueditor.runtime;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class EuRuntimeBase implements IEURuntime {

	protected Map<String, String> properties = new HashMap<String, String>();
	protected static RuntimeVersion version = new RuntimeVersion(0, 0, 0);
	
	public EuRuntimeBase (){
		properties = new HashMap<String, String>();
	}

	@Override
	public String setProperty(String name, String value) {
		return properties.put(name, value);
	}

	@Override
	public String getProperty(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperties(Map<String, String> properties) {
		this.properties.putAll(properties);
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> props = new HashMap<String, String>();
		if (properties != null && properties.size() > 0){
			Iterator<String> iter = properties.keySet().iterator();
			while (iter.hasNext()){
				String key = new String(iter.next());
				props.put(key, new String(properties.get(key)));
			}
		}
		return props;
	}
}
