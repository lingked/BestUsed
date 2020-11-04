package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.Order;

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
    
    @PersistenceContext
	EntityManager entityManager;
    
    String MY_QUERY = "SELECT i FROM Item i";
    
    @Override
    public Inventory getAvailableInventory() {
    	Inventory inventory = new Inventory();
    	
    	List<Item> items = new ArrayList<>();
    	
    	items = entityManager.createQuery(MY_QUERY, Item.class).getResultList();
		
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
	
	@Override
	public EntityManager getEM() {
		System.out.println(entityManager);
		EntityManager em = entityManager;
		return em;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

}
