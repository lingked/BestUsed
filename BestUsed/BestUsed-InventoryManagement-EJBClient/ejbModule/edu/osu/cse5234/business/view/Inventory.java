package edu.osu.cse5234.business.view;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements java.io.Serializable {
	
	private static final long serialVersionUID = -7792306855414923185L;
	List<Item> Items = new ArrayList<>();
	
	public Inventory() {
		
	}

	public List<Item> getItems() {
		return Items;
	}

	public void setItems(List<Item> items) {
		Items = items;
	}
	
	
}
