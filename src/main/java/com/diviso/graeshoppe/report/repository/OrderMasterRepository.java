package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OrderMaster;
import com.diviso.graeshoppe.report.service.dto.OrderMasterDTO;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {


	OrderMaster findOrderMasterByOrderNumber(@Param("orderNumber")String orderNumber);

}