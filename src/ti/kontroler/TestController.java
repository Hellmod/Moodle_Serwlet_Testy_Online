package ti.kontroler;

import ti.Narzedzia;
import ti.model.Answers;
import ti.model.Baza;
import ti.model.Test;
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
import java.util.Collections;
import java.util.List;


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
            String odData = request.getParameter("odData");
            String doData = request.getParameter("doData");
            String ileMin = request.getParameter("ileMin");
            String points = request.getParameter("points");

            String[] question = request.getParameterValues("question");

            String[] answer1 = request.getParameterValues("answer1");
            String[] answer2 = request.getParameterValues("answer2");
            String[] answer3 = request.getParameterValues("answer3");
            String[] answer4 = request.getParameterValues("answer4");

            String[] correct1 = request.getParameterValues("correct1");
            String[] correct2 = request.getParameterValues("correct2");
            String[] correct3 = request.getParameterValues("correct3");
            String[] correct4 = request.getParameterValues("correct4");

            if (user.getPermissions() == 2) {
                if (baza.addQuestion(testName, odData, doData, ileMin, question, points, answer1, answer2, answer3, answer4, correct1, correct2, correct3, correct4))
                    komunikat = "Dodano pytanie";
                else
                    komunikat = "Błąd podczas dodawanie pytania skontaktuj się z administratorem";
            } else komunikat = "Nie masz uprawnień do dodania testu";
        } else if (akcja.equals("addUserToTest")) {

            String[] userInTest = request.getParameterValues("listToadd");
            if (user.getPermissions() == 2) {
                String testId = request.getParameter("testId");
                if (baza.addUserToTest(userInTest, testId))
                    komunikat = "Dodano użytkowników do stestu";
                else
                    komunikat = "Błąd podczas dodawanie użytkowników skontaktuj się z administratorem";
            } else komunikat = "Nie masz uprawnień do dodania użytkowników";
        } else if (akcja.equals("solveTest")) {
            String[] answersTab = request.getParameterValues("answers");
            Test test = (Test) sesja.getAttribute("test");
            Answers answers = new Answers.AnswersBuilder()
                    .answer1(Boolean.parseBoolean(answersTab[0]))
                    .answer2(Boolean.parseBoolean(answersTab[1]))
                    .answer3(Boolean.parseBoolean(answersTab[2]))
                    .answer4(Boolean.parseBoolean(answersTab[3]))
                    .questionId(test.getQuestionId())
                    .userId(user.getId())
                    .build();
            if (baza.addAnswers(answers)) {
                List<Test> testList = (List<Test>) sesja.getAttribute("testList");
                List<Answers> answersList = (List<Answers>) sesja.getAttribute("answersList");
                deleteAnswerQuestions(testList, answersList);
                if (!testList.isEmpty()) {
                    test = testList.get(0);
                    sesja.setAttribute("testList", testList);
                    sesja.setAttribute("test", test);
                    testList.remove(test);
                    response.sendRedirect("index.jsp?strona=Test/solveTest");
                } else {
                    baza.lockTest(String.valueOf(test.getTestId()), user.getId());
                    komunikat = "Test został już rozwiązany";
                }
            } else
                komunikat = "Błąd podczas dodawanie odpowiedzi skontaktuj się z administratorem";

        } else if (akcja.equals("startTest")) {
            String testId = request.getParameter("testId");
            if (baza.isPermission(testId, user.getId())) {
                if (!baza.isStarted(testId, user.getId()))
                    baza.setStartDate(testId, user.getId());

                List<Test> testList = baza.selectTests(user.getId(), Integer.parseInt(testId));
                List<Answers> answersList = baza.selectAnswers(user.getId(), Integer.parseInt(testId));
                deleteAnswerQuestions(testList, answersList);
                Collections.shuffle(testList);
                if (!testList.isEmpty()) {
                    Test test = testList.get(0);
                    sesja.setAttribute("testList", testList);
                    sesja.setAttribute("answersList", answersList);

                    sesja.setAttribute("test", test);
                    testList.remove(test);

                    response.sendRedirect("index.jsp?strona=Test/solveTest");//&testId="+test.getQuestId()+"&quesrNum="+test.getQuestNum());
                } else {
                    baza.lockTest(testId, user.getId());
                    komunikat = "Test został już rozwiązany";
                }
            } else
                komunikat = "Nie możesz już rozwiązać testu";

        } else {
            komunikat = "Nieprawidłowe wywołanie";
        }


        String szablon = Narzedzia.pobierzSzablon("komunikat.jsp", context);
        szablon = szablon.replace("[[TRESC]]", komunikat);


        out.println(szablon);
        out.close();

    }

    private void deleteAnswerQuestions(List<Test> testList, List<Answers> answersList) {
        for (Answers answer : answersList) {
            for (Test test : testList)
                if (test.getQuestionId() == answer.getQuestionId()) {
                    testList.remove(test);
                }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404, "Strona o podanym adresie nie istnieje");
    }
}
