package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
public class InventoryServiceBean implements InventoryService {

    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public Inventory getAvailableInventory() {
    	Inventory inventory = new Inventory();
    	List<Item> items = new ArrayList<>();
		Item item1 = new Item("basketball", "15.00", "0", "images/basketball.png", "5");
		Item item2 = new Item("IPhone", "500.00", "0", "images/iphone.jpg", "6");
		Item item3 = new Item("chair", "10.00", "0", "images/chair.jpg", "7");
		Item item4 = new Item("desk", "25.00", "0", "images/desk.jpg", "8");
		Item item5 = new Item("Mac", "1000.00", "0", "images/mac.jpg", "9");
		Item item6 = new Item("Lamp", "10.00", "0","images/lamp.jpg", "10");
		
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		
		inventory.setItems(items);
		return inventory;
    };
	
    @Override
	public boolean validateQuantity(List<Item> items) {
		return true;
	};
	
	@Override
	public boolean updateInventory(List<Item> items) {
		return true;
	};

}
