package com.facundo.bank;

import com.facundo.bank.banks.Account;
import com.facundo.bank.banks.PrivateBank;
import com.facundo.bank.exceptions.LoanAmountNotValidException;
import com.facundo.bank.people.clients.Client;
import com.facundo.bank.people.employee.Analyst;
import com.facundo.bank.people.employee.ManagingDirector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Client client = new Client();
        client.setName("Robert");
        client.setLastName("Rogers");
        client.setAge(30);
        client.setSalary(BigDecimal.valueOf(1500));

        PrivateBank privateBank = new PrivateBank();
        privateBank.setName("Bank of America");
        privateBank.setAddress(new Address("Los Angeles", "California", "Main Street", 8510));
        privateBank.setReserves(BigDecimal.valueOf(100000000));

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(0));
        account.setAccountNumber(15616);
        account.setBank(privateBank);

        client.setBankAccount(account);
        client.makeDeposit(BigDecimal.valueOf(5000));

        Analyst analyst = new Analyst();
        analyst.setName("John");
        analyst.setLastName("Johnson");
        analyst.setAge(27);

        ManagingDirector director = new ManagingDirector();
        director.setAge(53);
        director.setName("Bob");
        director.setLastName("Dylan");

        privateBank.addAnalyst(analyst);
        privateBank.setManagingDirector(director);
        privateBank.newClient(client);

        LOGGER.info(client.toString());

        try {
            analyst.newLoan(client, BigDecimal.valueOf(10000), (short)15, 600);
        } catch (LoanAmountNotValidException e) {
            LOGGER.error(e);
        }

        LOGGER.info("Client account balance: ");
        LOGGER.info(client.getBankAccount().getBalance());

        LOGGER.info("Client loan amount: ");
        LOGGER.info(client.getCreditHistory().getLoans().get(0).getAmount().toString());

        client.payLoan(BigDecimal.valueOf(3000), 600);

        LOGGER.info("Client account balance: ");
        LOGGER.info(client.getBankAccount().getBalance());

        LOGGER.info("Client loan amount: ");
        LOGGER.info(client.getCreditHistory().getLoans().get(0).getAmount().toString());
    }
}