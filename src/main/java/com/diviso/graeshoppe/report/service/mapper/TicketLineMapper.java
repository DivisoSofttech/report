package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.TicketLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TicketLine} and its DTO {@link TicketLineDTO}.
 */
@Mapper(componentModel = "spring", uses = {SaleMapper.class})
public interface TicketLineMapper extends EntityMapper<TicketLineDTO, TicketLine> {

    @Mapping(source = "sale.id", target = "saleId")
    TicketLineDTO toDto(TicketLine ticketLine);

    @Mapping(source = "saleId", target = "sale")
    TicketLine toEntity(TicketLineDTO ticketLineDTO);

    default TicketLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        TicketLine ticketLine = new TicketLine();
        ticketLine.setId(id);
        return ticketLine;
    }
}
