package org.example.hospitalmanagementsystem.services;

import org.example.hospitalmanagementsystem.dto.OfferDto;
import org.example.hospitalmanagementsystem.dto.OfferEditDto;
import org.example.hospitalmanagementsystem.dto.OfferInputDto;
import org.example.hospitalmanagementsystem.entities.Offer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OfferService {
    void createOffer(OfferInputDto offerDto);
    void updateOffer(OfferEditDto offerDto);
    OfferDto getOfferById(Integer id);
    List<OfferDto> getLastFiveOffers();
    Page<OfferDto> getOffers(int page, int size);
    Page<OfferDto> getOffers(String searchTerm, int page, int size);
}
