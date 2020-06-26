package ti.kontroler;

import ti.Narzedzia;
import ti.model.Baza;
import ti.model.Baza2;
import ti.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "RM", urlPatterns = "/RM")

public class RM extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        ServletContext context = getServletContext();
        HttpSession sesja = request.getSession();


        String komunikat = "Niepoprawne dane";
        String strona = request.getParameter("strona");
        String akcja = request.getParameter("akcja");

        if (akcja == null) akcja ="";


        User uzytkownik = (User) sesja.getAttribute("uzytkownik");
        if (uzytkownik == null) {
            uzytkownik = new User();   // użytkownik
            sesja.setAttribute("uzytkownik", uzytkownik);
        }

        User user = (User) sesja.getAttribute("user");
        if (user == null) {
            user = new User();   // użytkownik
            sesja.setAttribute("user", user);
        }

        Baza2 baza2 = (Baza2)context.getAttribute("baza");
        if (baza2 == null) {
            baza2 = new Baza2();
            context.setAttribute("baza", baza2);
        }

        Baza baza = new Baza();
/*
        Baza baza = (Baza)context.getAttribute("baza2");
        if (baza == null) {
            baza = new Baza();
            context.setAttribute("baza2", baza);
        }
*/
        if(akcja.equals("login")) {

            String login=request.getParameter("log");
            String haslo=request.getParameter("pass");
            //User temp = baza.selectUser(login,haslo);
            User temp = baza.test();
            //RMuzytkownik temp = baza2.zalogujUzytkownika2(login,haslo);
            komunikat = "Zostałeś prawidłowo zalogowany";
            if(temp !=null) {
                if(temp.getPermissions()!=-2){
                    sesja.setAttribute("uzytkownik", temp);
                    user=temp;
                    //uzytkownik=temp;

                }else {
                    komunikat = "Konto zablokowane!";
                }

            }
            else {
                komunikat = "Błędny login lub hasło";
            }


        }
        else if(akcja.equals("ustawienia")){
            String imie = request.getParameter("imie");
            String nazwisko = request.getParameter("nazwisko");


            if(imie==null) imie="";
            if(nazwisko==null) nazwisko="";

            sesja.setAttribute("uzytkownik", uzytkownik);
            //System.out.println(sesja.getAttribute("uzytkownik"));

            komunikat = "Zmieniono dane";
           // response.sendRedirect("index.jsp");

        }

        else if(akcja.equals("uprawnienia")){
            String login = request.getParameter("login");
            String uprawnienia = request.getParameter("uprawnienia");

            komunikat = "Nie można zmienić ustawiań admina";
            if(login==null) login="";
            if(uprawnienia==null) uprawnienia="-1";

            if(!login.equals("admin")) {
                baza2.nadajUprawnienia(login, Integer.parseInt(uprawnienia));
                context.setAttribute("baza", baza2);
                komunikat = "Zmieniono dane";
            }


        }
        else if(akcja.equals("wyloguj")) {
            sesja.setAttribute("uzytkownik", new User());
            komunikat = "Zostałeś prawidłowo wylogowany";
         //   response.sendRedirect("index.jsp");
        }
        else if(akcja.equals("register")){
            String login=request.getParameter("log");
            String haslo=request.getParameter("pass");
            komunikat = "Taki login już istnijej !";
            if(baza2.pobierzUzytkownika(login)==null){
                baza2.dodajUzytkownika(login,haslo,0);
                context.setAttribute("baza", baza2);
                komunikat = "Poprawnie zarejestrowano";
            }
        } else if (akcja.equals("usun")) {
            String login = request.getParameter("login");
            baza2.usunUzytkownika(login);
            context.setAttribute("baza", baza2);
            komunikat = "Usunięto "+login;

        }
        else if(akcja.equals("zablokuj")){
            String login = request.getParameter("login");
            baza2.nadajUprawnienia(login,-2);
            context.setAttribute("baza", baza2);
            komunikat = "Zablokowano"+login;
        }

        else{
            komunikat = "Nieprawidłowe wywołanie";
        }


        String szablon = Narzedzia.pobierzSzablon("komunikat.jsp", context);
        szablon =szablon.replace("[[TRESC]]",komunikat);



        out.println(szablon);
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404, "Strona o podanym adresie nie istnieje");
    }
}
