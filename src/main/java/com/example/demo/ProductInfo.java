package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductInfo {

	@RequestMapping("/productInfo")
	public String GetProductInfo() {
		// TODO Auto-generated constructor stub
		return "This is product info from application.";
	}

}
