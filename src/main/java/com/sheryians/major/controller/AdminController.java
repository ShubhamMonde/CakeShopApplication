package com.sheryians.major.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.ProductDTO;
import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;


@Controller
public class AdminController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome.html";
	}
	
	
				//opration perform on categories by admin started
	
	
	//to save new category in database
	@GetMapping("/admin/categories/add")
	public String getCategoriesAdd(Model model) {
		model.addAttribute("category" ,new Category());
		return "categoriesAdd.html";
	}
	//to add new category
	//to display the added category list
	@PostMapping("/admin/categories/add")
	public String postCategoriesAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	//to display all details  stored in database
		@GetMapping("/admin/categories")
		public String getCategories(Model model) {
			model.addAttribute("categories", categoryService.getAllCategory());
			return "categories.html";
		}
		
		//to delete mapping we have to first find id
		@GetMapping("/admin/categories/delete/{id}")
		public String deleteCategory(@PathVariable int id) {// to fetch id's value we use @PatheVariable
			categoryService.removeCategoryById(id);
			return "redirect:/admin/categories";
		}
		
		//to update the record we firstly we have to fetch id
		@GetMapping("/admin/categories/update/{id}")
		public String updateCategory(@PathVariable int id, Model model){
			Optional<Category> category=categoryService.getCategoryById(id);
			if(category.isPresent()) {
				model.addAttribute("category",category.get());//get() method Object मधील data fetch करतो.
				return "categoriesAdd.html";
			}else
				return "404";
		}
		
		

						//opration perform on categories by admin ended


						//opration perform on product by admin started
		public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";//part09 2.59
		@Autowired
		ProductService productService;
		
		@GetMapping("/admin/products")
		public String products(Model model) {
			model.addAttribute("products", productService.getAllProduct());
			return "products";
		}
		
		@GetMapping("/admin/products/add")
		public String productAddGet(Model model) {
			model.addAttribute("productDTO", new ProductDTO());//we are sending empty ProductDTO object in form
			model.addAttribute("categories", categoryService.getAllCategory());
			//we are sending this categories also because we can select category that is already present in our database.
			return "productsAdd";
		}
		
		//Part 08
		@PostMapping("/admin/products/add")
		public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
									@RequestParam("productImage")MultipartFile file,
									@RequestParam("imgName") String imgName) throws IOException {
			Product product=new Product();
			product.setId(productDTO.getId());
			product.setName(productDTO.getName());
			product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
			product.setPrice(productDTO.getPrice());
			product.setWeight(productDTO.getWeight());
			product.setDescription(productDTO.getDescription());
			String imageUUID;
			if(!file.isEmpty()) {
				imageUUID=file.getOriginalFilename();
				Path fileNameAndpath=Paths.get(uploadDir, imageUUID);//used to create file path and name
				Files.write(fileNameAndpath, file.getBytes());//used to give file name, path and actual file
			}else {
				imageUUID= imgName;
			}
			product.setImageName(imageUUID);
			productService.addProduct(product);
			return "redirect:/admin/products";
			
		}
		
		//to delete product
		@GetMapping("/admin/product/delete/{id}")
		public String deleteProduct(@PathVariable Long id) {
			productService.removeProductById(id);
			return "redirect:/admin/products";
		}
		
		@GetMapping("/admin/product/update/{id}")
		public String updateProductGet(@PathVariable Long id, Model model) {
			Product product= productService.getProductById(id).get();
			ProductDTO productDTO= new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setCategoryId(product.getCategory().getId());
			productDTO.setPrice(product.getPrice());
			productDTO.setWeight(product.getWeight());
			productDTO.setDescription(product.getDescription());
			productDTO.setImageName(product.getImageName());
			
			model.addAttribute("categories",categoryService.getAllCategory());
			model.addAttribute("productDTO",productDTO);
			
			return "productsAdd";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
