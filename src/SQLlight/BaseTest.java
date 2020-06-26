package SQLlight;

import ti.model.Baza;
import ti.model.User;

public class BaseTest {

    public static void main(String[] args) {
        Baza b = new Baza();
      //  b.insertUser(new User(1,"as","123",1));


        //List<User> rekordy = b.selectUsers();
        User user = b.selectUser("as","123");
        //User user = b.test();
        System.out.println(user.getId()+" "+user.getLogin()+" "+user.getPassword()+" "+user.getPermissions());
/*
        System.out.println("Lista uzytkowników:");
        for(User k: rekordy)
            System.out.println(k.getId()+" "+k.getLogin()+" "+k.getPassword()+" "+k.getPermissions());
        b.closeConnection();

 */
    }
}