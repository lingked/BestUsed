package edu.osu.cse5234.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;
import edu.osu.cse5234.model.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.util.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */

@Stateless
@LocalBean
public class OrderProcessingServiceBean {

    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
	EntityManager entityManager;
    
    public String processOrder(Order order) {
    	
    	if(!validateItemAvailability(order)) {
    		return "Error";
    	}
    	
    	entityManager.persist(order);
    	entityManager.flush();
    	
    	return "SE163JI99846";
    }
    
    public boolean validateItemAvailability(Order order) {
    	InventoryService inventoryService = ServiceLocator.getInventoryService();
    	Inventory inventory = inventoryService.getAvailableInventory();
    	List<Item> availableItems = inventory.getItems();
    	List<LineItem> orderItems = order.getLineItems();
    	for(int i=0, j=0; i<availableItems.size(); i++) {
    		if(availableItems.get(i).getItemNumber()==orderItems.get(j).getItemNumber()) {
    			if(availableItems.get(i).getReservation() < orderItems.get(j).getQuantity()) {
    				return false;
    			}
    			j++;
    			if(j==orderItems.size()) {
    				return true;
    			}
    		}
    	}
    	return true;
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
    
    

}
