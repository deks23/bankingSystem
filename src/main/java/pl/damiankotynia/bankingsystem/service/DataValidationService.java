package pl.damiankotynia.bankingsystem.service;

public class DataValidationService {
    public static Long validatePesel(String pesel){
            Long returnValue = 0L;
            pesel = pesel.trim();
            try{
                returnValue = Long.parseLong(pesel);
                if(returnValue<0)
                    return 0L;
                if(pesel.length()!=11)
                    return 0L;
            }catch (NumberFormatException e){
                return 0L;
            }
        return returnValue;
    }
}
