package com.test.models.bos;

import com.test.models.beans.User;
import com.test.models.daos.UserDAO;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.ArrayList;

public class UserBO {
    private UserDAO userDAO;

    public UserBO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserById(long userId) {
        return userDAO.getById(userId);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        String myHash = getHashCode(password);

        return userDAO.getUserByUsernameAndPassword(username, myHash);
    }

    public ArrayList<User> getUsers(long exceptionId) {
        return userDAO.getUsers(exceptionId);
    }

    public ArrayList<User> search(String keyWord) {
        return  userDAO.getUsers(keyWord);
    }

    public int insert(User user) {
        user.setPassword(getHashCode(user.getPassword()));
        return userDAO.insert(user);
    }

    public int update(User user) {
        return userDAO.update(user);
    }

    public int delete(long userId) {
        return userDAO.delete(userId);
    }

    private String getHashCode(String password) {
        String myHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes()); // Updates the digest using the specified array of bytes
            byte[] digest = md.digest(); // Completes the hash computation
            myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return myHash;
    }
}
