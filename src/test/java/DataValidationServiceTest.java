import org.junit.Assert;
import org.junit.Test;
import pl.damiankotynia.bankingsystem.service.DataValidationService;

public class DataValidationServiceTest {
    @Test
    public void tooShortPeselValidation(){
        Long pesel = DataValidationService.validatePesel("123");
        Long expected = new Long(-1L);
        Assert.assertEquals(expected, pesel);
    }

    @Test
    public void negativePeselValidation(){
        Long pesel = DataValidationService.validatePesel("-12312312312");
        Long expected = new Long(-2L);
        Assert.assertEquals(expected, pesel);
    }

    @Test
    public void notANumberPeselValidation(){
        Long pesel = DataValidationService.validatePesel("wqeqwe123 1231231ed ddwq");
        Long expected = new Long(-3L);
        Assert.assertEquals(expected, pesel);
    }

    @Test
    public void validPeselValidation(){
        Long pesel = DataValidationService.validatePesel("12312312312");
        Long expected = new Long(12312312312L);
        Assert.assertEquals(expected, pesel);
    }

    @Test
    public void validDoubleValidation(){
        Double value = DataValidationService.validateDouble("123");
        Double expected = new Double(123);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void notANumberDoubleValidation(){
        Double value = DataValidationService.validateDouble("qwe");
        Double expected = new Double(-2);
        Assert.assertEquals(expected, value);
    }

    @Test
    public void negativeDoubleValidation(){
        Double value = DataValidationService.validateDouble("-12");
        Double expected = new Double(-1);
        Assert.assertEquals(expected, value);
    }

}
