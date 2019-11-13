package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OrderMaster;

import java.time.Instant;
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

	Optional<OrderMaster> findByOrderNumber(String orderNumber);

	

	Long countByExpectedDeliveryBetweenAndStoreName(Instant dateBegin, Instant dateEnd, String storeName);


	@Query(value = "SELECT sum(c.totalDue) FROM OrderMaster c  WHERE c.expectedDelivery BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName ")
	Double sumOfTotalDue(@Param("dateBegin")Instant dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String storeName);

	@Query(value = "SELECT COUNT(c) FROM OrderMaster c  WHERE c.expectedDelivery BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.methodOfOrder LIKE CONCAT('%',:deliveryType,'%')")
	Integer countByMethodOfOrderAndStoreName(@Param("dateBegin")Instant dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String storeName, @Param("deliveryType") String deliveryType);


	@Query(value = "SELECT COALESCE(sum(c.totalDue),0) FROM OrderMaster c  WHERE c.expectedDelivery BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.methodOfOrder LIKE CONCAT('%',:deliveryType,'%')")
	Double sumOfTotalByOrderType(@Param("dateBegin")Instant dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String storeName, @Param("deliveryType") String deliveryType);


	@Query(value = "SELECT COUNT(c) FROM OrderMaster c  WHERE c.expectedDelivery BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.orderStatus LIKE CONCAT('%',:orderStatus,'%')")
	Integer countByOrderStatusAndStoreName(@Param("dateBegin")Instant dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String storeName, @Param("orderStatus") String orderStatus);


	@Query(value = "SELECT COALESCE(sum(c.totalDue),0)FROM OrderMaster c  WHERE c.expectedDelivery BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.orderStatus LIKE CONCAT('%',:orderStatus,'%')")
	Double sumOftotalByOrderStatus(@Param("dateBegin")Instant dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String storeName, @Param("orderStatus") String orderStatus);
	



}
