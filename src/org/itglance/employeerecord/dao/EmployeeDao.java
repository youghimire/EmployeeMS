package org.itglance.employeerecord.dao;

import org.itglance.employeerecord.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ramillc on 8/28/2018.
 */public interface EmployeeDao {

         ArrayList<Employee> getAllEmployee();
         void fetchEmployeeDatabase() throws SQLException;
         Employee getEmployee(int index);
         void addEmployee(String name, double salary) throws SQLException;
         void updateEmployee(int index, String name, double salary);
         void deleteEmployee(int index);
}

