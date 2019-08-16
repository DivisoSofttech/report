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
package com.diviso.graeshoppe.report.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diviso.graeshoppe.report.service.OrderLineService;
import com.diviso.graeshoppe.report.service.OrderMasterService;
import com.diviso.graeshoppe.report.service.dto.OrderLine;
import com.diviso.graeshoppe.report.service.dto.OrderLineDTO;
import com.diviso.graeshoppe.report.service.dto.OrderMaster;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;
import com.diviso.graeshoppe.report.web.rest.errors.BadRequestAlertException;
import com.diviso.graeshoppe.report.web.rest.util.HeaderUtil;

/**
 * TODO Provide a detailed description here
 * 
 * @author MayaSanjeev mayabytatech, maya.k.k@lxisoft.com
 */

@RestController
@RequestMapping("/api")
public class ReportCommandResource {

	@Autowired
	private OrderLineService orderLineService;

	@Autowired
	private OrderMasterService orderMasterService;

	@PostMapping("/ordermasters")
	public ResponseEntity<OrderMasterDTO> createOrderMaster(@RequestBody OrderMaster orderMaster)
			throws URISyntaxException {

		OrderMasterDTO master = new OrderMasterDTO();

		master.setAddressType(orderMaster.getAddressType());
		master.setAlternatePhone(orderMaster.getAlternatePhone());
		master.setPhone(orderMaster.getPhone());
		master.setCity(orderMaster.getCity());
		master.setHouseNoOrBuildingName(orderMaster.getHouseNoOrBuildingName());
		master.setLandmark(orderMaster.getLandmark());
		master.setName(orderMaster.getName());
		master.setPincode(orderMaster.getPincode());
		master.setRoadNameAreaOrStreet(orderMaster.getRoadNameAreaOrStreet());
		master.setState(orderMaster.getState());
		master.setCustomerId(orderMaster.getCustomerId());
		master.setDeliveryCharge(orderMaster.getDeliveryCharge());
		master.setCustomerOrder(orderMaster.getCustomersOrder());
		master.setDueDate(orderMaster.getDueDate());
		master.setDueTime(orderMaster.getDueTime());
		master.setMethodOfOrder(orderMaster.getMethodOfOrder());
		master.setNotes(orderMaster.getNotes());
		master.setTotalDue(orderMaster.getTotalDue());
		master.setOrderAcceptedAt(orderMaster.getOrderAcceptedAt());
		master.setServiceCharge(orderMaster.getServiceCharge());
		master.setStorePhone(orderMaster.getPhone());
		master.setOrderStatus(orderMaster.getOrderStatus());
		master.setOrderNumber(orderMaster.getOrderNumber());
		master.setOrderFromCustomer(orderMaster.getOrderFromCustomer());
		master.setStoreName(orderMaster.getStoreName());
		master.setOrderPlaceAt(orderMaster.getOrderPlaceAt());

		if (master.getId() != null) {
			throw new BadRequestAlertException("A new orderMaster cannot already have an ID", "orderMaster",
					"idexists");
		}

		OrderMasterDTO result = orderMasterService.save(master);

		List<OrderLine> orderLines = orderMaster.getOrderLine();

		orderLines.forEach(orderLine -> {

			OrderLineDTO orderDTO = new OrderLineDTO();

			orderDTO.setItem(orderLine.getItem());
			
			orderDTO.setQuantity(orderLine.getQuantity());
			
			orderDTO.setTotal(orderLine.getTotal());
			
			orderDTO.setOrderMasterId(result.getId());

			if (orderDTO.getId() != null) {
				throw new BadRequestAlertException("A new orderLine cannot already have an ID", "orderline",
						"idexists");
			}
			OrderLineDTO result1 = orderLineService.save(orderDTO);

		});

		return ResponseEntity.ok().body(result);
	}

	@PutMapping("/ordermasters")
	public ResponseEntity<OrderMasterDTO> updateOrderMaster(@RequestBody OrderMaster orderMaster)
			throws URISyntaxException {

		OrderMasterDTO master = new OrderMasterDTO();

		master.setAddressType(orderMaster.getAddressType());
		master.setAlternatePhone(orderMaster.getAlternatePhone());
		master.setPhone(orderMaster.getPhone());
		master.setCity(orderMaster.getCity());
		master.setHouseNoOrBuildingName(orderMaster.getHouseNoOrBuildingName());
		master.setLandmark(orderMaster.getLandmark());
		master.setName(orderMaster.getName());
		master.setPincode(orderMaster.getPincode());
		master.setRoadNameAreaOrStreet(orderMaster.getRoadNameAreaOrStreet());
		master.setState(orderMaster.getState());
		master.setCustomerId(orderMaster.getCustomerId());
		master.setDeliveryCharge(orderMaster.getDeliveryCharge());
		master.setCustomerOrder(orderMaster.getCustomersOrder());
		master.setDueDate(orderMaster.getDueDate());
		master.setDueTime(orderMaster.getDueTime());
		master.setMethodOfOrder(orderMaster.getMethodOfOrder());
		master.setNotes(orderMaster.getNotes());
		master.setTotalDue(orderMaster.getTotalDue());
		master.setOrderAcceptedAt(orderMaster.getOrderAcceptedAt());
		master.setServiceCharge(orderMaster.getServiceCharge());
		master.setStorePhone(orderMaster.getPhone());
		master.setOrderStatus(orderMaster.getOrderStatus());
		master.setOrderNumber(orderMaster.getOrderNumber());
		master.setOrderFromCustomer(orderMaster.getOrderFromCustomer());
		master.setStoreName(orderMaster.getStoreName());
		master.setOrderPlaceAt(orderMaster.getOrderPlaceAt());

		if (master.getId() == null) {
			throw new BadRequestAlertException("A order is new so no id", "orderMaster",
					"id not exists");
		}

		OrderMasterDTO result = orderMasterService.save(master);

		List<OrderLine> orderLines = orderMaster.getOrderLine();

		orderLines.forEach(orderLine -> {

			OrderLineDTO orderDTO = new OrderLineDTO();

			orderDTO.setItem(orderLine.getItem());
			
			orderDTO.setQuantity(orderLine.getQuantity());
			
			orderDTO.setTotal(orderLine.getTotal());
			
			orderDTO.setOrderMasterId(result.getId());

			if (orderDTO.getId() == null) {
				throw new BadRequestAlertException("A orderLine is new so no id", "orderline",
						"id not exists");
			}
			OrderLineDTO result1 = orderLineService.save(orderDTO);

		});

		return ResponseEntity.ok().body(result);
	}

	
}
