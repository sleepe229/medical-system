package org.example.hospitalmanagementsystem.controllers.admin;

import jakarta.validation.Valid;
import org.example.hospitalmanagementsystem.dto.OfferEditDto;
import org.example.hospitalmanagementsystem.dto.OfferInputDto;
import org.example.hospitalmanagementsystem.services.OfferService;
import org.example.hospitalmanagementsystemview.controllers.admin.AdminOfferController;
import org.example.hospitalmanagementsystemview.input.OfferCreateForm;
import org.example.hospitalmanagementsystemview.input.OfferEditForm;
import org.example.hospitalmanagementsystemview.input.OfferSearchForm;
import org.example.hospitalmanagementsystemview.viewmodel.adminview.OfferCreateViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.adminview.OfferEditViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.details.OfferDetailsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.homeview.OfferViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.lists.OffersListViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/offer")
public class AdminOfferControllerImpl implements AdminOfferController {

    private OfferService offerService;

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    @GetMapping
    public String listOffers(@ModelAttribute("offerForm") OfferSearchForm offerForm, Model model) {

        String offerSearchTerm = offerForm != null && offerForm.searchTerm() != null ? offerForm.searchTerm() : "";
        int offerPage = offerForm != null && offerForm.page() != null ? offerForm.page() : 1;
        int offerSize = offerForm != null && offerForm.size() != null ? offerForm.size() : 3;
        offerForm = new OfferSearchForm(offerSearchTerm, offerPage, offerSize);

        var offersPage = offerService.getOffers(offerSearchTerm, offerPage, offerSize);
        var offerViewModels = offersPage.stream()
                .map(offer -> new OfferViewModel(offer.getTitle(), offer.getDescription(), offer.getPrice(), offer.getImageUrl()))
                .toList();

        var offerViewModel = new OffersListViewModel(
                offerViewModels, offersPage.getTotalPages()
        );

        model.addAttribute("model", offerViewModel);
        model.addAttribute("offerForm", offerForm);

        return "offer-list";
    }

    @Override
    @GetMapping("/{id}")
    public String detailsOffer(Integer id, Model model) {
        var offer = offerService.getOfferById(id);
        var viewModel = new OfferDetailsViewModel(
                createBaseViewModel("делат скидка"),
                new OfferViewModel(offer.getTitle(), offer.getDescription(), offer.getPrice(), offer.getImageUrl())
        );
        model.addAttribute("model", viewModel);
        return "offer-details";
    }

    @Override
    @GetMapping("/create")
    public String createOfferForm(Model model) {
        var viewModel = new OfferCreateViewModel(
                createBaseViewModel("создат акцию")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new OfferCreateForm("", "", 1800, ""));
        return "offer-create";
    }

    @Override
    @PostMapping("/create")
    public String createOffer(OfferCreateForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new OfferCreateViewModel(
                    createBaseViewModel("делат скидка")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "offer-create";
        }

        offerService.createOffer(new OfferInputDto(form.offerTitle(), form.offerDescription(), form.offerPrice(), form.imageUrl()));
        return "redirect:/admin/offer";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editOfferForm(Integer id, Model model) {
        //todo: fix getoffer
        var offer = offerService.getOfferById(id);
        var viewModel = new OfferEditViewModel(
                createBaseViewModel("менят скидка")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new OfferEditForm(offer.getTitle(), offer.getDescription(), offer.getPrice(), offer.getImageUrl()));
        return "offer-edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String editOffer(@PathVariable Integer id, @Valid @ModelAttribute("form") OfferEditForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new OfferEditViewModel(
                    createBaseViewModel("менят скидка")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "offer-edit";
        }
        //todo:check id OfferEditDto
        offerService.updateOffer(new OfferEditDto(form.offerTitle(), form.offerDescription(), form.offerPrice(), form.imageUrl()));
        return "redirect:/admin/offer";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return null;
    }
}
