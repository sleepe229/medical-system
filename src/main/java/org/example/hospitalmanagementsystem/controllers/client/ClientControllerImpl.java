package org.example.hospitalmanagementsystem.controllers.client;

import jakarta.validation.Valid;
import org.example.hospitalmanagementsystem.dto.AppointmentInputDto;
import org.example.hospitalmanagementsystem.services.AppointmentService;
import org.example.hospitalmanagementsystem.services.DoctorService;
import org.example.hospitalmanagementsystemview.controllers.client.ClientController;
import org.example.hospitalmanagementsystemview.input.*;
import org.example.hospitalmanagementsystemview.viewmodel.appointmentview.AppointmentCreateViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.clientview.AppointmentViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.lists.AppointmentListViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/account")
public class ClientControllerImpl implements ClientController {

    private DoctorService doctorService;
    private AppointmentService appointmentService;
//    private ClientService clientService;

    @Autowired
    public void setDoctorService(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @Override
    @GetMapping
    public String listAppointments(
            @ModelAttribute("form") AppointmentSearchForm form,
                                   Model model) {
//        var doctor = doctorService.getDoctor(doctorId);

        String appointmentSearchTerm = form != null && form.searchTerm() != null ? form.searchTerm() : "";
        int page = form != null && form.page() != null ? form.page() : 1;
        int size = form != null && form.size() != null ? form.size() : 3;
        form = new AppointmentSearchForm(appointmentSearchTerm, page, size);

        var currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        assert currentUser instanceof User;
        var appointmentPage = appointmentService.getAppointmentsForClient(((User) currentUser).getUsername(), page, size);
        var appointmentViewModels = appointmentPage.stream()
                .map(appointment -> new AppointmentViewModel(appointment.getId(), appointment.getPhoneNumber(), appointment.getDirection(), appointment.getClinic(), appointment.getDoctorFullName(), appointment.getResult())).toList();

        var appointmentViewModel = new AppointmentListViewModel(
                appointmentViewModels, appointmentPage.getTotalPages()
        );

        model.addAttribute("model", appointmentViewModel);
        model.addAttribute("form", form);

        return "client-appointment";
    }

//    @Override
//    public String listAnalyzes(String id, AnalysisListViewModel analysisListViewModel, Model model) {
//        return "";
//    }

    @Override
    public String editAccountForm(Integer id, Model model) {
        return "";
    }

    @Override
    public String editAccount(Integer id, UserEditForm form, BindingResult bindingResult, Model model) {
        return "";
    }

    @Override
    @GetMapping("/appointment/create")
    public String createAppointmentForm(Model model) {
        var viewModel = new AppointmentCreateViewModel(
                createBaseViewModel("создат заявка")
        );
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("form", new AppointmentCreateForm("", 0, 0, 0, LocalDate.now().toString()));
        return "client-appointment-create";
    }

    @Override
    @PostMapping("/appointment/create")
    public String createAppointment(@Valid @ModelAttribute("form") AppointmentCreateForm form,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new AppointmentCreateViewModel(
                    createBaseViewModel("создат запис к врач")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "client-appointment-create";
        }
//        String phoneNumber;
//        Integer specializationId;
//        Integer clinicId;
//        Integer doctorId;
//        String date;
        appointmentService.createAppointment(new AppointmentInputDto(form.phoneNumber(), form.specializationId(), form.clinicId(), form.doctorId(), form.date()));
        return "redirect:/account";
    }

    @Override
    public String createAccount(UserCreateForm form, BindingResult bindingResult, Model model) {
        return "";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return null;
    }
}
