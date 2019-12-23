package org.itglance.employeerecord.main;

import org.itglance.employeerecord.view.EmployeeView;

import java.sql.SQLException;

/**
 * Created by ramillc on 8/28/2018.
 */
public class EmployeeRecord {
    public static void main(String[] args) throws SQLException {

        EmployeeView employeeView = new EmployeeView();
        employeeView.menu();


    }
}
