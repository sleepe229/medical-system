package org.example.hospitalmanagementsystem.controllers.doctor;

import org.example.hospitalmanagementsystem.dto.AppointmentEditDto;
import org.example.hospitalmanagementsystem.services.AppointmentService;
import org.example.hospitalmanagementsystem.services.DoctorService;
import org.example.hospitalmanagementsystemview.controllers.doctor.DoctorController;
import org.example.hospitalmanagementsystemview.input.AppointmentEditForm;
import org.example.hospitalmanagementsystemview.input.AppointmentSearchForm;
import org.example.hospitalmanagementsystemview.input.OfferSearchForm;
import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.clientview.AppointmentViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.homeview.OfferViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.lists.AppointmentListViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.lists.OffersListViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.medicalview.AppointmentEditViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorControllerImpl implements DoctorController {

    DoctorService doctorService;
    AppointmentService appointmentService;

    @Autowired
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    @GetMapping()
    public String listAppointments(@ModelAttribute("form") AppointmentSearchForm form,
                                   Model model) {

        String appointmentSearchTerm = form != null && form.searchTerm() != null ? form.searchTerm() : "";
        int page = form != null && form.page() != null ? form.page() : 1;
        int size = form != null && form.size() != null ? form.size() : 3;
        form = new AppointmentSearchForm(appointmentSearchTerm, page, size);

        var currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        assert currentUser instanceof User;
        var appointmentPage = appointmentService.getAppointmentsForDoctor(((User) currentUser).getUsername(), page, size);
        var appointmentViewModels = appointmentPage.stream()
                .map(appointment -> new AppointmentViewModel(appointment.getId(), appointment.getPhoneNumber(), appointment.getDirection(), appointment.getClinic(), appointment.getDoctorFullName(), appointment.getResult())).toList();

        var appointmentViewModel = new AppointmentListViewModel(
                appointmentViewModels, appointmentPage.getTotalPages()
        );

        model.addAttribute("model", appointmentViewModel);
        model.addAttribute("form", form);

        return "doctor-appointment";
    }

//    @Override
//    public String listAppointmentsWithoutAnalyse(Integer id, Model model) {
//        return "";
//    }
//
//    @Override
//    public String listAppointmentsWithAnalyse(Integer doctorId, Model model) {
//        return "";
//    }

    @Override
    @GetMapping("/appointments/{id}/edit")
    public String editAppointmentForm(@PathVariable Integer id, Model model) {
        var appointment = appointmentService.getAppointmentById(id);
        var viewModel = new AppointmentEditViewModel(
                createBaseViewModel("добавит анализ")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new AppointmentEditForm(appointment.getId(), appointment.getPhoneNumber(), appointment.getDirection(), appointment.getClinic(), appointment.getDoctorFullName(),appointment.getResult()));
        return "doctor-appointment-edit";
    }

    @Override
    @PostMapping("/appointments/{id}/edit")
    public String editAppointment(Integer id, AppointmentEditForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new AppointmentEditViewModel(
                    createBaseViewModel("результат")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "doctor-appointment-edit";
        }
        appointmentService.updateAppointmentResult(new AppointmentEditDto(form.id(), form.phoneNumber(), form.direction(), form.clinic(), form.doctorFullName(), form.result()));
        return "redirect:/doctor";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return null;
    }
}
