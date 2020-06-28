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

        Baza baza = (Baza) context.getAttribute("baza");
        if (baza == null) {
            baza = new Baza();
            context.setAttribute("baza", baza);
        }


        if (akcja.equals("addTest")) {
            String testName = request.getParameter("testName");
            String[] question = request.getParameterValues("question");
            String[] points = request.getParameterValues("points");

            String[] answer1 = request.getParameterValues("answer1");
            String[] answer2 = request.getParameterValues("answer2");
            String[] answer3 = request.getParameterValues("answer3");
            String[] answer4 = request.getParameterValues("answer4");

            String[] correct1 = request.getParameterValues("correct1");
            String[] correct2 = request.getParameterValues("correct2");
            String[] correct3 = request.getParameterValues("correct3");
            String[] correct4 = request.getParameterValues("correct4");

            if (user.getPermissions() == 2) {
                if (baza.addQuestion(testName, question, points, answer1, answer2, answer3, answer4, correct1, correct2, correct3, correct4))
                    komunikat = "Dodano pytanie";
                else
                    komunikat = "Błąd podczas dodawanie pytania skontaktuj się z administratorem";
            } else komunikat = "Nie masz uprawnień do dodania testu";
        }
        else if (akcja.equals("addUserToTest")) {

            String[] userInTest = request.getParameterValues("listToadd");
            if (user.getPermissions() == 2) {
                String testId = request.getParameter("testId");
                if (baza.addUserToTest(userInTest,testId ))
                    komunikat = "Dodano użytkowników do stestu";
                else
                    komunikat = "Błąd podczas dodawanie użytkowników skontaktuj się z administratorem";
            } else komunikat = "Nie masz uprawnień do dodania użytkowników";
        }
        else {
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
