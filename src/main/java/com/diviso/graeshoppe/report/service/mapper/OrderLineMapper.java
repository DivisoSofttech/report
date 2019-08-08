package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.OrderLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderLine and its DTO OrderLineDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderMasterMapper.class})
public interface OrderLineMapper extends EntityMapper<OrderLineDTO, OrderLine> {

    @Mapping(source = "orderMaster.id", target = "orderMasterId")
    OrderLineDTO toDto(OrderLine orderLine);

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
