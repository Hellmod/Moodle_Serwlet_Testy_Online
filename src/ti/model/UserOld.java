package ti.model;

import ti.Narzedzia;

public class UserOld {
    private String login = "";
    private String haslo = "";
    private String imie = "";
    private String nazwisko = "";
    private int wiek =-1;
    private int uprawnienia = -1;

    // -1 użytkownik niezalogowany
    // 1 użytkownik zalogowany
    // 2 administrator

    public UserOld(){

    }
    public UserOld(String login, int uprawnienia){
        this.uprawnienia = uprawnienia;
        this.login = login;
    }

    public UserOld(String login, String haslo, int uprawnienia){
        setLogin(login);
        setHaslo(haslo);
        setUprawnienia(uprawnienia);
    }


    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = Narzedzia.filtruj(imie);
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = Narzedzia.filtruj(nazwisko);
    }

    public int getWiek() { return wiek; }

    public void setWiek(int wiek) {
        if(wiek>=0)
            this.wiek = wiek;
    }

    public String getWieks(){
        if (wiek<0)
            return "";
        return ""+wiek;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public int getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(int uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

}