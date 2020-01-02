package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.OrderLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderLine} and its DTO {@link OrderLineDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrderMasterMapper.class})
public interface OrderLineMapper extends EntityMapper<OrderLineDTO, OrderLine> {

    @Override
	@Mapping(source = "orderMaster.id", target = "orderMasterId")
    OrderLineDTO toDto(OrderLine orderLine);

    @Override
	@Mapping(target = "auxItems", ignore = true)
    @Mapping(target = "removeAuxItem", ignore = true)
    @Mapping(target = "comboItems", ignore = true)
    @Mapping(target = "removeComboItem", ignore = true)
    @Mapping(source = "orderMasterId", target = "orderMaster")
    OrderLine toEntity(OrderLineDTO orderLineDTO);

    default OrderLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setId(id);
        return orderLine;
    }
}
