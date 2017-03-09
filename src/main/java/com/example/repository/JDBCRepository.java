package com.example.repository;

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
    public List<Room> getRooms() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT roomid, size, name FROM room")) {
            List<Room> rooms = new ArrayList<>();
            while (rs.next()) rooms.add(rsRoom(rs));
            return rooms;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<User> getUsers() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT UserID, Password FROM User")) {
            List<User> users = new ArrayList<>();
            while (rs.next()) users.add(rsUser(rs));
            return users;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    private User rsUser(ResultSet rs) throws SQLException{
        return new User(rs.getInt("userid"), rs.getString("UserName"), rs.getString("Password"));
    }


    private Room rsRoom(ResultSet rs) throws SQLException {
        return new Room(rs.getInt("roomid"), rs.getInt("size"), rs.getString("name"));
    }

}
