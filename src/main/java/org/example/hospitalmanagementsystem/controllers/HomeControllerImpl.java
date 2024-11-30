package org.example.hospitalmanagementsystem.controllers;

import org.example.hospitalmanagementsystem.services.NewsService;
import org.example.hospitalmanagementsystem.services.OfferService;
import org.example.hospitalmanagementsystemview.controllers.home.HomeController;
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
    public String getHomePage(Model model) {

        var newsPage = newsService.getAllNews(1, 5);
        var offersPage = offerService.getOffers(1, 3);

        var newsViewModels = newsPage.stream()
                .map(n -> new NewsViewModel(n.getTitle(), n.getDescription(), n.getPublicationDate()))
                .toList();

        var offersViewModels = offersPage.stream()
                .map(o -> new OfferViewModel(o.getTitle(), o.getDescription(), o.getPrice()))
                .toList();

        var viewModel = new HomeViewModel(
                createBaseViewModel("Домашняя страница"),
                new OffersListViewModel(offersViewModels),
                new NewsListViewModel(newsViewModels)
        );


        model.addAttribute("model", viewModel);
        return "index";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return null;
    }
}
