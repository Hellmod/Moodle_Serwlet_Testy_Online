<%@page import="ti.model.Baza, ti.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="ti.model.Test" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${testList.get(0).getQuestion()}
Wynik: ${points} na możliwych ${testList.get(0).getPoints()}

<c:forEach var="test" items="${testList}">
    <c:forEach var="answer" items="${answersList}">
        <c:if test="${ test.getQuestionId() == answer.getQuestionId() }">
            Pytanie: ${test.getQuestion()}<br/>
            Odpowiedź: ${test.getAnswer1()}<br/>
            Poprawna: ${test.getCorrect1()} twoja: ${answer.getAnswer1()}
            <br/><br/>
            Odpowiedź: ${test.getAnswer2()}<br/>
            Poprawna: ${test.getCorrect2()} twoja: ${answer.getAnswer2()} <br/>
            <br/><br/>
            Odpowiedź: ${test.getAnswer3()}<br/>
            Poprawna: ${test.getCorrect3()} twoja: ${answer.getAnswer3()} <br/>
            <br/><br/>
            Odpowiedź: ${test.getAnswer4()}<br/>
            Poprawna: ${test.getCorrect4()} twoja: ${answer.getAnswer4()} <br/>
            <br/><br/>
        </c:if>
    </c:forEach>
</c:forEach>


