package com.sheryians.major.dto;

import lombok.Data;

//here there is no need to create table of these class. so we never user @Entity annotation
@Data
public class ProductDTO {
	private Long id;
	
	private String name;
	
	private int categoryId;
    //we can't handle this object directly in view.So we have to create dto. dto(Data Transefer Object) it is just holder.
	
	private Double price;
	
	private Double weight;
	
	private String description;
	
	private String imageName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
