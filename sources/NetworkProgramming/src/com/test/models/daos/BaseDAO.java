package com.test.models.daos;

import com.test.utils.DBConnection;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class BaseDAO<T> {
    protected Connection connection;
    public BaseDAO() {
        if (this.connection == null) {
            connection = DBConnection.connect();
        }
    }

    public abstract ArrayList<T> getAll();

    public abstract T getById(long id);
}
