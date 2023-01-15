package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/","/home"})
	public String home(Model model) {//we are using here model because to count the cart
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index.html";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {//here we display all categories and products
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "shop.html";
	}
	
	

	//it provide list of product of same category
	//जेव्हा आपण flipcart वर Samsung चा filter लावतो तेव्हा आपल्याला फक्त Samsung चे mobile दिसतात.तसचं जी category आपण select करणार फक्त त्याच category चे product आपल्याला दिसनार
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductByCategoryId(id));
		return "shop.html";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable Long id) {
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "viewProduct.html";
	}
	
	
	
	
	
	
	
	
	
	
}
