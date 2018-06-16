package pl.damiankotynia.bankingsystem.service;

public class DataValidationService {
    public static Long validatePesel(String pesel){
            Long returnValue = 0L;
            pesel = pesel.trim();
            try{
                returnValue = Long.parseLong(pesel);
                if(returnValue<0)
                    return -2L;
                if(pesel.length()!=11)
                    return -1L;
            }catch (NumberFormatException e){
                return -3L;
            }
        return returnValue;
    }

    public static Double validateDouble(String value){
        Double returnValue = -1.0;
        try{
            returnValue = Double.parseDouble(value);
            if(returnValue<0){
                return -1.0;
            }
        }catch (NumberFormatException e){
            return -2.0;
        }
        return returnValue;
    }

    public static Long validateLong(String value){
        Long returnValue;
        try{
            returnValue = Long.parseLong(value);
            if (returnValue<=0)
                return -1L;
        }catch (NumberFormatException e){
            return -1L;
        }
        return returnValue;
    }
}
