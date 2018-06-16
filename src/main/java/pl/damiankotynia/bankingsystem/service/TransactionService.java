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
    private InputService inputService;
    public TransactionService(Database database){
        this.database = database;
        this.inputService = new InputService();
    }

    public TransactionService(){
        this.database = new DatabaseImpl();
    }

    public boolean transferMoney(){
        System.out.println("Podaj numer konta uzytkownika ktory przelewa pieniadze");
        long fromUserAccount = inputService.getLong();

        System.out.println("Podaj numer konta na ktore maja byc przelane pieniadze");
        long toUserAccount = inputService.getLong();

        System.out.println("Podaj kwote");
        Double cash = inputService.getDouble();

        User fromUser = findUser(fromUserAccount);
        User toUser = findUser(toUserAccount);
        cash = roundCash(cash);
        if(fromUser==null){
            System.out.println("Nie znaleziono użytkownika z numerem konta: "+ fromUserAccount);
            return false;
        }
        if(toUser==null){
            System.out.println("Nie znaleziono użytkownika z numerem konta: "+ toUserAccount);
            return false;
        }
        if(fromUser.getCash()<cash){
            System.out.println("Brak wystarczajacych srodkow na koncie o numerze: "+ fromUser.getId());
            return false;
        }else{
            if(inputService.getAreYouSure()) {
                fromUser.setCash(fromUser.getCash() - cash);
                toUser.setCash(toUser.getCash() + cash);
                if(database.saveUserData(fromUser) && database.saveUserData(toUser))
                    return true;
                return false;
            }else {
                System.out.println("Anuluje operacje");
                return false;
            }
        }
    }

    public boolean withdrawMoney(){
        User user;

        System.out.println("Podaj numer konta z którego będą wypłacane pieniądze");
        long userAccount = inputService.getLong();
        user = findUser(userAccount);
        if(user==null){
            System.out.println("Nie znaleziono użytkownika z numerem konta: " + userAccount);
            return false;
        }

        System.out.println("Podaj kwote");
        Double cash = inputService.getDouble();
        if(user.getCash()<cash){
            System.out.println("Brak wystarczajacych srodkow na koncie o numerze: "+ user.getId());
            return false;
        }else{
            if(inputService.getAreYouSure()){
                user.setCash(user.getCash()-cash);
                if(database.saveUserData(user))
                    return true;
                return false;
            }
            else{
                System.out.println("Anuluję operację");
                return false;
            }
        }
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

    public boolean depositMoney(){
        User user;

        System.out.println("Podaj numer konta na które będą wpłacane pieniądze");
        long userAccount = inputService.getLong();
        user = findUser(userAccount);
        if(user==null){
            System.out.println("Nie znaleziono użytkownika z numerem konta: "+ userAccount);
            return false;
        }

        System.out.println("Podaj kwote");
        Double cash = inputService.getDouble();
        if(inputService.getAreYouSure()) {
            user.setCash(user.getCash() + cash);
            if(database.saveUserData(user))
                return true;
            return false;
        }else{
            System.out.println("Anuluję operację");
            return false;
        }
    }



    private User findUser(Long accountNumber){
        User user = database.findUserById(accountNumber);
        if(user==null)
            return null;
        else
            return user;
    }

    private double roundCash(double cash){
        cash *=100;
        long qwe = Math.round(cash);
        cash = (double)qwe/100;
        return cash;
    }
}
