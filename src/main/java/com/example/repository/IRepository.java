package com.example.repository;

import com.example.domain.Booking;
import com.example.domain.Person;
import com.example.domain.Room;
import com.example.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2017-03-08.
 */
public interface IRepository {
    List<Room> getRooms();
    List<User> getUsers();
    List<Booking> getBookings();
    List<Person> getPersons();
}
