package SQLlight;

import ti.model.Baza;
import ti.model.User;

import java.util.List;

public class BaseTest {

    public static void main(String[] args) {
        Baza b = new Baza();
        b.insertUser(new User(1,"as","123",1));


        List<User> rekordy = b.selectUzytkownik("as","123");


        System.out.println("Lista uzytkowników:");
        for(User k: rekordy)
            System.out.println(k.getId()+" "+k.getLogin()+" "+k.getPassword()+" "+k.getPermissions());
        b.closeConnection();
    }
}