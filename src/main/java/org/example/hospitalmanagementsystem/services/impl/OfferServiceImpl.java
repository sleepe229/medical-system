package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.OfferDto;
import org.example.hospitalmanagementsystem.dto.OfferEditDto;
import org.example.hospitalmanagementsystem.dto.OfferInputDto;
import org.example.hospitalmanagementsystem.entities.Offer;
import org.example.hospitalmanagementsystem.repository.OfferRepo;
import org.example.hospitalmanagementsystem.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<OfferDto> getLastFiveOffers() {
        List<Offer> offerList = offerRepo.findFiveLastAdded();
        offerList = offerList.size() > 5 ? offerList.subList(0, 5) : offerList;
        return offerList.stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());
    }


    @Override
    public Page<OfferDto> getOffers(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Offer> offerPage = offerRepo.findAll(pageable);
        return offerPage.map(offers -> modelMapper.map(offers, OfferDto.class));
    }

    @Override
    public Page<OfferDto> getOffers(String searchTerm, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("title"));
        Page<Offer> booksPage = (searchTerm != null && !searchTerm.isEmpty())
                ? offerRepo.findByTitle(searchTerm, pageable)
                : offerRepo.findAll(pageable);
        return booksPage.map(offer -> new OfferDto(offer.getTitle(), offer.getDescription(), offer.getPrice(), offer.getImageUrl()));
    }
}
