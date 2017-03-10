package com.example.controller;

import com.example.Methods.Encryptor;
import com.example.domain.Booking;
import com.example.domain.Person;
import com.example.domain.Room;
import com.example.domain.User;
import com.example.repository.IRepository;
import com.example.repository.JDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-03-08.
 */
@Controller
public class CustomController {

    ArrayList<Room> rooms;
    ArrayList<User> users;
    ArrayList<Person> persons;
    ArrayList<Booking> bookings;

    @PostConstruct
    public void RefreshRooms(){
        rooms = (ArrayList<Room>) repository.getRooms();
    }
    @PostConstruct
    public void RefreshUsers(){
        users = (ArrayList<User>) repository.getUsers();
    }
    @PostConstruct
    public void RefreshBookings(){
        bookings = (ArrayList<Booking>) repository.getBookings();
    }
    @PostConstruct
    public void RefreshPersons(){
        persons = (ArrayList<Person>) repository.getPersons();
    }

    @Autowired
    private IRepository repository;

    @Autowired
    DataSource dataSource;

    @GetMapping("/booking")
    public ModelAndView foundation() {
        ModelAndView mModel = new ModelAndView("booking/index").addObject("bookings", bookings);
        return mModel;
    }

    @GetMapping("/sandbox")
    public ModelAndView modelSec() {
        ModelAndView mModel = new ModelAndView("booking/sandbox")
                .addObject("rooms", repository.getRooms());
        return mModel;
    }

    /*
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("/login/login");
    }
    */

    @GetMapping("/login/login")
    public void loginPage() { return;}

    @PostMapping("/login/login")
    public ModelAndView loginPage(String username, String password) throws SQLException {
        ModelAndView model;
        try {
            Encryptor encryptor = new Encryptor();
            String passwordEncrypted = encryptor.MD5(password);
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT UserName, [Password] FROM [User] WHERE UserName = ? AND [Password]=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwordEncrypted);
            ResultSet rs = preparedStatement.executeQuery();
            String userNameChecked = null;
            String passwordChecked = null;
            while (rs.next()) {
                userNameChecked = rs.getString("UserName");
                passwordChecked = rs.getString("Password");
            }

            if (userNameChecked != null && passwordChecked != null & userNameChecked.trim().equalsIgnoreCase(username) && passwordChecked.equalsIgnoreCase(passwordEncrypted)) {
                //TODO Return booking-page
                System.out.println("correct pass");
                return new ModelAndView("/booking");
            } else {
                //TODO Return login try-again
                System.out.println("wrong login");
                return new ModelAndView("/login/login").addObject("error", "Wrong username or password.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ModelAndView("/login/login");
    }

    @GetMapping("/room/")
    public ModelAndView listRooms() {
        return new ModelAndView("room/index")
                .addObject("rooms", rooms);
    }
}
