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
    public int getMainMenuInput(){

        boolean isCorrect = false;
        int returnValue = 0;
        while(!isCorrect){
            String value = input.nextLine();
            try{
                returnValue = Integer.parseInt(value);
                if(returnValue>0 && returnValue<10)
                    isCorrect = true;
                else
                    System.out.println("Podana wartosc jest niepoprawna");
            }catch (NumberFormatException e){
                System.out.println("Podana wartosc jest niepoprawna");
            }
        }
        return returnValue;
    }

    public boolean getAreYouSure(){
        while (true){
           System.out.println("Czy napewno? (T/N)");
           String value = input.nextLine();
           if(value.length()==1){
               if(value.equals("T")||value.equals("t"))
                   return true;
               else if(value.equals("N")||value.equals("n"))
                   return false;
           }
            System.out.println("Podano niepoprawny symbol");
        }
    }

    public double getDouble(){
        boolean isCorrect = false;
        double returnValue = 0;
        while(!isCorrect){
            String value = input.nextLine();
            try{
                returnValue = Double.parseDouble(value);
                isCorrect = true;
                if(returnValue<0){
                    System.out.println("Liczba ujemna, podaj dodatnia kwote");
                    isCorrect=false;
                }
            }catch (NumberFormatException e){
                System.out.println("Podana wartosc jest niepoprawna");
            }
        }
        return returnValue;
    }

    public long  getPesel(){
        boolean isCorrect = false;
        long returnValue = 0;
        while(!isCorrect){
            String value = input.nextLine();
            try{
                returnValue = Long.parseLong(value);
                isCorrect = true;
                if(returnValue<0){
                    System.out.println("Liczba ujemna, podaj dodatnia kwote");
                    isCorrect=false;
                }
                if(value.length()!=11){
                    isCorrect = false;
                    System.out.println("Numer PESEL musi mieć 11 cyfr");
                }
            }catch (NumberFormatException e){
                System.out.println("Podana wartosc jest niepoprawna");
            }
        }
        return returnValue;
    }
}
