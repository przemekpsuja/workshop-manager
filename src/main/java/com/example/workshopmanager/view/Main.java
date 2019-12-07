package com.example.workshopmanager.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;


@Route("")
@StyleSheet("/background.css")
public class Main extends HorizontalLayout {

    public Main() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
            AppLayout appLayout = new AppLayout();

            AppLayoutMenu menu = appLayout.createMenu();
            Image img = new Image("https://i.pinimg.com/originals/72/82/5c/72825cd366980b3ba05c314c9e6e75bb.png",
                    "Car workshop Logo");
            img.setHeight("100px");
            appLayout.setBranding(img);
            Image mainPicture = new Image("/images/helloImage.png", "hello");
            mainPicture.setSizeFull();

            AppLayoutMenuItem login = new AppLayoutMenuItem("Zaloguj/Wyloguj");
            login.addMenuItemClickListener(menuItemClickEvent ->
            {
                UI.getCurrent().getPage().executeJavaScript("window.open(\"/login\", \"_self\");");
            });

            AppLayoutMenuItem registering = new AppLayoutMenuItem("Zarejestruj", "register");
            AppLayoutMenuItem pricesCustomer = new AppLayoutMenuItem("Cennik", "prices");
            AppLayoutMenuItem calendar = new AppLayoutMenuItem("Terminarz", "dates");
            AppLayoutMenuItem contactDetails = new AppLayoutMenuItem("Kontakt", "contact");
            AppLayoutMenuItem editionPrices = new AppLayoutMenuItem("Zarządzaj cenami", "prices-add");
            AppLayoutMenuItem addCars = new AppLayoutMenuItem("Dodaj samochód", "addCar");
            AppLayoutMenuItem addCustomer = new AppLayoutMenuItem("Dodaj klienta", "addClient");
            AppLayoutMenuItem seeCustomers = new AppLayoutMenuItem("Lista klientów", "allOwners");
            AppLayoutMenuItem seeAllCars = new AppLayoutMenuItem("Lista samochodów", "allCars");

            calendar.addMenuItemClickListener(ClickEvent -> {
                calendar.getUI().ifPresent(ui -> ui.navigate("/dates"));
            });
            seeAllCars.addMenuItemClickListener(ClickEvent -> {
                seeAllCars.getUI().ifPresent(ui -> ui.navigate("/allCars"));

            });

            seeCustomers.addMenuItemClickListener(ClickEvent -> {
                seeCustomers.getUI().ifPresent(ui -> ui.navigate("/allOwners"));

            });

            contactDetails.addMenuItemClickListener(ClickEvent -> {
                contactDetails.getUI().ifPresent(ui -> ui.navigate("/contact"));
            });

            pricesCustomer.addMenuItemClickListener(ClickEvent -> {
                pricesCustomer.getUI().ifPresent(ui -> ui.navigate("/prices"));
            });

            editionPrices.addMenuItemClickListener(ClickEvent -> {
                editionPrices.getUI().ifPresent(ui -> ui.navigate("/prices-add"));

            });

            addCars.addMenuItemClickListener(ClickEvent -> {
                addCars.getUI().ifPresent(ui -> ui.navigate("/addCar"));

            });

            addCustomer.addMenuItemClickListener(ClickEvent -> {
                addCustomer.getUI().ifPresent(ui -> ui.navigate("/addClient"));

            });

            menu.addMenuItems(
                    pricesCustomer,
                    calendar,
                    contactDetails,
                    editionPrices,
                    addCars,
                    addCustomer,
                    seeCustomers,
                    seeAllCars,
                    login
//                    registering
            );

            Component content = new Span(

                    mainPicture

            );

            appLayout.setContent(content);

            add(appLayout);
        } else {
            AppLayout appLayout = new AppLayout();
            AppLayoutMenu menu = appLayout.createMenu();
            Image img = new Image("https://i.pinimg.com/originals/72/82/5c/72825cd366980b3ba05c314c9e6e75bb.png",
                    "Car workshop Logo");
            img.setHeight("100px");
            appLayout.setBranding(img);
            Image mainPicture = new Image("/images/helloImage.png", "hello");
            mainPicture.setSizeFull();

            AppLayoutMenuItem login = new AppLayoutMenuItem("Zaloguj/Wyloguj");
            login.addMenuItemClickListener(menuItemClickEvent ->
            {
                UI.getCurrent().getPage().executeJavaScript("window.open(\"/login\", \"_self\");");
            });

            AppLayoutMenuItem registering = new AppLayoutMenuItem("Zarejestruj", "register");
            AppLayoutMenuItem pricesCustomer = new AppLayoutMenuItem("Cennik", "prices");
            AppLayoutMenuItem calendar = new AppLayoutMenuItem("Terminarz", "dates");
            AppLayoutMenuItem contactDetails = new AppLayoutMenuItem("Kontakt", "contact");


            contactDetails.addMenuItemClickListener(ClickEvent -> {
                contactDetails.getUI().ifPresent(ui -> ui.navigate("/contact"));
            });

            pricesCustomer.addMenuItemClickListener(ClickEvent -> {
                pricesCustomer.getUI().ifPresent(ui -> ui.navigate("/prices"));
            });


            menu.addMenuItems(
                    pricesCustomer,
                    calendar,
                    contactDetails,
                    login
//                    registering
            );

            Component content = new Span(

                    mainPicture

            );

            appLayout.setContent(content);

            add(appLayout);
        }
    }
}
