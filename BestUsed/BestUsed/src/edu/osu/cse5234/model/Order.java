package edu.osu.cse5234.model;

import java.util.ArrayList;
import java.util.List;



public class Order {
	List<edu.osu.cse5234.business.view.Item> Items;
	public Order () {
		this.Items = new ArrayList<>();
	}
	public List<edu.osu.cse5234.business.view.Item> getItems() {
		return this.Items;
	}
	public void setItems(List<edu.osu.cse5234.business.view.Item> list) {
		this.Items = list;
	};
}
