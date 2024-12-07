package org.example.hospitalmanagementsystem.controllers.home;

import org.example.hospitalmanagementsystem.services.NewsService;
import org.example.hospitalmanagementsystem.services.OfferService;
import org.example.hospitalmanagementsystemview.controllers.home.HomeController;
import org.example.hospitalmanagementsystemview.input.NewsSearchForm;
import org.example.hospitalmanagementsystemview.input.OfferSearchForm;
import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.homeview.NewsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.homeview.HomeViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.homeview.OfferViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.lists.NewsListViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.lists.OffersListViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeControllerImpl implements HomeController {

    private NewsService newsService;
    private OfferService offerService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String getHomePage(
            @ModelAttribute("newsForm") NewsSearchForm newsSearchForm,
            @ModelAttribute("offersForm") OfferSearchForm offerSearchForm,
            Model model) {

        var newsPageCount = newsSearchForm.page() != null ? newsSearchForm.page() : 1;
        var newsPageSize = newsSearchForm.size() != null ? newsSearchForm.size() : 5;

        var offerPageCount = offerSearchForm.page() != null ? offerSearchForm.page() : 1;
        var offerPageSize  = offerSearchForm.size() != null ? offerSearchForm.size() : 3;

        var newsForm = new NewsSearchForm("", newsPageCount, newsPageSize);
        var offerForm = new OfferSearchForm("", offerPageCount, offerPageSize);

        var newsPage = newsService.getNews(1, 5);
        var offersPage = offerService.getLastFiveOffers();

        var newsViewModels = newsPage.stream()
                .map(n -> new NewsViewModel(n.getTitle(), n.getDescription(), n.getPublicationDate()))
                .toList();

        var offersViewModels = offersPage.stream()
                .map(o -> new OfferViewModel(o.getTitle(), o.getDescription(), o.getPrice(), o.getImageUrl()))
                .toList();

        var viewModel = new HomeViewModel(
                createBaseViewModel("Домашняя страница"),
                new OffersListViewModel(offersViewModels, 1),
                new NewsListViewModel(newsViewModels, newsPage.getTotalPages())
        );


        model.addAttribute("model", viewModel);
        model.addAttribute("newsForm", newsForm);
        model.addAttribute("offerForm", offerForm);
        return "index";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return null;
    }
}
