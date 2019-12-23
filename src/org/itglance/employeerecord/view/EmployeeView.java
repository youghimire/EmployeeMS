package org.itglance.employeerecord.view;

import org.itglance.employeerecord.dao.EmployeeDao;
import org.itglance.employeerecord.dao.EmployeeDaoImpl;

import org.itglance.employeerecord.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by ramillc on 8/28/2018.
 */
public class EmployeeView{

    EmployeeDao myEmployee = new EmployeeDaoImpl();

    Scanner scan = new Scanner(System.in);

    public EmployeeView() throws SQLException {
    }


    ///////////////////////////////// Display Menu Items //////////////////////////////////////////////////

    public void menu() throws SQLException {
        myEmployee.fetchEmployeeDatabase();
        int choice=-1;
        int id;
        int index;
        boolean flag=true;

        do {
            System.out.println("Choose one of the following options: ");
            System.out.println("1. Type '1' to add new Employee.");
            System.out.println("2. Type '2' to get an Employee detail.");
            System.out.println("3. Type '3' to update an Employee record.");
            System.out.println("4. Type '4' to remove an Employee from the record.");
            System.out.println("5. Type '5' to view the Employee list.");
            System.out.println("0. Type '0' to exit.");
            System.out.print("What do you want ? --> ");
            Scanner scan = new Scanner(System.in);
            boolean flag1 = true;
            while (flag1) {
                choice = scan.nextInt();
                if (choice >= 0 && choice < 6) {
                    //flag = selection(s);
                    break;
                }
                else
                    System.out.print("Incorrect choice!! Please provide the correct input -->");

            }

            switch(choice){

                case 1:
                    System.out.print("Enter the name of the Employee : ");
                    String name = scan.next();
                    String finalName = name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
                    System.out.print("Enter the Salary for the Employee: ");
                    double salary = scan.nextDouble();
                    myEmployee.addEmployee(finalName,salary);
                    System.out.println("Successfully added the new employee!!");
                    break;

                case 2:
                    id = askID();
                    index = EmployeeDaoImpl.findID(id);
                    if(index !=-1){
                        display(myEmployee.getEmployee(index));
                    }else
                        errorMessage();
                    break;

                case 3:
                    id = askID();
                    index=EmployeeDaoImpl.findID(id);
                    if(index!=-1){
                        display(EmployeeDaoImpl.employeeDatabase.get(index));
                        System.out.println("You are trying to edit the information of the above Employee...");
                        System.out.print("Enter new name : ");
                        String newName = scan.next();
                        String finalNewName = newName.substring(0,1).toUpperCase()+newName.substring(1).toLowerCase();
                        System.out.print("Enter new salary : ");
                        double newSalary = scan.nextDouble();
                        myEmployee.updateEmployee(index,finalNewName,newSalary);
                        System.out.println("Successfully updated the record of the Employee...");
                    }else
                        errorMessage();
                    break;

                case 4:
                    id = askID();
                    index=EmployeeDaoImpl.findID(id);
                    if(index!=-1){
                        display(EmployeeDaoImpl.employeeDatabase.get(index));
                        System.out.println("Removing the above Employee from the record...");
                        myEmployee.deleteEmployee(index);
                    }else
                        errorMessage();
                    break;

                case 5:
                    ArrayList<Employee> myList = myEmployee.getAllEmployee();
                    displayAll(myList);
                    break;

                case 0:
                    System.exit(0);
                default:
                    System.out.println("Incorrect choice!! Please provide the correct input...");

                }

                while(true)
                {
                    System.out.println("Type any character to continue to the main menu...");
                    if(scan.hasNext())
                        break;
                }


        }while(flag);

    }


    /////////////////////////////// other display methods /////////////////////////////////////////////////

    public void display(Employee e){
        System.out.println("ID : "+e.getId()+"  Name : "+e.getName()+"  Salary : "+e.getSalary());

    }

    public void errorMessage(){
        System.out.println("Sorry! cannot find the requested ID, Please try again...");
    }

    public int askID(){
        System.out.print("Enter the ID of the Employee : ");
        int id =scan.nextInt();
        return id;
    }

     public void displayAll(ArrayList<Employee> myList){
        System.out.println("\u263a================================================\u263a");
        System.out.printf("\u2551 %-7s \u2551 %-7s \u2551 %-15s \u2551 %-10s \u2551\n","Index","ID","Name","Salary");
        System.out.println("\u2551=========\u2551=========\u2551=================\u2551============\u2551");
        for(Employee e: myList) {

            System.out.printf("\u2551 %-7d \u2551 %-7d \u2551 %-15s \u2551 %10.3f \u2551\n", myList.indexOf(e), e.getId(), e.getName(), e.getSalary());
            System.out.println("\u2551=========\u2551=========\u2551=================\u2551============\u2551");
        }
    }


}
