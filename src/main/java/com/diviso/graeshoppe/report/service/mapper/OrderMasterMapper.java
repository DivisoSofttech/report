package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderMaster and its DTO OrderMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMasterMapper extends EntityMapper<OrderMasterDTO, OrderMaster> {


    @Mapping(target = "orderLines", ignore = true)
    @Mapping(target = "offerLines", ignore = true)
    OrderMaster toEntity(OrderMasterDTO orderMasterDTO);

    default OrderMaster fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setId(id);
        return orderMaster;
    }
}
