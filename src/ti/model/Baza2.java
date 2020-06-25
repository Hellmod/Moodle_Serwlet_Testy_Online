package ti.model;

import java.util.Arrays;
import java.util.HashMap;

public class Baza2 {
    private HashMap<String, RMuzytkownik> uzytkownicy = new HashMap<>();

    public Baza2(){
        dodajUzytkownika("user","123",0);
        dodajUzytkownika("as","123",1);
        dodajUzytkownika("admin","123",2);
    }

    public RMuzytkownik[] getTab(){

        return null;
    }

    public void dodajUzytkownika(String login, String haslo, int uprawnienia){
        uzytkownicy.put(login,new RMuzytkownik(login,haslo,uprawnienia));
    }

    public void ustawKolor(String login, String kolor){
        RMuzytkownik uzytkownik = uzytkownicy.get(login);
        uzytkownicy.remove(login);
        uzytkownicy.put(login,uzytkownik);
    }

    public void usunUzytkownika(String login){
        uzytkownicy.remove(login);
    }

    public RMuzytkownik pobierzUzytkownika(String login){
        return uzytkownicy.get(login);
    }

    public void nadajUprawnienia(String login, int uprawnienia){
        RMuzytkownik uzytkownik = uzytkownicy.get(login);
        uzytkownik.setUprawnienia(uprawnienia);
        uzytkownicy.remove(login);
        uzytkownicy.put(login,uzytkownik);
    }

    public int zalogujUzytkownika(String login, String haslo){
        int wynik =-1;
        RMuzytkownik uzytkonik = uzytkownicy.get(login);
        if(uzytkonik !=null){
            if(uzytkonik.getHaslo().equals(haslo))
                wynik=uzytkonik.getUprawnienia();
        }
        return wynik;
    }

    public RMuzytkownik zalogujUzytkownika2(String login, String haslo){
        int wynik =-1;
        RMuzytkownik uzytkonik = uzytkownicy.get(login);
        if(uzytkonik !=null){
            if(uzytkonik.getHaslo().equals(haslo))
                wynik=uzytkonik.getUprawnienia();
        }
        return uzytkonik;
    }

    public  String[] pobierzUzytkownikow(){
        String[] wynik = uzytkownicy.keySet().toArray(new String[uzytkownicy.keySet().size()]);
        Arrays.sort(wynik);
        return wynik;
    }

    public HashMap<String, RMuzytkownik> getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(HashMap<String, RMuzytkownik> uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }
}
