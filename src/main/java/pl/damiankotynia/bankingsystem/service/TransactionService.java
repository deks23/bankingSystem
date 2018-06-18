package pl.damiankotynia.bankingsystem.service;


import javafx.scene.control.Alert;
import pl.damiankotynia.bankingsystem.controller.DepositMoneyController;
import pl.damiankotynia.bankingsystem.controller.TransferMoneyController;
import pl.damiankotynia.bankingsystem.controller.WithdrawMoneyController;
import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;
import pl.damiankotynia.bankingsystem.model.User;

import java.util.DoubleSummaryStatistics;

public class TransactionService {
    private Database database;


    public TransactionService(){
        this.database = new DatabaseImpl();
    }


    public boolean withdrawMoney(Long accountNumber, Double cashAmmount, WithdrawMoneyController controller){
        User user = database.findUserById(accountNumber);
        if (user==null){
            controller.showAlert("Błąd", "Podane konto nie istnieje");
            return false;
        }

        if(user.getCash()<cashAmmount){
            controller.showAlert("Błąd", "Na koncie nie ma wystarczającej ilości środków");
            return false;
        }
        user.setCash(user.getCash()-cashAmmount);
        if(!database.saveUserData(user)){
            controller.showAlert("Błąd", "Transakcja została anulowana");
            return false;
        }
        else
            return true;
    }

    public boolean depositMoney(Long accountNumber, Double cashAmmount, DepositMoneyController controller){
        User user = database.findUserById(accountNumber);
        if (user==null){
            controller.showAlert("Błąd", "Podane konto nie istnieje");
            return false;
        }
        user.setCash(user.getCash()+cashAmmount);
        if(!database.saveUserData(user)){
            controller.showAlert("Błąd", "Transakcja została anulowana");
            return false;
        }
        else
            return true;
    }

    public boolean transferMoney(Long sourceAccountNumber, Long targetAccountNumber, Double cashAmmount, TransferMoneyController controller){
        User sourceUser = database.findUserById(sourceAccountNumber);
        if (sourceUser==null){
            controller.showAlert("Błąd", "Podane konto źródłowe nie istnieje");
            return false;
        }
        User targetUser = database.findUserById(targetAccountNumber);
        if (targetUser==null){
            controller.showAlert("Błąd", "Podane konto docelowe nie istnieje");
            return false;
        }

        if(sourceUser.getCash()<cashAmmount){
            controller.showAlert("Błąd", "Na koncie nie ma wystarczającej ilości środków");
            return false;
        }

        sourceUser.setCash(sourceUser.getCash()-cashAmmount);
        targetUser.setCash(targetUser.getCash()+cashAmmount);
        if(!database.saveUserAfterTransfer(sourceUser, targetUser)){
            controller.showAlert("Błąd", "Transakcja została anulowana");
            return false;
        }
        else
            return true;
    }


}
