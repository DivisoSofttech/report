package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.AuxItem;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AuxItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuxItemRepository extends JpaRepository<AuxItem, Long> {

//	@Query("SELECT ai FROM AuxItem ai WHERE ai.id = :id")
//	List<AuxItem> findByOrderLine_id(@Param ("id") Long id);

}
