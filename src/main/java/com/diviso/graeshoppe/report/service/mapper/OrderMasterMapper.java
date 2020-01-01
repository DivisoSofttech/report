package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderMaster} and its DTO {@link OrderMasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMasterMapper extends EntityMapper<OrderMasterDTO, OrderMaster> {


    @Mapping(target = "orderLines", ignore = true)
    @Mapping(target = "removeOrderLine", ignore = true)
    @Mapping(target = "offerLines", ignore = true)
    @Mapping(target = "removeOfferLines", ignore = true)
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
