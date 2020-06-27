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


@WebServlet(name = "TEST", urlPatterns = "/TEST")

public class TestController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();
        HttpSession sesja = request.getSession();


        String komunikat = "Niepoprawne dane";
        String strona = request.getParameter("strona");
        String akcja = request.getParameter("akcja");

        if (akcja == null) akcja = "";

        User user = (User) sesja.getAttribute("user");
        if (user == null) {
            user = new User();   // użytkownik
            sesja.setAttribute("user", user);
        }

        Baza baza = (Baza) context.getAttribute("baza2");
        if (baza == null) {
            baza = new Baza();
            context.setAttribute("baza2", baza);
        }


        if (akcja.equals("addTest")) {
            String question = request.getParameter("question");
            String points = request.getParameter("points");

            if (question == null) question = "";
            if (points == null) points = "-1";

            if (user.getPermissions() == 2) {
                if (baza.addQuestion(question, Integer.parseInt(points)))
                    komunikat = "Dodano pytanie";
                else
                    komunikat = "Błąd podczas dodawanie pytania skontaktuj się z administratorem";
            }
        } else {
            komunikat = "Nieprawidłowe wywołanie";
        }


        String szablon = Narzedzia.pobierzSzablon("komunikat.jsp", context);
        szablon = szablon.replace("[[TRESC]]", komunikat);


        out.println(szablon);
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404, "Strona o podanym adresie nie istnieje");
    }
}
