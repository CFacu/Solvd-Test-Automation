package com.facundo.bank.people.employee;

import com.facundo.bank.enums.Salary;
import com.facundo.bank.people.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VicePresident extends Person {
    private List<Analyst> inChargeOfAnalysts = new ArrayList<>();
    private List<Associate> inChargeOfAssociates = new ArrayList<>();
    private Salary salary = Salary.VICE_PRESIDENT;

    public VicePresident() {
    }

    public VicePresident(List<Analyst> inChargeOfAnalysts, List<Associate> inChargeOfAssociates) {
        this.inChargeOfAnalysts = inChargeOfAnalysts;
        this.inChargeOfAssociates = inChargeOfAssociates;
    }

    public List<Analyst> getInChargeOfAnalysts() {
        return inChargeOfAnalysts;
    }

    public void setInChargeOfAnalysts(List<Analyst> inChargeOfAnalysts) {
        this.inChargeOfAnalysts = inChargeOfAnalysts;
    }

    public List<Associate> getInChargeOfAssociates() {
        return inChargeOfAssociates;
    }

    public void setInChargeOfAssociates(List<Associate> inChargeOfAssociates) {
        this.inChargeOfAssociates = inChargeOfAssociates;
    }

    public BigDecimal getSalary() {
        return salary.getSalary();
    }

    public void manageAnalysts(Analyst analyst){}
    public void manageAssociate(Associate associate){}
}
