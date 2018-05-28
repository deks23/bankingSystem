package pl.damiankotynia.bankingsystem.service;


import java.util.Scanner;

public class InputService {

    private Scanner input;

    public InputService(){
        input = new Scanner(System.in);
    }

    public String getString(){
        String returnValue = input.nextLine();
        return returnValue;
    }

    public long getLong(){
        boolean isCorrect = false;
        long returnValue = 0;
        while(!isCorrect){
            String value = input.nextLine();
            try{
                returnValue = Long.parseLong(value);
                isCorrect = true;
            }catch (NumberFormatException e){
                System.out.println("Podana wartosc jest niepoprawna");
            }
        }
        return returnValue;
    }
}
