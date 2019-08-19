package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OrderMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {

<<<<<<< HEAD
	/**
	 * @param orderNumber
	 * @return
	 */
	OrderMaster findOrderMasterByOrderNumber(@Param("orderNumber")String orderNumber);

}
=======

	OrderMaster findOrderMasterByOrderNumber(@Param("orderNumber")String orderNumber);

}
>>>>>>> 265c600a1e3a29e48d03b5f4ffe7226926c8512e
