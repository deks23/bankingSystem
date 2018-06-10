package pl.damiankotynia.bankingsystem.service;


import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.model.User;

import java.util.DoubleSummaryStatistics;

public class TransactionService {
    private Database database;
    private InputService inputService;
    public TransactionService(Database database){
        this.database = database;
        this.inputService = new InputService();
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
