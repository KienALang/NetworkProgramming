package com.test.models.daos;

import com.test.models.beans.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends BaseDAO<User> {

    public UserDAO() {
        super();
    }

    @Override
    public ArrayList getAll() {
        ArrayList<User> users = null;
        String sql = "SELECT * FROM [User];";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            users = new ArrayList<>();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User getById(long id) {
        User user = null;
        String sql = "SELECT * FROM [User] WHERE [UserId] = ?;";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM [User] WHERE Username = ? AND Password = ?;";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs != null && rs.next()) {
                user = getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public ArrayList<User> getUsers(long exceptionId) {
        ArrayList<User> users = null;
        String sql = "SELECT * FROM [User] WHERE [UserId] NOT IN (?);";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, exceptionId);
            rs = pstmt.executeQuery();
            users = new ArrayList<>();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("UserId"),
                rs.getString("Username"),
                "", // Should get password to show back.
                rs.getString("FullName"),
                rs.getString("Email"),
                rs.getLong("RoleId")
        );
    }

    public ArrayList<User> getUsers(String keyword) {
        String keyWord = "%"+keyword+"%";
        ArrayList<User> users = null;
        String sql = "SELECT * FROM [User] WHERE [Username] LIKE ? OR [FullName] LIKE ? AND [UserId] NOT IN (1);";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, keyWord);
            pstmt.setString(2, keyWord);
            rs = pstmt.executeQuery();
            users = new ArrayList<>();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int insert(User user) {
        String sql = "INSERT INTO [User] (Username, Password, FullName, Email, RoleId) VALUES(?, ?, ?, ?, ?);";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFullName());
            pstmt.setString(4, user.getEmail());
            pstmt.setLong(5, user.getRoleId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int update(User user) {
        String sql = "UPDATE [User] SET Username = ?, FullName = ?, Email = ? WHERE UserId = ? ;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getFullName());
            pstmt.setString(3, user.getEmail());
            pstmt.setLong(4, user.getUserId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int delete (long userId) {
        String sql = "DELETE [User] WHERE [UserId] = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, userId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
