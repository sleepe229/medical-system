package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Offer;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfferRepo extends BaseCRUDRepo<Offer, Integer> {
//    Page<Offer> findByStatus(String status, Pageable pageable);
}
