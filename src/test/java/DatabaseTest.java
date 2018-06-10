import org.junit.Before;
import org.junit.Test;
import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;
import pl.damiankotynia.bankingsystem.model.User;

import java.util.Set;

public class DatabaseTest {
    private Database database;

    @Before
    public void initTests(){
        database = new DatabaseImpl();
    }

    @Test
    public void qwe(){
        User user = new User();
        user.setId(200L);
        user.setCash(123.12);
        user.setAddress("asd qwe");
        user.setPesel(456L);

        database.addUser(user);
    }

    @Test
    public void searchByNameTest(){
        Set<User> qwe = database.findUserByName("damian");
        for (User u : qwe)
            System.out.println(u.toString());
    }

    @Test
    public void searchByAddressTest(){
        Set<User> qwe = database.findByAddress("aasd");
        for (User u : qwe)
            System.out.println(u.toString());
    }
}
