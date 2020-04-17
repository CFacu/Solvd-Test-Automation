package com.facundo.bank.people.employee;

import com.facundo.bank.people.Person;

public class Accountant extends Person {

    public Accountant () {
    }

    public void payEmployee(Analyst employee) {
        employee.setCash(employee.getCash().add(employee.getSalary()));
    }

    public void payEmployee(Associate employee) {
        employee.setCash(employee.getCash().add(employee.getSalary()));
    }

    public void payEmployee(VicePresident employee) {
        employee.setCash(employee.getCash().add(employee.getSalary()));
    }

    public void payEmployee(ManagingDirector employee) {
        employee.setCash(employee.getCash().add(employee.getSalary()));
    }
}