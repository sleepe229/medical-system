package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Offer;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepo extends BaseCRUDRepo<Offer, Integer> {
//    Page<Offer> findByStatus(String status, Pageable pageable);
    Page<Offer> findByTitle(String title, Pageable pageable);
    @Query("SELECT o FROM Offer o ORDER BY o.id DESC")
    List<Offer> findFiveLastAdded();

}
