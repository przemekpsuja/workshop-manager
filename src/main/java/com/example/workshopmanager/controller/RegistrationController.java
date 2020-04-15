package com.example.workshopmanager.controller;

import com.example.workshopmanager.model.User;
import com.example.workshopmanager.repository.UserRepository;
import com.example.workshopmanager.security.RegistrationForm;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;

@Controller
public class RegistrationController {

    private final String registrationMessage = ("Cześć! Witamy Cię w naszym warsztacie");

    private RegistrationForm registrationForm;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;
    private UserRepository userRepository;

    public RegistrationController(PasswordEncoder passwordEncoder, MailService mailService, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.userRepository = userRepository;
    }

    public void registerUser(String userNameValue, String userEmailValue, String userPasswordValue) {
        if (!userNameValue.isEmpty() && !userEmailValue.isEmpty() && !userPasswordValue.isEmpty()) {

            User user = User
                    .builder()
                    .userName(userNameValue)
                    .userEmail(userEmailValue)
                    .userPassword(passwordEncoder.encode(userPasswordValue))
                    .enabled(true)
                    .role("USER")
                    .build();
            userRepository.save(user);

            try {
                mailService.sendMail(user.getUserEmail(), "Witamy w warsztacie",
                        registrationMessage, false);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        } else {
            Notification error = new Notification("Uzupełnij wszystkie pola");
            registrationForm.add(error);
        }
    }
}
