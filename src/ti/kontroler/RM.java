package ti.kontroler;

import ti.Narzedzia;
import ti.model.Baza2;
import ti.model.RMuzytkownik;

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


        RMuzytkownik uzytkownik = (RMuzytkownik) sesja.getAttribute("uzytkownik");
        if (uzytkownik == null) {
            uzytkownik = new RMuzytkownik();   // użytkownik
            sesja.setAttribute("uzytkownik", uzytkownik);
        }

        Baza2 baza = (Baza2)context.getAttribute("baza");
        if (baza == null) {
            baza = new Baza2();
            context.setAttribute("baza", baza);
        }







        if(akcja.equals("login")) {

            String login=request.getParameter("log");
            String haslo=request.getParameter("pass");
            RMuzytkownik temp = baza.zalogujUzytkownika2(login,haslo);
            komunikat = "Zostałeś prawidłowo zalogowany";
            if(temp !=null) {
                if(temp.getUprawnienia()!=-2){
                    sesja.setAttribute("uzytkownik", temp);
                    uzytkownik=temp;
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

            int wiek = Narzedzia.parsujInteger(request.getParameter("wiek"), -1);

            uzytkownik.setImie(imie);
            uzytkownik.setNazwisko(nazwisko);
            uzytkownik.setWiek(wiek);

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
                baza.nadajUprawnienia(login, Integer.parseInt(uprawnienia));
                context.setAttribute("baza", baza);
                komunikat = "Zmieniono dane";
            }


        }
        else if(akcja.equals("wyloguj")) {
            sesja.setAttribute("uzytkownik", new RMuzytkownik());
            komunikat = "Zostałeś prawidłowo wylogowany";
         //   response.sendRedirect("index.jsp");
        }
        else if(akcja.equals("register")){
            String login=request.getParameter("log");
            String haslo=request.getParameter("pass");
            komunikat = "Taki login już istnijej !";
            if(baza.pobierzUzytkownika(login)==null){
                baza.dodajUzytkownika(login,haslo,0);
                context.setAttribute("baza", baza);
                komunikat = "Poprawnie zarejestrowano";
            }
        } else if (akcja.equals("usun")) {
            String login = request.getParameter("login");
            baza.usunUzytkownika(login);
            context.setAttribute("baza", baza);
            komunikat = "Usunięto "+login;

        }
        else if(akcja.equals("zablokuj")){
            String login = request.getParameter("login");
            baza.nadajUprawnienia(login,-2);
            context.setAttribute("baza", baza);
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
