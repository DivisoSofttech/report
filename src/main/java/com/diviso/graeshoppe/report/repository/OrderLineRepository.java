package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OrderLine;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

	//@Query(value="SELECT ol FROM OrderLine ol where ol.OrderMaster.orderName = :id")
		//ResponseEntity<OrderLine> findByOrderLineOrderMaster(@Param("id") String orderId);
		
		List<OrderLine> findByOrderMaster_OrderNumber(String id);


		Set<OrderLine> findByOrderMaster_OrderNumber(Long id);

		Set<OrderLine> findByOrderMasterId(Long id);
	
}
