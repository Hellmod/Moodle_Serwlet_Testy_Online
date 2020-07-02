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
        <table>
            <TR><td>${test.getAnswer1()}</td>   <td><select name="answers"> <option value="True">True</option> <option value="False">False</option></select></td></TR>
            <TR><td>${test.getAnswer2()}</td>   <td><select name="answers"> <option value="True">True</option> <option value="False">False</option></select></td></TR>
            <TR><td>${test.getAnswer3()}</td>   <td><select name="answers"> <option value="True">True</option> <option value="False">False</option></select></td></TR>
            <TR><td>${test.getAnswer4()}</td>   <td><select name="answers"> <option value="True">True</option> <option value="False">False</option></select></td></TR>
        </table>
        <input type="submit" value="Dalej"/>
    </form>
<br/>

<br/>

