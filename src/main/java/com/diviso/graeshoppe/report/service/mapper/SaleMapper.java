package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.SaleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sale and its DTO SaleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SaleMapper extends EntityMapper<SaleDTO, Sale> {


    @Mapping(target = "ticketLines", ignore = true)
    Sale toEntity(SaleDTO saleDTO);

    default Sale fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sale sale = new Sale();
        sale.setId(id);
        return sale;
    }
}
