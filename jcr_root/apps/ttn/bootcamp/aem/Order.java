package apps.ttn.bootcamp.aem;

import javax.script.Bindings;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.scripting.sightly.pojo.Use;
import java.util.*;
import org.apache.sling.api.resource.*;

public class Order implements Use {
		private String path;
		private String item;
		private String orderNo;
		private Integer quantity;
		private Integer price;
		private Integer total;

		Order(Resource resource) {
			path = resource.getPath();
			ValueMap valueMap = resource.getValueMap();
			orderNo = valueMap.get("title").toString();
			quantity = Integer.parseInt(valueMap.get("quantity").toString());
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

		public Integer getQuantity() {
			return quantity;
		}

		public String getItem() {
			return item;
		}

		public Integer getTotal() {
			return total;
		}

		public Integer getPath() {
			return path;
		}
	}