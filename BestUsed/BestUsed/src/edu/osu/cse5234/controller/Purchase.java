package edu.osu.cse5234.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.osu.cse5234.business.OrderProcessingServiceBean;
import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;
import edu.osu.cse5234.model.LineItem;
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.model.ShippingInfo;
import edu.osu.cse5234.util.ServiceLocator;

@Controller
@RequestMapping("/purchase")
public class Purchase {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Initialize order
		
		InventoryService inventoryService = ServiceLocator.getInventoryService();
		Inventory inventory = inventoryService.getAvailableInventory();
		
		Order order = new Order();
		ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
		for(int i=0; i<inventory.getItems().size(); i++) {
			lineItems.add(new LineItem());
		}
		
		order.setLineItems(lineItems);
		request.setAttribute("inventory", inventory);
		request.setAttribute("order", order);
		
		return "OrderEntryForm";
	}

	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		OrderProcessingServiceBean orderProcessingServiceBean = ServiceLocator.getOrderProcessingService();
		if(!orderProcessingServiceBean.validateItemAvailability(order)) {
			request.setAttribute("alert", "Please resubmit item quantities");
			return "OrderEntryForm";
		}
		
		// remove items that are not selected
		List<LineItem> lineItems = order.getLineItems();
		List<LineItem> orderedItems = new ArrayList<>();
		for(int i=0; i<lineItems.size(); i++) {
			if(lineItems.get(i).getQuantity()!=0) {
				orderedItems.add(lineItems.get(i));
			}
		}
		
		order.setLineItems(orderedItems);
		// Store the order in session
		request.getSession().setAttribute("order", order);
		return "redirect:/purchase/paymentEntry";
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryForm(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("paymentInfo", new PaymentInfo());	
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo, HttpServletRequest request) {
		// store payment information in session
		request.getSession().setAttribute("paymentInfo", paymentInfo);
		Order order = (Order) request.getSession().getAttribute("order");
		order.setPaymentInfo(paymentInfo);
		request.setAttribute("order", order);
		return "redirect:/purchase/shippingEntry"; 
	}
	
	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String viewShippingEntryForm(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("shippingInfo", new ShippingInfo());	
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
	public String submitShipping(@ModelAttribute("shippingInfo") ShippingInfo shippingInfo, HttpServletRequest request) {
		// store shipping information in session
		Order order = (Order) request.getSession().getAttribute("order");
		order.setShippinginfo(shippingInfo);
		order.setCustomerName(shippingInfo.getName());
		order.setEmailAddress(shippingInfo.getEmail());
		request.getSession().setAttribute("shippingInfo", shippingInfo);
		request.getSession().setAttribute("order", order);
		return "redirect:/purchase/viewOrder";
	}
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request, HttpServletResponse response) {
		
		return "ViewOrder";
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request) {
		
		return "redirect:/purchase/viewConfirmation";
	}
	
	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmation(HttpServletRequest request, HttpServletResponse response) {
		// Give user the confirmation number/code
		Order order = (Order) request.getSession().getAttribute("order");
		OrderProcessingServiceBean orderProcessingServiceBean = ServiceLocator.getOrderProcessingService();
		String code = orderProcessingServiceBean.processOrder(order);
		request.setAttribute("confirmationNum", code);
		return "Confirmation";
	}
	
	@RequestMapping(value="/checkQuantity", method=RequestMethod.GET)
	@ResponseBody
	public String checkQuantity(@RequestParam(value= "id") Integer id, 
			@RequestParam(value="requiredQuantity") Integer requiredQuantity,
			HttpServletRequest request, HttpServletResponse response){
		
		InventoryService inventoryService = ServiceLocator.getInventoryService();
		Inventory inventory = inventoryService.getAvailableInventory();
		
		int reservation = inventory.getItems().get(id).getReservation();
		String res=null;
		if(requiredQuantity>reservation) {
			res = "The reserved quantity is "+reservation+", less than your requirement";
		}
		return res;
	}

}
