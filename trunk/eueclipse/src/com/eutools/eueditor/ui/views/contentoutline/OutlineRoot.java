package com.eutools.eueditor.ui.views.contentoutline;

import java.util.ArrayList;
import java.util.List;

public class OutlineRoot {
	private int id = 0;
	private List<OutlineRoot> children = new ArrayList<OutlineRoot>();
	
	public void addChild(OutlineRoot node){
		if (node != null){
			children.add(node);
		}
	}
	
	public void removeChild(OutlineRoot node){
		if (node != null){
			children.remove(node);
		}
	}
	
	public void removeAllChildren(){
		children.clear();
	}

	public String toString(){
		return String.valueOf(id);
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public OutlineRoot[] getChildrenAsArray(){
		OutlineRoot [] childArray = null;
		if (children != null && children.size() > 0){
			childArray = new OutlineRoot[children.size()];
			for (int i=0;i<children.size();i++){
				childArray[i] = children.get(i);
			}
		}
		else{
			childArray = new OutlineRoot[0];
		}
		return childArray;
	}
	
	public int getId(){
		return this.id;
	}
}
