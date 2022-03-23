package com.wipro.bankofamerica.estore.factory;

import com.wipro.bankofamerica.estore.exception.EStoreException;
import com.wipro.bankofamerica.estore.service.AccessoriesService;

public class AccessoriesFactory {

	public static AccessoriesService getAccessoriesType(String type) {
		// camera, mobile, laptop
		if (type == null) {
			return null;
		} else if (type.equalsIgnoreCase("camera")) {  //camera.equalsIgnoreCase("camear");
			return new Camera();  //return the object
		} else if (type.equalsIgnoreCase("mobile")) {
			return new Mobile();
		}else if(type.equalsIgnoreCase("laptop")) {
			return new Laptop();
		}
		throw new EStoreException("Invalid input");
	}
}
