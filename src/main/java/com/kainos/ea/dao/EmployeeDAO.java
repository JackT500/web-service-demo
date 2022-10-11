package com.kainos.ea.dao;

import com.kainos.ea.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class EmployeeDAO {
    public void insertEmployee(Employee emp, Connection c) throws SQLException {
        String insertOrderQuery = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = c.prepareStatement(insertOrderQuery);
        preparedStmt.setInt(1, emp.getNumber());
        preparedStmt.setString(2, emp.getForename());
        preparedStmt.setString(3, emp.getSurname());
        preparedStmt.setFloat(4, emp.getSalary() / 100);
        preparedStmt.setString(5, emp.getBankAccountNum());
        preparedStmt.setString(6, emp.getSortCode());
        preparedStmt.setString(7, emp.getNin());
        preparedStmt.setString(8, emp.getTelNo());
        preparedStmt.setString(9, emp.getEmail());
        preparedStmt.execute();
    }

    public Employee getEmployee(int id, Connection c) throws SQLException {
        String query = "SELECT * FROM Employee WHERE EmployeeID = ?";
        PreparedStatement preparedStmt = c.prepareStatement(query);
        preparedStmt.setInt(1, id);
        preparedStmt.execute();
        ResultSet rs = preparedStmt.getResultSet();
        Employee dbEmp = null;
        while (rs.next()) {
            dbEmp = new Employee(rs.getInt("EmployeeID"),
                    rs.getString("Forename"),
                    rs.getString("Surname"),
                    rs.getInt("Salary") * 100,
                    rs.getString("BankAccountNum"),
                    rs.getString("SortCode"),
                    rs.getString("NIN"),
                    rs.getString("EmployeeTelNo"),
                    rs.getString("EmployeeEmail")
            );
        }
        return dbEmp;
    }

    public Collection getAll(Connection c) throws SQLException {
        Collection col = new ArrayList();
        String query = "SELECT * FROM Employee";
        PreparedStatement preparedStmt = c.prepareStatement(query);
        preparedStmt.execute();
        ResultSet rs = preparedStmt.getResultSet();
        while (rs.next()) {
            Employee dbEmp = new Employee(rs.getInt("EmployeeID"),
                    rs.getString("Forename"),
                    rs.getString("Surname"),
                    rs.getInt("Salary") * 100,
                    rs.getString("BankAccountNum"),
                    rs.getString("SortCode"),
                    rs.getString("NIN"),
                    rs.getString("EmployeeTelNo"),
                    rs.getString("EmployeeEmail"));
            col.add(dbEmp);
        }
        return col;

    }
}
