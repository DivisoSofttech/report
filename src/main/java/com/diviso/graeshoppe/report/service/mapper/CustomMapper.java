package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderMaster and its DTO OrderMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomMapper extends EntityMapper<OrderMaster, EscPosDocket> {


    //@Mapping(target = "orderLines", ignore = true)
    @Mapping(target = "offerLines", ignore = true)
    EscPosDocket toEntity(OrderMaster orderMaster);

    default EscPosDocket fromId(Long id) {
        if (id == null) {
    		System.out.println("/////ghhhhhhhhhhhhhhhhh///");

            return null;
        }
        EscPosDocket escPosDocket = new EscPosDocket();
        escPosDocket.setId(id);
        return escPosDocket;
    }
}