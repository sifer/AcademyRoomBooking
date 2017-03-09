package com.example.repository;

import com.example.domain.Booking;
import com.example.domain.Person;
import com.example.domain.Room;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-03-08.
 */

@Component
public class JDBCRepository implements IRepository {
    @Autowired
    private DataSource dataSource;

    @Override
    public ArrayList<Room> getRooms() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT roomid, size, name FROM room")) {
            ArrayList<Room> rooms = new ArrayList<>();
            while (rs.next()) rooms.add(rsRoom(rs));
            return rooms;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public ArrayList<User> getUsers() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT UserID, Person_ID FROM [User]")) {
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) users.add(rsUser(rs));
            return users;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public ArrayList<Booking> getBookings() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT BookingID, StartTime, EndTime, Room_ID, User_ID FROM Booking")) {
            ArrayList<Booking> bookings = new ArrayList<>();
            while (rs.next()) bookings.add(rsBookings(rs));
            return bookings;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public ArrayList<Person> getPersons() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT PersonID, FirstName, LastName FROM Person")) {
            ArrayList<Person> persons = new ArrayList<>();
            while (rs.next()) persons.add(rsPerson(rs));
            return persons;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    private User rsUser(ResultSet rs) throws SQLException{
        return new User(rs.getInt("userid"), rs.getInt("Person_ID"));
    }
    private Room rsRoom(ResultSet rs) throws SQLException {
        return new Room(rs.getInt("roomid"), rs.getInt("size"), rs.getString("name"));
    }
    private Booking rsBookings(ResultSet rs) throws SQLException {
        return new Booking(rs.getInt("BookingID"), rs.getDate("StartTime"), rs.getDate("EndTime"), rs.getInt("Room_ID"), rs.getInt("User_ID"));
    }
    private Person rsPerson(ResultSet rs) throws SQLException {
        return new Person(rs.getInt("PersonID"), rs.getString("FirstName"), rs.getString("LastName"));
    }

}
