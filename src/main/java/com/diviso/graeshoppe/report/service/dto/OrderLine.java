 /*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diviso.graeshoppe.report.service.dto;

import java.util.List;

/**
 * TODO Provide a detailed description here 
 * @author MayaSanjeev
 * mayabytatech, maya.k.k@lxisoft.com
 */
public class OrderLine {
	
	  private List<AuxItem> auxItems ;

	
	  private List<ComboItem> combos ;

	  private String item ;

	 
	  private Integer quantity ;


	  private Double total;


	public List<AuxItem> getAuxItems() {
		return auxItems;
	}


	public void setAuxItems(List<AuxItem> auxItems) {
		this.auxItems = auxItems;
	}


	public List<ComboItem> getCombos() {
		return combos;
	}


	public void setCombos(List<ComboItem> combos) {
		this.combos = combos;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Double getTotal() {
		return total;
	}


	@Override
	public String toString() {
		return "OrderLine [auxItems=" + auxItems + ", combos=" + combos + ", item=" + item + ", quantity=" + quantity
				+ ", total=" + total + "]";
	}


	public void setTotal(Double total) {
		this.total = total;
	}

	  
	  
}
