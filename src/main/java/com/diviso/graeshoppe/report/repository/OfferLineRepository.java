package com.diviso.graeshoppe.report.repository;

import com.diviso.graeshoppe.report.domain.OfferLine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OfferLine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferLineRepository extends JpaRepository<OfferLine, Long> {

}
