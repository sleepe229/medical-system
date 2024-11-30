package org.example.hospitalmanagementsystem.services;

import org.example.hospitalmanagementsystem.dto.OfferDto;
import org.example.hospitalmanagementsystem.dto.OfferEditDto;
import org.example.hospitalmanagementsystem.dto.OfferInputDto;
import org.springframework.data.domain.Page;

public interface OfferService {
    void createOffer(OfferInputDto offerDto);
    void updateOffer(OfferEditDto offerDto);
    OfferDto getOfferById(Integer id);
    Page<OfferDto> getOffers(int page, int size);
}
