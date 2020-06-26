package ti.model;

import java.util.Arrays;
import java.util.HashMap;

public class Baza2 {
    private HashMap<String, User> uzytkownicy = new HashMap<>();

    public Baza2(){
        dodajUzytkownika("user","123",0);
        dodajUzytkownika("as","123",1);
        dodajUzytkownika("admin","123",2);
    }

    public User[] getTab(){

        return null;
    }

    public void dodajUzytkownika(String login, String haslo, int uprawnienia){
        uzytkownicy.put(login,new User(login,haslo,uprawnienia));
    }

    public void ustawKolor(String login, String kolor){
        User uzytkownik = uzytkownicy.get(login);
        uzytkownicy.remove(login);
        uzytkownicy.put(login,uzytkownik);
    }

    public void usunUzytkownika(String login){
        uzytkownicy.remove(login);
    }

    public User pobierzUzytkownika(String login){
        return uzytkownicy.get(login);
    }

    public void nadajUprawnienia(String login, int uprawnienia){
        User uzytkownik = uzytkownicy.get(login);
        uzytkownik.setPermissions(uprawnienia);
        uzytkownicy.remove(login);
        uzytkownicy.put(login,uzytkownik);
    }

    public int zalogujUzytkownika(String login, String haslo){
        int wynik =-1;
        User uzytkonik = uzytkownicy.get(login);
        if(uzytkonik !=null){
            if(uzytkonik.getPassword().equals(haslo))
                wynik=uzytkonik.getPermissions();
        }
        return wynik;
    }

    public User zalogujUzytkownika2(String login, String haslo){
        int wynik =-1;
        User uzytkonik = uzytkownicy.get(login);
        if(uzytkonik !=null){
            if(uzytkonik.getPassword().equals(haslo))
                wynik=uzytkonik.getPermissions();
        }
        return uzytkonik;
    }

    public  String[] pobierzUzytkownikow(){
        String[] wynik = uzytkownicy.keySet().toArray(new String[uzytkownicy.keySet().size()]);
        Arrays.sort(wynik);
        return wynik;
    }

    public HashMap<String, User> getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(HashMap<String, User> uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }
}
