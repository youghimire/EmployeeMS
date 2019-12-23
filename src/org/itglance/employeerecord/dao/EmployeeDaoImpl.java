package org.itglance.employeerecord.dao;
import org.itglance.employeerecord.model.Employee;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.sql.*;
/**
 * Created by ramillc on 8/28/2018.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    public static ArrayList<Employee> employeeDatabase = new ArrayList<Employee>();

        public void fetchEmployeeDatabase() throws SQLException {

            Connection con = DriverManager.getConnection(url,uname,pass);

            Statement st= con.createStatement();
            String query = "select * from ouremployee";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                double salary = rs.getDouble(3);
                employeeDatabase.add(new Employee(name, salary));

            }
            st.close();
            con.close();

    }

    public EmployeeDaoImpl() throws SQLException {


    }

    //////////////////////////////// jdbc connection and statements////////////////////////////////////////

    static String url = "jdbc:mysql://localhost:3306/employee";
    static String uname = "root";
    static String pass = "root";


    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void addData(int id, String name, double salary) throws SQLException {
        Connection con = DriverManager.getConnection(url,uname,pass);
        Statement st = con.createStatement();
        String query = "insert into ouremployee values ("+id+",'"+name+"',"+salary+")";
        int count = st.executeUpdate(query);
        System.out.println(count+" rows affected...");
        st.close();
        con.close();

    }




    /////////////////////////////////////////Search and return Index for the given id////////////////////////////////////////////
     public static int findID(int id) {

        Iterator<Employee> findIndex = employeeDatabase.iterator();
        while (findIndex.hasNext()) {
            Employee myObj = (Employee) findIndex.next();
            if (myObj.getId() == id) {
                return employeeDatabase.indexOf(myObj);
            }
        }
        return -1;
    }
////////////////////////////////////CRUD Methods////////////////////////////////////////////////

    public ArrayList<Employee> getAllEmployee() {
        return employeeDatabase;
    }


    public Employee getEmployee(int index) {
            return employeeDatabase.get(index);
    }


    public void addEmployee(String name, double salary) throws SQLException {
         employeeDatabase.add(new Employee(name,salary));
         int id = employeeDatabase.get(employeeDatabase.size()-1).getId();
         addData(id,name,salary);
    }


    public void updateEmployee(int index,String name, double salary) {
        employeeDatabase.get(index).setName(name);
        employeeDatabase.get(index).setSalary(salary);
    }


    public void deleteEmployee(int index) {
        employeeDatabase.remove(index);
    }

}
