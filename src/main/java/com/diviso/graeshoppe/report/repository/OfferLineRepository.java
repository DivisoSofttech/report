package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OfferLine;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OfferLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferLineRepository extends JpaRepository<OfferLine, Long> {

	//List<OfferLine> findByOrderMasterId(Long id);

	List<OfferLine> findByOrderMaster_OrderNumber(String orderId);

	/*
	 * @Query("SELECT offerLine FROM OfferLine offLine WHERE offerLine.orderMaster.orderName = :orderName"
	 * ) List<OfferLine> findByOrderMaster_orderNumber(@Param ("orderName") String
	 * orderId);
	 */
}
