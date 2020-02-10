package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.domain.ReportOrderModel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

/**
 * Spring Data  repository for the OrderMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {

Optional<OrderMaster> findByOrderNumber(String orderNumber);

	

	/*
	 * Long countByOrderPlaceAtBetweenAndStoreName(Instant dateBegin, Instant
	 * dateEnd, String storeName);
	 * 
	 * 
	 * @Query(value =
	 * "SELECT COALESCE(sum(c.totalDue),0) FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName "
	 * ) Double sumOfTotalDue(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String
	 * storeName);
	 * 
	 * @Query(value =
	 * "SELECT COUNT(c) FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.methodOfOrder LIKE CONCAT('%',:deliveryType,'%')"
	 * ) Integer countByMethodOfOrderAndStoreName(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String
	 * storeName, @Param("deliveryType") String deliveryType);
	 * 
	 * 
	 * @Query(value =
	 * "SELECT COALESCE(sum(c.totalDue),0) FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.methodOfOrder LIKE CONCAT('%',:deliveryType,'%')"
	 * ) Double sumOfTotalByOrderType(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String
	 * storeName, @Param("deliveryType") String deliveryType);
	 * 
	 * 
	 * @Query(value =
	 * "SELECT COUNT(c) FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.paymentStatus LIKE CONCAT('%',:paymentStatus,'%')"
	 * ) Integer countByPaymentStatusAndStoreName(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String
	 * storeName, @Param("paymentStatus") String paymentStatus);
	 * 
	 * 
	 * @Query(value =
	 * "SELECT COALESCE(sum(c.totalDue),0)FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.paymentStatus LIKE CONCAT('%',:paymentStatus,'%')"
	 * ) Double sumOftotalByPaymentStatus(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("storeName")String
	 * storeName, @Param("paymentStatus") String paymentStatus);
	 */


Page<OrderMaster> findByorderPlaceAtBetweenAndStoreIdpcode(Instant dateBegin, Instant dateEnd, String storeIdpcode,Pageable pageable);

	public Long countByOrderStatus(String orderStatus);
	public Long  countByOrderPlaceAtBetweenAndOrderStatus(Instant from,Instant to,String orderStatus);
	
	
	Page<OrderMaster> findByExpectedDeliveryBetween(Instant from, Instant to, Pageable pageable);



	Long countByOrderPlaceAtBetween(Instant fromDate, Instant toDate);


	//List<OrderMaster> findByOrderPlaceAtBetweenAndStoreIdpcode1(Instant dateBegin, Instant dateEnd, String storeId);



	Page<OrderMaster> findByOrderPlaceAtBetweenAndStoreIdpcodeAndMethodOfOrder(Instant dateBegin, Instant dateEnd,
			String storeIdpcode, String methodOfOrder, Pageable pageable);



	Page<OrderMaster> findByOrderPlaceAtBetweenAndStoreIdpcodeAndPaymentStatus(Instant dateBegin, Instant dateEnd,
			String storeIdpcode, String paymentStatus, Pageable pageable);



	Page<OrderMaster> findByOrderPlaceAtBetween(Instant dateBegin, Instant dateEnd, Pageable pageble);



	Page<OrderMaster> findByOrderPlaceAtBetweenAndStoreIdpcode(Instant dateBegin, Instant dateEnd, String storeId, Pageable pageable);



	Page<OrderMaster> findByExpectedDeliveryBetweenAndStoreIdpcode(Instant fromDate, Instant toDate,
			String storeIdpcode, Pageable pageable);


	Page<OrderMaster> findByOrderPlaceAtBetweenAndPaymentStatus(Instant dateBegin, Instant dateEnd,
			String paymentStatus, Pageable pageable);



	Page<OrderMaster> findByOrderPlaceAtBetweenAndMethodOfOrder(Instant dateBegin, Instant dateEnd,
			String methodOfOrder, Pageable pageable);



	Page<OrderMaster> findByOrderPlaceAtBetweenAndPaymentStatusAndMethodOfOrder(Instant dateBegin, Instant dateEnd,
			String paymentStatus, String methodOfOrder, Pageable pageable);


	Page<OrderMaster> findByOrderPlaceAtBetweenAndStoreIdpcodeAndPaymentStatusAndMethodOfOrder(Instant dateBegin,
			Instant dateEnd, String storeIdpcode, String paymentStatus, String methodOfOrder, Pageable pageable);


	/*
	 * @Query(value =
	 * "SELECT COALESCE(sum(c.totalDue),0) FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd"
	 * ) Double sumOfTotalDue(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd);
	 * 
	 * 
	 * @Query(value =
	 * "SELECT COUNT(c) FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd  AND  c.methodOfOrder LIKE CONCAT('%',:deliveryType,'%')"
	 * ) Integer countByMethodOfOrder(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("deliveryType") String
	 * deliveryType);
	 * 
	 * 
	 * @Query(value =
	 * "SELECT COALESCE(sum(c.totalDue),0) FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND  c.methodOfOrder LIKE CONCAT('%',:deliveryType,'%')"
	 * ) Double sumOfTotalByOrderType(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("deliveryType") String
	 * deliveryType);
	 * 
	 * 
	 * @Query(value =
	 * "SELECT COUNT(c) FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND  c.paymentStatus LIKE CONCAT('%',:paymentStatus,'%')"
	 * ) Integer countByPaymentStatus(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("paymentStatus") String
	 * paymentStatus);
	 * 
	 * 
	 * @Query(value =
	 * "SELECT COALESCE(sum(c.totalDue),0)FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND  c.paymentStatus LIKE CONCAT('%',:paymentStatus,'%')"
	 * ) Double sumOftotalByPaymentStatus(@Param("dateBegin")Instant
	 * dateBegin, @Param("dateEnd")Instant dateEnd, @Param("paymentStatus") String
	 * paymentStatus);
	 * 
	 * 
	 */

	List<OrderMaster> findByOrderPlaceAtBetweenAndStoreIdpcode(Instant dateBegin, Instant dateEnd, String storeName);



	List<OrderMaster> findByOrderPlaceAtBetween(Instant dateBegin, Instant dateEnd);



	List<OrderMaster> findByOrderPlaceAtBetweenAndStoreIdpcodeAndMethodOfOrderAndPaymentStatus(Instant dateBegin,
			Instant dateEnd, String storeName, String methodOfOrder, String paymentStatus);

	
	/*
	 * @Query(value
	 * ="SELECT COALESCE(sum(c.totalDue),0)FROM OrderMaster c  WHERE c.orderPlaceAt BETWEEN :dateBegin AND :dateEnd AND c.storeName=:storeName AND  c.paymentStatus LIKE CONCAT('%',:paymentStatus,'%')"
	 * ) Double
	 * sumOftotalByPaymentStatus(@Param("dateBegin")InstantdateBegin, @Param(
	 * "dateEnd")Instant dateEnd, @Param("storeName")String
	 * storeName, @Param("paymentStatus") String paymentStatus);
	 */
}
