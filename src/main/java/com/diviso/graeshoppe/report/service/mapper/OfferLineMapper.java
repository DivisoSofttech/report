package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.OfferLineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OfferLine and its DTO OfferLineDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderMasterMapper.class})
public interface OfferLineMapper extends EntityMapper<OfferLineDTO, OfferLine> {

    @Mapping(source = "orderMaster.id", target = "orderMasterId")
    OfferLineDTO toDto(OfferLine offerLine);

    @Mapping(source = "orderMasterId", target = "orderMaster")
    OfferLine toEntity(OfferLineDTO offerLineDTO);

    default OfferLine fromId(Long id) {
        if (id == null) {
            return null;
        }
        OfferLine offerLine = new OfferLine();
        offerLine.setId(id);
        return offerLine;
    }
}
