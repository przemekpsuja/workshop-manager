package com.example.workshopmanager.security;

import com.example.workshopmanager.controller.MailService;
import com.example.workshopmanager.controller.RegistrationController;
import com.example.workshopmanager.repository.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.security.crypto.password.PasswordEncoder;

@Route("registration")
public class RegistrationForm extends VerticalLayout {

    private TextField usernameField;
    private EmailField userEmailField;
    private PasswordField userPasswordField;
    private Button signInButton;
    private UserRepository userRepository;
    private MailService mailService;
    private PasswordEncoder passwordEncoder;
    private RegistrationController registrationController;

    public RegistrationForm(UserRepository userRepository, MailService mailService, RegistrationController registrationController) {

        VerticalLayout loginLayout = new VerticalLayout();
        this.usernameField = new TextField();
        usernameField.setRequired(true);
        usernameField.setPlaceholder("Login");
        usernameField.setAutoselect(true);
        this.userEmailField = new EmailField();
        userEmailField.setErrorMessage("Podaj prawidłowey format maila");
        userEmailField.setPlaceholder("Email");
        this.userPasswordField = new PasswordField();
        userPasswordField.setPlaceholder("Hasło");
        userPasswordField.setPattern("^[a-zA-Z0-9]{6,}$");
        this.signInButton = new Button("Zarejestruj", buttonClickEvent -> {
            registrationController.registerUser(usernameField.getValue(), userEmailField.getValue(), userPasswordField.getValue());
            signInButton.getUI().ifPresent(ui -> ui.navigate("index"));
        });

        add(usernameField, userEmailField, userPasswordField, signInButton);
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public EmailField getUserEmailField() {
        return userEmailField;
    }

    public void setUserEmailField(EmailField userEmailField) {
        this.userEmailField = userEmailField;
    }

    public PasswordField getUserPasswordField() {
        return userPasswordField;
    }

    public void setUserPasswordField(PasswordField userPasswordField) {
        this.userPasswordField = userPasswordField;
    }
}
