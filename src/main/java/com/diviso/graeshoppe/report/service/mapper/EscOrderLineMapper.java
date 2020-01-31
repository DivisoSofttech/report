package com.diviso.graeshoppe.report.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.diviso.graeshoppe.report.domain.EscOrderLine;
import com.diviso.graeshoppe.report.domain.OrderLine;
import com.diviso.graeshoppe.report.service.dto.OrderLineDTO;



	/**
	 * Mapper for the entity {@link OrderLine} and its DTO {@link OrderLineDTO}.
	 */
	@Mapper(componentModel = "spring", uses = {OrderMasterMapper.class})
	public interface EscOrderLineMapper extends EntityMapper<OrderLineDTO, EscOrderLine> {

		@Mapping(source = "orderMaster.id", target = "orderMasterId")
	    OrderLineDTO toDto(EscOrderLine orderLine);

	    @Mapping(target = "auxItems", ignore = true)
	 
	    @Mapping(target = "comboItems", ignore = true)
	   
	    @Mapping(source = "orderMasterId", target = "orderMaster")
	    EscOrderLine toEscOrderLine(OrderLineDTO orderLineDTO);

	    default OrderLine fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        OrderLine orderLine = new OrderLine();
	        orderLine.setId(id);
	        return orderLine;
	    }
	
}
