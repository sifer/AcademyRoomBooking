package com.example.controller;

import com.example.Methods.Encryptor;
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

    @Autowired
    private IRepository repository;

    @Autowired
    DataSource dataSource;

    @GetMapping("/booking/")
    public ModelAndView modelSec() {
        ModelAndView mModel = new ModelAndView("booking/weekview");
        return mModel;
    }

    @GetMapping("/login/login")
    public void loginPage(){
        return;
    }
    @PostMapping("/login/login")
    public String loginPage(String username, String password) throws SQLException {
        try{
            Encryptor encryptor = new Encryptor();
            String passwordEncrypted = encryptor.MD5(password);
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT UserName, [Password] FROM [User] WHERE UserName = ? AND [Password]=?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,passwordEncrypted);
            ResultSet rs = preparedStatement.executeQuery();
            String userNameChecked = null;
            String passwordChecked = null;
            while(rs.next()){
                userNameChecked = rs.getString("UserName");
                passwordChecked = rs.getString("Password");
            }

            if(userNameChecked != null && passwordChecked != null & userNameChecked.trim().equalsIgnoreCase(username) && passwordChecked.equalsIgnoreCase(passwordEncrypted)){
                //TODO Return booking-page
                System.out.println("correct pass");
                return "/booking/";
            }
            else{
                //TODO Return login try-again
                System.out.println("wrong login");
                return "/login/login";
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    return "/login/login";
    }

    @GetMapping("/room/")
    public ModelAndView listRooms() {
        ArrayList<Room> rooms = (ArrayList<Room>) repository.getRooms();
        return new ModelAndView("room/index")
                .addObject("rooms", rooms);
    }
}
