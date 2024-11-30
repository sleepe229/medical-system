package org.example.hospitalmanagementsystem.controllers;

import jakarta.validation.Valid;
import jdk.jfr.Registered;
import org.example.hospitalmanagementsystem.dto.*;
import org.example.hospitalmanagementsystem.services.DoctorService;
import org.example.hospitalmanagementsystem.services.NewsService;
import org.example.hospitalmanagementsystem.services.OfferService;
import org.example.hospitalmanagementsystemview.controllers.admin.AdminController;
import org.example.hospitalmanagementsystemview.input.*;
import org.example.hospitalmanagementsystemview.viewmodel.adminview.*;
import org.example.hospitalmanagementsystemview.viewmodel.appointmentview.DoctorViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.details.DoctorDetailsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.details.NewsDetailsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.details.OfferDetailsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.homeview.NewsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.homeview.OfferViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements     AdminController {
    private DoctorService doctorService;
    private OfferService offerService;
    private NewsService newsService;
//todo: check annotations
    @Autowired
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public String adminDashboard(DoctorSearchForm doctorForm, OfferSearchForm offerForm, Model model) {
        //TODO add lists of models
        return "";
    }

    @Override
    @GetMapping("doctor/{id}")
    public String detailsDoctor(Integer id, Model model) {
        var doctor = doctorService.getDoctor(id);
        var viewModel = new DoctorDetailsViewModel(
                createBaseViewModel("искат врач"),
                new DoctorViewModel(doctor.getFullName(), doctor.getPosition(), doctor.getExperienceYears())
        );
        model.addAttribute("viewModel", viewModel);
        //todo: add html
        return "";
    }

    @Override
    @GetMapping("doctor/create")
    public String createDoctorForm(Model model) {
        var viewModel = new DoctorCreateViewModel(
                createBaseViewModel("создат врач")
        );
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("form", new DoctorCreateForm("", "", 0, "", "", "", 0, 0));
        return "doctor-create";
    }

    @Override
    @PostMapping("doctor/create")
    public String createDoctor(@Valid @ModelAttribute("form") DoctorCreateForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new DoctorCreateViewModel(
                    createBaseViewModel("создат врач")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "doctor-create";
        }

        doctorService.createDoctor(new DoctorInputDto(form.fullName(), form.phoneNumber(), form.specializationId(), form.education(), form.hireDate(), form.position(), form.statusId(), form.clinicId()));
        return "redirect:/admin/";
    }
    @Override
    @GetMapping("/doctor/{id}/edit")
    public String editDoctorForm(Integer id, Model model) {
        var doctor = doctorService.getDoctor(id);
        var viewModel = new DoctorEditViewModel(
                createBaseViewModel("менят врача")
        );
        model.addAttribute("model", viewModel);
        //model.addAttribute("form", new DoctorEditForm(doctor.getFullName(), doctor.getPhoneNumber(), doctor.getSpecializationId(), doctor.getEducation(), doctor.getHireDate(), doctor.getPosition(), doctor.getStatusId(), doctor.getClinicId()));
        //todo:fix
        return "doctor-edit";
    }

    @Override
    @PostMapping("/doctor/{id}/edit")
    public String editDoctor(Integer id, DoctorEditForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new DoctorEditViewModel(
                    createBaseViewModel("менят врач")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "doctor-edit";
        }

        doctorService.updateDoctor(new DoctorEditDto(id, form.fullName(), form.phoneNumber(), form.specializationId(), form.education(), form.hireDate(), form.position(), form.statusId(), form.clinicId()));
        return "redirect:/admin/" + id;
    }

    @Override
    public String deleteDoctor(Integer id) {
        //todo:add to change status
        return "";
    }

    @Override
    @GetMapping("/offer/{id}")
    public String detailsOffer(Integer id, Model model) {
        //todo: fix getOffer, method valid
        //var offer = offerService.getOffer(id);
//        var viewModel = new OfferDetailsViewModel(
//                createBaseViewModel("делат скидка"),
//                new OfferViewModel(offer.title(), offer.description(), offer.price())
//        );
//        model.addAttribute("model", viewModel);
        return "offer-details";
    }

    @Override
    @GetMapping("/offer/create")
    public String createOfferForm(Model model) {
        var viewModel = new OfferCreateViewModel(
                createBaseViewModel("создат акцию")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new OfferCreateForm("", "", 1800));
        return "offer-create";
    }

    @Override
    @PostMapping("/offer/create")
    public String createOffer(OfferCreateForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new OfferCreateViewModel(
                    createBaseViewModel("делат скидка")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "offer-create";
        }

        offerService.createOffer(new OfferInputDto(form.offerTitle(), form.offerDescription(), form.offerPrice()));
        return "redirect:/admin/";
    }

    @Override
    @GetMapping("/offer/{id}/edit")
    public String editOfferForm(Integer id, Model model) {
        //todo: fix getoffer
        var offer = offerService.getOfferById(id);
        var viewModel = new OfferEditViewModel(
                createBaseViewModel("менят скидка")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new OfferEditForm(offer.getTitle(), offer.getDescription(), offer.getPrice()));
        return "offer-edit";
    }

    @Override
    @PostMapping("/offer/{id}/edit")
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
        offerService.updateOffer(new OfferEditDto(form.offerTitle(), form.offerDescription(), form.offerPrice());
        return "redirect:/admin";
    }

    @Override
    public String deleteOffer(Integer id) {
        //todo: change status
        return "";
    }

    @Override
    public String detailsNews(Integer id, Model model) {
        //todo: add method at service, return dto
        var news = newsService.getNewsById(id);
        var viewModel = new NewsDetailsViewModel(
                createBaseViewModel("детал новост"),
                new NewsViewModel(news.getTitle(), news.getDescription(), news.getPublicationDate())
        );
        model.addAttribute("model", viewModel);
        return "news-details";
    }

    @Override
    public String createNewsForm(Model model) {
        //todo: add NewsCreatForm at contract
        var viewModel = new NewsCreateViewModel(
                createBaseViewModel("Создание книги")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new NewsCreateForm("", "", ""));
        return "news-create";
    }

    @Override
    public String createNews(NewsCreateForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new NewsCreateViewModel(
                    createBaseViewModel("Создание книги")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "book-create";
        }

        newsService.createNews(new NewsInputDto(form.title(), form.descriptin(), form.publicationDate()));
        return "redirect:/admin";
    }

    @Override
    @GetMapping("/news/{id}/edit")
    public String editNewsForm(@PathVariable Integer id, Model model) {
        //todo: fix method
        var news = newsService.getNewsById(id);
        var viewModel = new NewsEditViewModel(
                createBaseViewModel("Редактирование новости")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new NewsEditForm(news.getTitle(), news.getDescription(), news.getPublicationDate()));
        return "news-edit";  // View for editing the news.
    }

    @Override
    @PostMapping("/news/{id}/edit")
    public String editNews(@PathVariable Integer id, @Valid @ModelAttribute("form") NewsEditForm form,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new NewsEditViewModel(
                    createBaseViewModel("Редактирование новости")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "news-edit";
        }

        newsService.updateNews(new NewsEditDto(id, form.title(), form.description(), form.publicationDate()));
        return "redirect:/admin";
    }

    @Override
    public String deleteNews(Integer id) {
        return "";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(
                title, "admin"
        );
    }
}
