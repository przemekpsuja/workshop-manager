package com.example.workshopmanager.security;

import com.example.workshopmanager.controller.MailService;
import com.example.workshopmanager.model.User;
import com.example.workshopmanager.repository.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;

@Route("registration")
//@StyleSheet("/background.css")
public class RegistrationForm extends VerticalLayout {

    private final String registrtionMessage = ("Cześć! Witamy Cię w naszym warsztacie");
    private User user;
    private MailService mailService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationForm(UserRepository userRepository, MailService mailService, PasswordEncoder passwordEncoder){
        VerticalLayout loginLayout = new VerticalLayout();

        TextField userNameField = new TextField();
        userNameField.setRequired(true);
        userNameField.setPlaceholder("Imię");
        userNameField.setAutoselect(true);
//        TextField userSurnameField = new TextField();
//        userSurnameField.setRequired(true);
//        userSurnameField.setPlaceholder("Nazwisko");
        EmailField userEmailField = new EmailField();
        userEmailField.setErrorMessage("Podaj prawidłowey format maila");
        userEmailField.setPlaceholder("Email");
        PasswordField userPasswordField = new PasswordField();
        userPasswordField.setPlaceholder("Hasło");
        userPasswordField.setPattern("^[a-zA-Z0-9]{6,}$");
        Button signInButton = new Button("Zarejestruj", event -> {

            if(!userNameField.getValue().isEmpty() &&  !userEmailField.getValue().isEmpty() && !userPasswordField.getValue().isEmpty()){

                User user = new User()
                        .builder()
                        .userName(userNameField.getValue())
                        .userEmail(userEmailField.getValue())
//                        .userPassword(userPasswordField.getValue())
                        .userPassword(passwordEncoder.encode(userPasswordField.getValue()))
                        .enabled(true)
                        .role("USER")
                        .build();
                userRepository.save(user);
                try {
                    mailService.sendMail(userEmailField.getValue(), "Witamy w warsztacie", registrtionMessage,false);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }


            } else {
                Notification error = new Notification("Uzupełnij wszystkie pola") ;
                add(error);
            }
        });

        signInButton.addClickListener(ClickEvent -> {
            signInButton.getUI().ifPresent(ui -> ui.navigate(""));
        });

        add(userNameField, userEmailField, userPasswordField, signInButton);


    }

}
