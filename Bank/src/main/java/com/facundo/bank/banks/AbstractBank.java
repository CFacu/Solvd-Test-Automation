package com.facundo.bank.banks;

import com.facundo.bank.Address;
import com.facundo.bank.people.clients.Client;
import com.facundo.bank.people.employee.Analyst;
import com.facundo.bank.people.employee.Associate;
import com.facundo.bank.people.employee.ManagingDirector;
import com.facundo.bank.people.employee.VicePresident;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractBank {
    private Address address;
    private String name;
    private BigDecimal reserves;
    private List<Client> clients = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Analyst> analystsList = new ArrayList<>();
    private List<Associate> associatesList = new ArrayList<>();
    private ManagingDirector managingDirector;
    private VicePresident vicePresident;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public BigDecimal getReserves() {
        return reserves;
    }

    public void setReserves(BigDecimal reserves) {
        this.reserves = reserves;
    }

    public List<Analyst> getAnalystsList() {
        return analystsList;
    }

    public void setAnalystsList(List<Analyst> analystsList) {
        this.analystsList = analystsList;
    }

    public List<Associate> getAssociatesList() {
        return associatesList;
    }

    public void setAssociatesList(List<Associate> associatesList) {
        this.associatesList = associatesList;
    }

    public ManagingDirector getManagingDirector() {
        return managingDirector;
    }

    public void setManagingDirector(ManagingDirector managingDirector) {
        this.managingDirector = managingDirector;
    }

    public VicePresident getVicePresident() {
        return vicePresident;
    }

    public void setVicePresident(VicePresident vicePresident) {
        this.vicePresident = vicePresident;
    }

    public void addAnalyst(Analyst analyst) {
        this.analystsList.add(analyst);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        AbstractBank bank = (AbstractBank) obj;

        return this.name == bank.getName() && name.equals(bank.getName());
    }
}
