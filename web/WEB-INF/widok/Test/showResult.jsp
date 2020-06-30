<%@page import="ti.model.Baza, ti.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="ti.model.Test" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<br/>
${testList.get(0).getQuestion()}
Wynik: ${points} na możliwych ${testList.get(0).getPoints()}
<c:set var = "i" scope = "request" value = "0"/><br/>
<c:forEach var="test" items="${testList}">

    Pytanie: ${test.getQuestion()}<br/>
    Odpowiedź: ${test.getAnswer1()}<br/>
    Poprawna: ${test.getCorrect1()} twoja: ${answersList.get(i).getAnswer1()}
    <br/><br/>
    Odpowiedź: ${test.getAnswer2()}<br/>
    Poprawna: ${test.getCorrect2()} twoja: ${answersList.get(i).getAnswer2()} <br/>
    <br/><br/>
    Odpowiedź: ${test.getAnswer3()}<br/>
    Poprawna: ${test.getCorrect3()} twoja: ${answersList.get(i).getAnswer3()} <br/>
    <br/><br/>
    Odpowiedź: ${test.getAnswer4()}<br/>
    Poprawna: ${test.getCorrect4()} twoja: ${answersList.get(i).getAnswer4()} <br/>
    <c:set var = "i" scope = "request" value = "${i+1}"/>
    <br/><br/>
</c:forEach>
<br/>

<br/>

