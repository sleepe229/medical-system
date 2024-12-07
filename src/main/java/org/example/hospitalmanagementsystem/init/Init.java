package org.example.hospitalmanagementsystem.init;

import com.github.javafaker.Faker;
import org.example.hospitalmanagementsystem.dto.*;
import org.example.hospitalmanagementsystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Init implements CommandLineRunner {

    private final ClinicService clinicService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final NewsService newsService;
    private final OfferService offerService;

    private final Faker faker;

    @Autowired
    public Init(ClinicService clinicService,
                DoctorService doctorService,
                AppointmentService appointmentService,
                NewsService newsService,
                OfferService offerService) {
        this.clinicService = clinicService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.newsService = newsService;
        this.offerService = offerService;
        this.faker = new Faker(new java.util.Locale("ru"));
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Integer> clinicIds = initializeClinics();
//        List<Integer> doctorIds = initializeDoctors(clinicIds);
//        initializeAppointments(clinicIds, doctorIds);
//        initializeNews();
//        initializeOffers();
    }

    private List<Integer> initializeClinics() {
        System.out.println("Инициализация клиник...");
        List<Integer> clinicIds = new ArrayList<>();

        List<ClinicInputDto> clinics = List.of(
                new ClinicInputDto("Центральная клиника", "Москва, ул. Ленина, д. 10", "+7 495 123-45-67"),
                new ClinicInputDto("Медицинский центр здоровья", "Санкт-Петербург, ул. Маяковского, д. 15", "+7 812 456-78-90"),
                new ClinicInputDto("Современная клиника", "Новосибирск, ул. Советская, д. 5", "+7 383 321-12-34")
        );

        for (ClinicInputDto clinic : clinics) {
            clinicService.createClinic(clinic);
        }

        // Получение всех ID созданных клиник
        clinicService.getClinics(1, 100).getContent().forEach(clinic -> clinicIds.add(clinic.getId()));

        System.out.println("Клиники успешно добавлены.");
        return clinicIds;
    }

    private List<Integer> initializeDoctors(List<Integer> clinicIds) {
        System.out.println("Инициализация врачей...");
        List<Integer> doctorIds = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DoctorInputDto doctor = new DoctorInputDto(
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber(),
                    faker.number().numberBetween(1, 5), // ID специализации
                    faker.university().name(),
                    LocalDate.now().minusYears(faker.number().numberBetween(1, 30)).toString(), // HireDate
                    faker.job().position(),
                    faker.number().numberBetween(1, 3), // ID статуса
                    clinicIds.get(faker.random().nextInt(clinicIds.size())) // Случайная клиника
            );
            doctorService.createDoctor(doctor);
        }

        // Получение всех ID созданных врачей
//        doctorService.getDoctors(1, 100).getContent().forEach(doctor -> doctorIds.add(doctor.id()));

        System.out.println("Врачи успешно добавлены.");
        return doctorIds;
    }

    private void initializeAppointments(List<Integer> clinicIds, List<Integer> doctorIds) {
        System.out.println("Инициализация записей на приём...");

        for (int i = 0; i < 10; i++) {
            AppointmentInputDto appointment = new AppointmentInputDto(
                    faker.phoneNumber().phoneNumber(),
                    faker.number().numberBetween(1, 5), // ID специализации
                    clinicIds.get(faker.random().nextInt(clinicIds.size())), // Случайная клиника
                    doctorIds.get(faker.random().nextInt(doctorIds.size())) // Случайный врач
            );
            appointmentService.createAppointment(appointment);
        }

        System.out.println("Записи на приём успешно добавлены.");
    }

    private void initializeNews() {
        System.out.println("Инициализация новостей...");

        for (int i = 0; i < 10; i++) {
            NewsInputDto news = new NewsInputDto(
                    faker.book().title(),
//                    LocalDate.now().toString(),
                    faker.lorem().paragraph()
            );
            System.out.println("хуй" + news);
            newsService.createNews(news);
        }

        System.out.println("Новости успешно добавлены.");
    }

//    private void initializeOffers() {
//        System.out.println("Инициализация предложений...");
//
//        for (int i = 0; i < 10; i++) {
//            OfferInputDto offer = new OfferInputDto(
//                    faker.commerce().productName(),
//                    faker.lorem().paragraph(),
//                    faker.number().numberBetween(1, 100) * 100
//            );
//            offerService.createOffer(offer);
//        }
//
//        System.out.println("Предложения успешно добавлены.");
//    }
}
