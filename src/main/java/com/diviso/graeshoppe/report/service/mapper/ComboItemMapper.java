package com.diviso.graeshoppe.report.service.mapper;

import com.diviso.graeshoppe.report.domain.*;
import com.diviso.graeshoppe.report.service.dto.ComboItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ComboItem and its DTO ComboItemDTO.
 */
@Mapper(componentModel = "spring", uses = {OrderLineMapper.class})
public interface ComboItemMapper extends EntityMapper<ComboItemDTO, ComboItem> {

    @Mapping(source = "orderLine.id", target = "orderLineId")
    ComboItemDTO toDto(ComboItem comboItem);

    @Mapping(source = "orderLineId", target = "orderLine")
    ComboItem toEntity(ComboItemDTO comboItemDTO);

    default ComboItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        ComboItem comboItem = new ComboItem();
        comboItem.setId(id);
        return comboItem;
    }
}
