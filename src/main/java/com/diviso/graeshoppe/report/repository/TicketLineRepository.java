package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.TicketLine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TicketLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketLineRepository extends JpaRepository<TicketLine, Long> {

}
