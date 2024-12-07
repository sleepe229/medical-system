//package org.example.hospitalmanagementsystem.controllers.client;
//
//import jakarta.validation.Valid;
//import org.example.hospitalmanagementsystem.services.AppointmentService;
//import org.example.hospitalmanagementsystem.services.DoctorService;
//import org.example.hospitalmanagementsystemview.controllers.client.ClientController;
//import org.example.hospitalmanagementsystemview.input.AppointmentBookingForm;
//import org.example.hospitalmanagementsystemview.input.UserCreateForm;
//import org.example.hospitalmanagementsystemview.input.UserEditForm;
//import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.lists.AnalysisListViewModel;
//import org.example.hospitalmanagementsystemview.viewmodel.lists.AppointmentListViewModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//@Controller
//public class ClientControllerImpl implements ClientController {
//
//    private DoctorService doctorService;
//    private AppointmentService appointmentService;
////    private ClientService clientService;
//
//    @Autowired
//    public void setDoctorService(DoctorService doctorService){
//        this.doctorService = doctorService;
//    }
//
//    @Autowired
//    public void setAppointmentService(AppointmentService appointmentService){
//        this.appointmentService = appointmentService;
//    }
//
//    @Override
//    public String listAppointments(String id, AppointmentListViewModel appointmentListViewModel, Model model) {
//        return "";
//    }
//
//    @Override
//    public String listAnalyzes(String id, AnalysisListViewModel analysisListViewModel, Model model) {
//        return "";
//    }
//
//    @Override
//    public String editAccountForm(String id, Model model) {
//        return "";
//    }
//
//    @Override
//    public String editAccount(String id, UserEditForm form, BindingResult bindingResult, Model model) {
//        return "";
//    }
//
//    @Override
//    public String createAppointment(@Valid @ModelAttribute("form") AppointmentBookingForm form,
//                                    BindingResult bindingResult,
//                                    Model model) {
////        if (bindingResult.hasErrors()) {
////            var viewModel = new AppointmentBookingViewModel(
////                    createBaseViewModel("Вызов врача на дом"), doctorService.getDoctorsByClinicAndSpecialization(0, 0, 5, 3))
////            );
////        model.addAttribute("form", form);
////
////        return null;
////        }
////
////        appointmentService.createAppointment();
//        return "redirect:/home/";
//    }
//
//    @Override
//    public String createAccount(UserCreateForm form, BindingResult bindingResult, Model model) {
//        return "";
//    }
//
//    @Override
//    public BaseViewModel createBaseViewModel(String title) {
//        return null;
//    }
//}
