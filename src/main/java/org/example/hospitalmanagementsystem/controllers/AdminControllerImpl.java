//package org.example.hospitalmanagementsystem.controllers;
//
//import jakarta.validation.Valid;
//import org.example.hospitalmanagementsystem.dto.*;
//import org.example.hospitalmanagementsystem.services.DoctorService;
//import org.example.hospitalmanagementsystem.services.NewsService;
//import org.example.hospitalmanagementsystem.services.OfferService;
//import org.example.hospitalmanagementsystemview.controllers.admin.AdminController;
//import org.example.hospitalmanagementsystemview.input.*;
//import org.example.hospitalmanagementsystemview.viewmodel.adminview.*;
//import org.example.hospitalmanagementsystemview.viewmodel.appointmentview.DoctorViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.details.DoctorDetailsViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.details.NewsDetailsViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.details.OfferDetailsViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.homeview.NewsViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.homeview.OfferViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.lists.DoctorListViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.lists.NewsListViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.lists.OffersListViewModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminControllerImpl implements AdminController {
//    private DoctorService doctorService;
//    private OfferService offerService;
//    private NewsService newsService;
////todo: check annotations
//    @Autowired
//    public void setDoctorService(DoctorService doctorService) {
//        this.doctorService = doctorService;
//    }
//
//    @Autowired
//    public void setOfferService(OfferService offerService) {
//        this.offerService = offerService;
//    }
//
//    @Autowired
//    public void setNewsService(NewsService newsService) {
//        this.newsService = newsService;
//    }
//
//    @Override
//    @GetMapping
//    public String adminDashboard(@ModelAttribute("doctorForm") DoctorSearchForm doctorForm,
//                                 @ModelAttribute("offerForm") OfferSearchForm offerForm,
//                                 @ModelAttribute("newsForm") NewsSearchForm newsForm,
//                                 Model model) {
//        String offerSearchTerm = offerForm != null && offerForm.searchTerm() != null ? offerForm.searchTerm() : "";
//        int offerPage = offerForm != null && offerForm.page() != null ? offerForm.page() : 1;
//        int offerSize = offerForm != null && offerForm.size() != null ? offerForm.size() : 3;
//        offerForm = new OfferSearchForm(offerSearchTerm, offerPage, offerSize);
//
//        String doctorSearchTerm = doctorForm != null && doctorForm.searchTerm() != null ? doctorForm.searchTerm() : "";
//        int doctorPage = doctorForm != null && doctorForm.page() != null ? doctorForm.page() : 1;
//        int doctorSize = doctorForm != null && doctorForm.size() != null ? doctorForm.size() : 3;
//        doctorForm = new DoctorSearchForm(doctorSearchTerm, doctorPage, doctorSize);
//
//        String newsSearchTerm = newsForm != null && newsForm.searchTerm() != null ? newsForm.searchTerm() : "";
//        int newsPage = newsForm != null && newsForm.page() != null ? newsForm.page() : 1;
//        int newsSize = newsForm != null && newsForm.size() != null ? newsForm.size() : 3;
//        newsForm = new NewsSearchForm(newsSearchTerm, newsPage, newsSize);
//
//        var offersPage = offerService.getOffers(offerSearchTerm, offerPage, offerSize);
//        var offerViewModels = offersPage.stream()
//                .map(offer -> new OfferViewModel(offer.getTitle(), offer.getDescription(), offer.getPrice(), offer.getImageUrl()))
//                .toList();
//
//        var doctorsPage = doctorService.getDoctors(doctorSearchTerm, doctorPage, doctorSize);
//        var doctorViewModels = doctorsPage.stream()
//                .map(doctor -> new DoctorViewModel(doctor.getFullName(), doctor.getPosition(), doctor.getExperienceYears()))
//                .toList();
//
//        var newssPage = newsService.getNews(newsSearchTerm, newsPage, newsSize);
//        var newsViewModels = newssPage.stream()
//                .map(news -> new NewsViewModel(news.getTitle(), news.getDescription(), news.getPublicationDate()))
//                .toList();
//
//        var offerViewModel = new OffersListViewModel(
//                offerViewModels, offersPage.getTotalPages()
//        );
//
//        var doctorViewModel = new DoctorListViewModel(
//                doctorViewModels, doctorsPage.getTotalPages()
//        );
//
//        var newsViewModel = new NewsListViewModel(
//                newsViewModels, newssPage.getTotalPages()
//        );
//
//        var viewModel = new AdminViewModel(
//                createBaseViewModel("админка")
//        );
//
//        model.addAttribute("model", viewModel);
//        model.addAttribute("doctorForm", doctorForm);
//        model.addAttribute("newsForm", newsForm);
//        model.addAttribute("offerForm", offerForm);
//        return "admin-past";
//    }
//
//
//    @Override
//    @GetMapping("doctor/{id}")
//    public String detailsDoctor(Integer id, Model model) {
//        var doctor = doctorService.getDoctor(id);
//        var viewModel = new DoctorDetailsViewModel(
//                createBaseViewModel("искат врач"),
//                new DoctorViewModel(doctor.getFullName(), doctor.getPosition(), doctor.getExperienceYears())
//        );
//        model.addAttribute("viewModel", viewModel);
//        //todo: add html
//        return "doctor-details";
//    }
//
//    @Override
//    @GetMapping("doctor/create")
//    public String createDoctorForm(Model model) {
//        var viewModel = new DoctorCreateViewModel(
//                createBaseViewModel("создат врач")
//        );
//        model.addAttribute("viewModel", viewModel);
//        model.addAttribute("form", new DoctorCreateForm("", "", 0, "", "", "", 0, 0));
//        return "doctor-create";
//    }
//
//    @Override
//    @PostMapping("doctor/create")
//    public String createDoctor(@Valid @ModelAttribute("form") DoctorCreateForm form,
//                         BindingResult bindingResult,
//                         Model model) {
//        if (bindingResult.hasErrors()) {
//            var viewModel = new DoctorCreateViewModel(
//                    createBaseViewModel("создат врач")
//            );
//            model.addAttribute("model", viewModel);
//            model.addAttribute("form", form);
//            return "doctor-create";
//        }
//
//        doctorService.createDoctor(new DoctorInputDto(form.fullName(), form.phoneNumber(), form.specializationId(), form.education(), form.hireDate(), form.position(), form.statusId(), form.clinicId()));
//        return "redirect:/admin/";
//    }
//    @Override
//    @GetMapping("/doctor/{id}/edit")
//    public String editDoctorForm(Integer id, Model model) {
//        var doctor = doctorService.getDoctor(id);
//        var viewModel = new DoctorEditViewModel(
//                createBaseViewModel("менят врача")
//        );
//        model.addAttribute("model", viewModel);
////        model.addAttribute("form", new DoctorEditForm(doctor.getFullName(), doctor.getPhoneNumber(), doctor.getSpecializationId(), doctor.getEducation(), doctor.getHireDate(), doctor.getPosition(), doctor.getStatusId(), doctor.getClinicId()));
////        todo:fix
//        return "doctor-edit";
//    }
//
//    @Override
//    @PostMapping("/doctor/{id}/edit")
//    public String editDoctor(Integer id, DoctorEditForm form, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            var viewModel = new DoctorEditViewModel(
//                    createBaseViewModel("менят врач")
//            );
//            model.addAttribute("model", viewModel);
//            model.addAttribute("form", form);
//            return "doctor-edit";
//        }
//
//        doctorService.updateDoctor(new DoctorEditDto(id, form.fullName(), form.phoneNumber(), form.specializationId(), form.education(), form.hireDate(), form.position(), form.statusId(), form.clinicId()));
//        return "redirect:/admin/" + id;
//    }
//
//    @Override
//    @GetMapping("/offer/{id}")
//    public String detailsOffer(Integer id, Model model) {
//        var offer = offerService.getOfferById(id);
//        var viewModel = new OfferDetailsViewModel(
//                createBaseViewModel("делат скидка"),
//                new OfferViewModel(offer.getTitle(), offer.getDescription(), offer.getPrice(), offer.getImageUrl())
//        );
//        model.addAttribute("model", viewModel);
//        return "offer-details";
//    }
//
//    @Override
//    @GetMapping("/offer/create")
//    public String createOfferForm(Model model) {
//        var viewModel = new OfferCreateViewModel(
//                createBaseViewModel("создат акцию")
//        );
//        model.addAttribute("model", viewModel);
//        model.addAttribute("form", new OfferCreateForm("", "", 1800, ""));
//        return "offer-create";
//    }
//
//    @Override
//    @PostMapping("/offer/create")
//    public String createOffer(OfferCreateForm form, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            var viewModel = new OfferCreateViewModel(
//                    createBaseViewModel("делат скидка")
//            );
//            model.addAttribute("model", viewModel);
//            model.addAttribute("form", form);
//            return "offer-create";
//        }
//
//        offerService.createOffer(new OfferInputDto(form.offerTitle(), form.offerDescription(), form.offerPrice(), form.imageUrl()));
//        return "redirect:/admin/";
//    }
//
//    @Override
//    @GetMapping("/offer/{id}/edit")
//    public String editOfferForm(Integer id, Model model) {
//        //todo: fix getoffer
//        var offer = offerService.getOfferById(id);
//        var viewModel = new OfferEditViewModel(
//                createBaseViewModel("менят скидка")
//        );
//        model.addAttribute("model", viewModel);
//        model.addAttribute("form", new OfferEditForm(offer.getTitle(), offer.getDescription(), offer.getPrice(), offer.getImageUrl()));
//        return "offer-edit";
//    }
//
//    @Override
//    @PostMapping("/offer/{id}/edit")
//    public String editOffer(@PathVariable Integer id, @Valid @ModelAttribute("form") OfferEditForm form, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            var viewModel = new OfferEditViewModel(
//                    createBaseViewModel("менят скидка")
//            );
//            model.addAttribute("model", viewModel);
//            model.addAttribute("form", form);
//            return "offer-edit";
//        }
//        //todo:check id OfferEditDto
//        offerService.updateOffer(new OfferEditDto(form.offerTitle(), form.offerDescription(), form.offerPrice(), form.imageUrl()));
//        return "redirect:/admin";
//    }
//
//    @Override
//    @GetMapping("news/{id}")
//    public String detailsNews(Integer id, Model model) {
//        var news = newsService.getNewsById(id);
//        var viewModel = new NewsDetailsViewModel(
//                createBaseViewModel("детал новост"),
//                new NewsViewModel(news.getTitle(), news.getDescription(), news.getPublicationDate())
//        );
//        model.addAttribute("model", viewModel);
//        return "news-details";
//    }
//
//    @Override
//    @GetMapping("news/create")
//    public String createNewsForm(Model model) {
//        var viewModel = new NewsCreateViewModel(
//                createBaseViewModel("Создание книги")
//        );
//        model.addAttribute("model", viewModel);
//        model.addAttribute("form", new NewsCreateForm("", ""));
//        return "news-create";
//    }
//
//    @Override
//    @PostMapping("news/create")
//    public String createNews(NewsCreateForm form, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            var viewModel = new NewsCreateViewModel(
//                    createBaseViewModel("создат новаст")
//            );
//            model.addAttribute("model", viewModel);
//            model.addAttribute("form", form);
//            return "news-create";
//        }
//
//        newsService.createNews(new NewsInputDto(form.title(), form.description()));
//        return "redirect:/admin";
//    }
//
//    @Override
//    @GetMapping("/news/{id}/edit")
//    public String editNewsForm(@PathVariable Integer id, Model model) {
//        var news = newsService.getNewsById(id);
//        var viewModel = new NewsEditViewModel(
//                createBaseViewModel("менят новаст")
//        );
//        model.addAttribute("model", viewModel);
//        model.addAttribute("form", new NewsEditForm(news.getTitle(), news.getDescription(), news.getPublicationDate()));
//        return "news-edit";
//    }
//
//    @Override
//    @PostMapping("/news/{id}/edit")
//    public String editNews(@PathVariable Integer id, @Valid @ModelAttribute("form") NewsEditForm form,
//                           BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            var viewModel = new NewsEditViewModel(
//                    createBaseViewModel("Редактирование новости")
//            );
//            model.addAttribute("model", viewModel);
//            model.addAttribute("form", form);
//            return "news-edit";
//        }
//
//        newsService.updateNews(new NewsEditDto(id, form.title(), form.description(), form.publicationDate()));
//        return "redirect:/admin";
//    }
//
//    @Override
//    public BaseViewModel createBaseViewModel(String title) {
//        return new BaseViewModel(title);
//    }
//}
