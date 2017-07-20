package apps.ttn.bootcamp.aem;

import javax.script.Bindings;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.scripting.sightly.pojo.Use;
import java.util.*;
import org.apache.sling.api.resource.*;

public class ModelBean implements Use {
	public enum DrinkType {
		CAPPUCCINO("Cappuccino", 30), LATTE("Latte", 20), TEA("Tea", 10);

		private final String name;
		private final Integer price;

		private DrinkType(String name, Integer price) {
			this.name = name;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public Integer getPrice() {
			return price;
		}

		static List<DrinkType> getAll() {
			return Arrays.asList(CAPPUCCINO, LATTE, TEA);
		}

		static DrinkType findByName(String name) {
			DrinkType drinkType = null;
			for (DrinkType drink : getAll()) {
				if (name.equalsIgnoreCase(drink.getName())) {
					drinkType = drink;
				}
			}
			return drinkType;
		}
	}

	public class Order {
		private String path;
		private String item;
		private String orderNo;
		private Integer quantity;
		private Integer price;
		private Integer total;
		private String status;

		Order(Resource resource) {
			path = resource.getPath();
			ValueMap valueMap = resource.getValueMap();
			orderNo = valueMap.get("title").toString();
			quantity = Integer.parseInt(valueMap.get("quantity").toString());
			status = valueMap.get("status").toString();
			DrinkType drinkType = DrinkType.findByName(valueMap.get("item").toString());
			item = drinkType.getName();
			price = drinkType.getPrice();
			total = price * quantity;
		}

		public Integer getPrice() {
			return price;
		}

		public String getOrderNo() {
			return orderNo;
		}
		
		public String getStatus() {
			return status;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public String getItem() {
			return item;
		}

		public Integer getTotal() {
			return total;
		}

		public String getPath() {
			return path;
		}
	}

	private List<DrinkType> drinkTypeList;
	private List<Order> pendingOrders;
	private List<Order> orders;
	private Long currentOrder;
	private Order order;

	@Override
	public void init(Bindings bindings) {
		Resource resource = (Resource)bindings.get("resource");
		this.drinkTypeList = DrinkType.getAll();
		this.currentOrder = PropertiesUtil.toLong(bindings.get("currentOrder"), System.currentTimeMillis());
		if(!"order".equalsIgnoreCase(resource.getName()) && !"records".equalsIgnoreCase(resource.getName()))
			this.order = new Order(resource);
		else if ("records".equalsIgnoreCase(resource.getName())) {
			Iterator<Resource> children = resource.listChildren();
			this.orders = new ArrayList();
			this.pendingOrders = new ArrayList<>();
			while (children.hasNext()) {
				Order order = new Order(children.next());
				orders.add(order);
				if(order.status.equalsIgnoreCase("pending")) {
					pendingOrders.add(order);
				}
			}
		}
	}
	
	public Long getCurrentOrder() {
		return currentOrder;
	}
	
	public List<DrinkType> getDrinkTypeList() {
		return drinkTypeList;
	}

	public List<Order> getPendingOrders() {
	    return pendingOrders;
    }
	
	public List<Order> getOrders() {
	    return orders;
    }
	
	public Order getOrder() {
		return order;
	}
}