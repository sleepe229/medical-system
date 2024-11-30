package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.NewsDto;
import org.example.hospitalmanagementsystem.dto.OfferDto;
import org.example.hospitalmanagementsystem.dto.OfferEditDto;
import org.example.hospitalmanagementsystem.dto.OfferInputDto;
import org.example.hospitalmanagementsystem.entities.News;
import org.example.hospitalmanagementsystem.entities.Offer;
import org.example.hospitalmanagementsystem.repository.OfferRepo;
import org.example.hospitalmanagementsystem.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepo offerRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepo offerRepo, ModelMapper modelMapper) {
        this.offerRepo = offerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createOffer(OfferInputDto offerInputDto) {
        Offer offer = modelMapper.map(offerInputDto, Offer.class);
        offerRepo.save(offer);
    }

    @Override
    public void updateOffer(OfferEditDto offerEditDto) {
        Offer updatedOffer = offerRepo.findById(offerEditDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Offer with ID " + offerEditDto.getId() + " not found"));
        offerRepo.save(updatedOffer);
    }

    @Override
    public OfferDto getOfferById(Integer id) {
        Offer offer = offerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Скидка с ID " + id + " не найдена"));
        return modelMapper.map(offer, OfferDto.class);
    }


    @Override
    public Page<OfferDto> getOffers(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Offer> offerPage = offerRepo.findAll(pageable);
        return offerPage.map(offers -> modelMapper.map(offers, OfferDto.class));
    }

}
