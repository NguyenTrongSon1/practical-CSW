package com.example.practicalcsw.model;

import com.example.practicalcsw.entity.Employee;
import com.example.practicalcsw.utils.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private Connection conn;

    public EmployeeModel() {
        conn = ConnectionHelper.getConnection();
    }

    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from employees where status = ?");
        stmt.setInt(1, 1);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            int status = rs.getInt("status");
            employees.add(new Employee(id, name, email, status));
        }
        return employees;
    }

    public Employee findById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from employees where status = ? and id = ?");
        stmt.setInt(1, 1);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            int status = rs.getInt("status");
            return new Employee(id, name, email, status);
        }
        return null;
    }

    public Employee save(Employee product) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("insert into employees (name, email, status) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getEmail());
        stmt.setInt(3, product.getStatus());
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet resultSetGeneratedKeys = stmt.getGeneratedKeys();
            if (resultSetGeneratedKeys.next()) {
                int id = resultSetGeneratedKeys.getInt(1);
                product.setId(id);
                return product;
            }
        }
        return null;
    }

    public Employee update(int id, Employee updateProduct) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("update employees set name = ?, email = ?, status = ? where id = ?");
        stmt.setString(1, updateProduct.getName());
        stmt.setString(2, updateProduct.getEmail());
        stmt.setInt(3, updateProduct.getStatus());
        stmt.setInt(4, id);
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            updateProduct.setId(id);
            return updateProduct;
        }
        return null;
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement stmtDelete = conn.prepareStatement("delete from employees where id = ?");
        stmtDelete.setInt(1, id);
        int affectedRows = stmtDelete.executeUpdate();
        if (affectedRows > 0) {
            return true;
        }
        return false;
    }
}
