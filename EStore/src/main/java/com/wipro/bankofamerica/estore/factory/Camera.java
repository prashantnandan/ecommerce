package com.wipro.bankofamerica.estore.factory;

import com.wipro.bankofamerica.estore.service.AccessoriesService;

public class Camera implements AccessoriesService {

	@Override
	public String getSpecificProduct() {
		return "camera";
	}

}
