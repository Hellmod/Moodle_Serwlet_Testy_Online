package ti.kontroler;

import ti.Narzedzia;
import ti.model.Baza;
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

        User user = (User) sesja.getAttribute("user");
        if (user == null) {
            user = new User();   // użytkownik
            sesja.setAttribute("user", user);
        }

       // Baza baza = new Baza();
        Baza baza = (Baza)context.getAttribute("baza");
        if (baza == null) {
            baza = new Baza();
            context.setAttribute("baza", baza);
        }

        if(akcja.equals("login")) {

            String login=request.getParameter("log");
            String haslo=request.getParameter("pass");
            User temp = baza.selectUser(login,haslo);

            komunikat = "Zostałeś prawidłowo zalogowany";
            if(temp !=null) {
                if(temp.getPermissions()!=-2){
                    sesja.setAttribute("user", temp);
                }else
                    komunikat = "Konto zablokowane!";

            }
            else
                komunikat = "Błędny login lub hasło";

        }
        else if(akcja.equals("ustawienia")){
            String imie = request.getParameter("imie");
            String nazwisko = request.getParameter("nazwisko");

            if(imie==null) imie="";
            if(nazwisko==null) nazwisko="";

            //sesja.setAttribute("uzytkownik", uzytkownik);
            //System.out.println(sesja.getAttribute("uzytkownik"));

            komunikat = "Zmieniono dane";
           // response.sendRedirect("index.jsp");

        }
        else if(akcja.equals("wyloguj")) {
            sesja.setAttribute("user", new User());
            komunikat = "Zostałeś prawidłowo wylogowany";
       //   response.sendRedirect("index.jsp");
        }
        else if(akcja.equals("register")){
            String login=request.getParameter("log");
            String haslo=request.getParameter("pass");
            if(baza.loginAvailable(login)){
                if(baza.insertUser(new User(login,haslo,-2)))
                    komunikat = "Poprawnie zarejestrowano";
                else
                    komunikat = "Błąd podczas dodawania użytkownika skontatkurj się z amdinistartorem strony";
            }
            else
                komunikat = "Taki login już istnieje!";
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
