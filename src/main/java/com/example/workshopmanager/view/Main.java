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

@Route("index")
@StyleSheet("background.css")
public class Main extends HorizontalLayout {

    public Main() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {

            AppLayout appLayout = new AppLayout();

            AppLayoutMenu menu = appLayout.createMenu();
            Image img = new Image("https://i.pinimg.com/originals/72/82/5c/72825cd366980b3ba05c314c9e6e75bb.png", "Car workshop Logo");
            img.setHeight("100px");
            appLayout.setBranding(img);
            Image mainPicture = new Image("/images/helloImage.png", "hello");
            mainPicture.setSizeFull();

            AppLayoutMenuItem logout = new AppLayoutMenuItem("Wyloguj");

            AppLayoutMenuItem registering = new AppLayoutMenuItem("Zarejestruj", "register");
            AppLayoutMenuItem pricesCustomer = new AppLayoutMenuItem("Cennik", "prices");
            AppLayoutMenuItem calendar = new AppLayoutMenuItem("Terminarz", "dates");
            AppLayoutMenuItem contactDetails = new AppLayoutMenuItem("Kontakt", "contact");
            AppLayoutMenuItem editionPrices = new AppLayoutMenuItem("Zarządzaj cenami", "prices-add");
            AppLayoutMenuItem addCars = new AppLayoutMenuItem("Dodaj samochód", "addCar");
            AppLayoutMenuItem addCustomer = new AppLayoutMenuItem("Dodaj klienta", "addClient");
            AppLayoutMenuItem allCustomersView = new AppLayoutMenuItem("Lista klientów", "allOwners");
            AppLayoutMenuItem allCarsView = new AppLayoutMenuItem("Lista samochodów", "allCars");
            AppLayoutMenuItem addOrder = new AppLayoutMenuItem("Nowe zlecenie", "add-order");
            AppLayoutMenuItem allOrderView = new AppLayoutMenuItem("Lista Zleceń", "all-orders");

            Component content = new Span(
                    mainPicture
            );

            appLayout.setContent(content);

            logout.addMenuItemClickListener(menuItemClickEvent ->
            {
                UI.getCurrent().getPage().executeJavaScript("window.open(\"/logout\", \"_self\");");
            });

            calendar.addMenuItemClickListener(ClickEvent -> {
                calendar.getUI().ifPresent(ui -> ui.navigate("/dates"));
            });

            allCarsView.addMenuItemClickListener(ClickEvent -> {
                Component component = new Span();
                add(component);
//                allCarsView.getUI().ifPresent(ui -> ui.navigate("/allCars"));

            });

            allCustomersView.addMenuItemClickListener(ClickEvent -> {
                allCustomersView.getUI().ifPresent(ui -> ui.navigate("/allOwners"));

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
                appLayout.setMenu(new Span(addCars));

//                add(appLayout);
//                addCars.getUI().ifPresent(ui -> ui.navigate("/addCar"));

            });

            addCustomer.addMenuItemClickListener(ClickEvent -> {
                addCustomer.getUI().ifPresent(ui -> ui.navigate("/addClient"));

            });

            menu.addMenuItems(
                    addCars,
                    allCarsView,
                    addCustomer,
                    allCustomersView,
                    addOrder,
                    allOrderView,
                    calendar,
                    pricesCustomer,
                    editionPrices,
                    contactDetails,
                    logout
            );

            add(appLayout);

        } else if (authorities.contains(new SimpleGrantedAuthority("USER"))) {

            AppLayout appLayout = new AppLayout();
            AppLayoutMenu menu = appLayout.createMenu();
            Image img = new Image("https://i.pinimg.com/originals/72/82/5c/72825cd366980b3ba05c314c9e6e75bb.png",
                    "Car workshop Logo");
            img.setHeight("100px");
            appLayout.setBranding(img);
            Image mainPicture = new Image("/images/helloImage.png", "hello");
            mainPicture.setSizeFull();

            AppLayoutMenuItem login = new AppLayoutMenuItem("Zaloguj");
            login.addMenuItemClickListener(menuItemClickEvent ->
            {
                UI.getCurrent().getPage().executeJavaScript("window.open(\"/login\", \"_self\");");
            });

            AppLayoutMenuItem pricesCustomer = new AppLayoutMenuItem("Cennik", "prices");
            AppLayoutMenuItem calendar = new AppLayoutMenuItem("Terminarz", "dates");
            AppLayoutMenuItem contactDetails = new AppLayoutMenuItem("Kontakt", "contact");
            AppLayoutMenuItem logout = new AppLayoutMenuItem("Wyloguj");

            contactDetails.addMenuItemClickListener(ClickEvent -> {
                contactDetails.getUI().ifPresent(ui -> ui.navigate("/contact"));
            });

            pricesCustomer.addMenuItemClickListener(ClickEvent -> {
                pricesCustomer.getUI().ifPresent(ui -> ui.navigate("/prices"));
            });

            logout.addMenuItemClickListener(menuItemClickEvent -> {
                UI.getCurrent().getPage().executeJavaScript("window.open(\"/logout\", \"_self\");");
            });

            menu.addMenuItems(
                    pricesCustomer,
                    calendar,
                    contactDetails,
                    logout
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

            AppLayoutMenuItem login = new AppLayoutMenuItem("Zaloguj");
            login.addMenuItemClickListener(menuItemClickEvent ->
            {
                UI.getCurrent().getPage().executeJavaScript("window.open(\"/login\", \"_self\");");
            });

            AppLayoutMenuItem registration = new AppLayoutMenuItem("Zarejestruj", "registration");
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
                    login,
                    registration
            );

            Component content = new Span(
                    mainPicture
            );

            appLayout.setContent(content);
            add(appLayout);
        }
    }
}

