<%@page import="ti.model.Baza, ti.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="ti.model.Test" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Baza baza = (Baza) pageContext.getServletContext().getAttribute("baza");
    User user = (User) session.getAttribute("user");

    Test test = (Test) session.getAttribute("test");//baza.selectTest(user.getId(),request.getParameter("quesrNum"),request.getParameter("testId"));
    session.setAttribute("test",test);

%>
<br/>
    <form action="TEST?akcja=solveTest" method="post" accept-charset="UTF-8">
        ${test.getQuestion()} <br/>
        ${test.getAnswer1()} <select name="answers"> <option value="True">True</option> <option value="False">False</option></select> <br/>
        ${test.getAnswer2()} <select name="answers"> <option value="True">True</option> <option value="False">False</option></select> <br/>
        ${test.getAnswer3()} <select name="answers"> <option value="True">True</option> <option value="False">False</option></select> <br/>
        ${test.getAnswer4()} <select name="answers"> <option value="True">True</option> <option value="False">False</option></select> <br/>
        <input type="submit" value="Dalej"/>
    </form>
<br/>

<br/>

