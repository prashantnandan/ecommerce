package com.wipro.bankofamerica.estore.factory;

import com.wipro.bankofamerica.estore.service.AccessoriesService;

public class Mobile implements AccessoriesService {

	@Override
	public String getSpecificProduct() {
		return "mobile";
	}

}
