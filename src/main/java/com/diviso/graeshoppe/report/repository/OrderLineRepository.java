package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OrderLine;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

	//@Query(value="SELECT ol FROM OrderLine ol where ol.OrderMaster.orderName = :id")
	//ResponseEntity<OrderLine> findByOrderLineOrderMaster(@Param("id") String orderId);
	
	List<OrderLine> findByOrderMaster(Long id);

}
