package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OrderMaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {

	/**
	 * @param orderNumber
	 * @return
	 */
	OrderMaster findOrderMasterByOrderNumber(@Param("orderNumber")String orderNumber);
	
	Optional<OrderMaster> findByOrderNumber(String orderNumber);

}

