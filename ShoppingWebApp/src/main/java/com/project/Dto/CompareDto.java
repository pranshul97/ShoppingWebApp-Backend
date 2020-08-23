package com.project.Dto;

import java.util.ArrayList;
import java.util.List;

public class CompareDto {
	
	List<Integer> productsId = new ArrayList<>();

	public List<Integer> getProductsId() {
		return productsId;
	}

	public void setProductsId(List<Integer> productsId) {
		this.productsId = productsId;
	}
	
	public int arrayListSize() {
		return productsId.size();
	}
	
	public int fetchElementValue(int index){
		return productsId.get(index);
	}

}
