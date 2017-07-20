package apps.ttn.bootcamp.aem;

import javax.script.Bindings;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.scripting.sightly.pojo.Use;
import java.util.*;
import org.apache.sling.api.resource.*;

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
