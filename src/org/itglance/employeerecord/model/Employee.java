package org.itglance.employeerecord.model;

/**
 * Created by ramillc on 8/28/2018.
 */
public class Employee {



    private int id;
    private String name;
    private double salary;
    static int generateId = 0;

///////////////////////////Constructor and Getter,Setter methods////////////////////////////////////////

   public Employee(String name, double salary){
        id = ++generateId;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


}
