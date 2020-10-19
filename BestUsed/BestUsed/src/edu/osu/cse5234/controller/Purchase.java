package edu.osu.cse5234.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import edu.osu.cse5234.model.Order;
import edu.osu.cse5234.model.PaymentInfo;
import edu.osu.cse5234.model.ShippingInfo;
import edu.osu.cse5234.util.ServiceLocator;

@Controller
@RequestMapping("/purchase")
public class Purchase {
	
	Order order = new Order();
	
	Purchase(){
		InventoryService inventoryService = ServiceLocator.getInventoryService();
		Inventory inventory = inventoryService.getAvailableInventory();
		order.setItems(inventory.getItems());
	}

	
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Initialize order
		Order order = this.order;
		request.setAttribute("order", order);
		return "OrderEntryForm";
	}

	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		
		if(!OrderProcessingServiceBean.validateItemAvailability(order)) {
			request.setAttribute("alert", "Please resubmit item quantities");
			return "OrderEntryForm";
		}
		
		// remove items that are not selected
		List<Item> items = order.getItems();
		List<Item> orderedItems = new ArrayList<>();
		for(int i=0; i<items.size(); i++) {
			if(Integer.parseInt(items.get(i).getQuantity())!=0) {
				orderedItems.add(items.get(i));
			}
		}
		
		order.setItems(orderedItems);
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
		request.getSession().setAttribute("shippingInfo", shippingInfo);
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
		String code = OrderProcessingServiceBean.processOrder(order);
		request.setAttribute("confirmationNum", code);
		return "Confirmation";
	}
	
	@RequestMapping(value="/checkQuantity", method=RequestMethod.GET)
	@ResponseBody
	public String checkQuantity(@RequestParam(value= "id") Integer id, 
			@RequestParam(value="requiredQuantity") Integer requiredQuantity,
			HttpServletRequest request, HttpServletResponse response){
		
		
		String reservation = order.getItems().get(id).getReservation();
		String res=null;
		if(requiredQuantity>Integer.parseInt(reservation)) {
			res = "The reserved quantity is "+reservation+", less than your requirement";
		}
		return res;
	}

}
