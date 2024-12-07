package org.example.hospitalmanagementsystem.controllers.admin;

import jakarta.validation.Valid;
import org.example.hospitalmanagementsystem.dto.DoctorEditDto;
import org.example.hospitalmanagementsystem.dto.DoctorInputDto;
import org.example.hospitalmanagementsystem.services.DoctorService;
import org.example.hospitalmanagementsystemview.controllers.admin.AdminDoctorController;
import org.example.hospitalmanagementsystemview.input.DoctorCreateForm;
import org.example.hospitalmanagementsystemview.input.DoctorEditForm;
import org.example.hospitalmanagementsystemview.input.DoctorSearchForm;
import org.example.hospitalmanagementsystemview.viewmodel.adminview.DoctorCreateViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.adminview.DoctorEditViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.appointmentview.DoctorViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.baseview.BaseViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.details.DoctorDetailsViewModel;
import org.example.hospitalmanagementsystemview.viewmodel.lists.DoctorListViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/doctor")
public class AdminDoctorControllerImpl implements AdminDoctorController {

    private DoctorService doctorService;

    @Autowired
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    @GetMapping
    public String listDoctors(@ModelAttribute("doctorForm") DoctorSearchForm doctorForm, Model model) {
        String doctorSearchTerm = doctorForm != null && doctorForm.searchTerm() != null ? doctorForm.searchTerm() : "";
        int doctorPage = doctorForm != null && doctorForm.page() != null ? doctorForm.page() : 1;
        int doctorSize = doctorForm != null && doctorForm.size() != null ? doctorForm.size() : 3;
        doctorForm = new DoctorSearchForm(doctorSearchTerm, doctorPage, doctorSize);

        var doctorsPage = doctorService.getDoctors(doctorSearchTerm, doctorPage, doctorSize);
        var doctorViewModels = doctorsPage.stream()
                .map(doctor -> new DoctorViewModel(doctor.getFullName(), doctor.getPosition(), doctor.getExperienceYears()))
                .toList();

        var doctorViewModel = new DoctorListViewModel(
                doctorViewModels, doctorsPage.getTotalPages()
        );

        model.addAttribute("doctorForm", doctorForm);
        model.addAttribute("model", doctorViewModel);
        return "doctor-list";
    }

    @Override
    @GetMapping("/{id}")
    public String detailsDoctor(Integer id, Model model) {
        var doctor = doctorService.getDoctor(id);
        var viewModel = new DoctorDetailsViewModel(
                createBaseViewModel("искат врач"),
                new DoctorViewModel(doctor.getFullName(), doctor.getPosition(), doctor.getExperienceYears())
        );
        model.addAttribute("viewModel", viewModel);
        //todo: add html
        return "doctor-details";
    }

    @Override
    @GetMapping("/create")
    public String createDoctorForm(Model model) {
        var viewModel = new DoctorCreateViewModel(
                createBaseViewModel("создат врач")
        );
        model.addAttribute("viewModel", viewModel);
        model.addAttribute("form", new DoctorCreateForm("", "", 0, "", "", "", 0, 0));
        return "doctor-create";
    }

    @Override
    @PostMapping("/create")
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
        return "redirect:/admin/doctor";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editDoctorForm(Integer id, Model model) {
        var doctor = doctorService.getDoctor(id);
        var viewModel = new DoctorEditViewModel(
                createBaseViewModel("менят врача")
        );
        model.addAttribute("model", viewModel);
//        model.addAttribute("form", new DoctorEditForm(doctor.getFullName(), doctor.getPhoneNumber(), doctor.getSpecializationId(), doctor.getEducation(), doctor.getHireDate(), doctor.getPosition(), doctor.getStatusId(), doctor.getClinicId()));
//        todo:fix
        return "doctor-edit";
    }

    @Override
    @PostMapping("/{id}/edit")
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
        return "redirect:/admin/doctor/" + id;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return null;
    }
}
