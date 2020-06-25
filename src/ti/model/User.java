package ti.model;

public class User {
    private int id = -1;
    private String login = "";
    private String password = "";
    private int permissions = -1;

    // -1 użytkownik niezalogowany
    // 1 użytkownik zalogowany
    // 2 administrator

    public User(){

    }
    public User(String login, int permissions){
        this.permissions = permissions;
        this.login = login;
    }

    public User(String login, String haslo, int permissions){
        setLogin(login);
        setPassword(haslo);
        setPermissions(permissions);
    }

    public User(int id, String login, String password, int permissions) {
        this.id=id;
        this.login = login;
        this.password = password;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }
}