<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ti.model.Test" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="TEST?akcja=editTest" method="post" accept-charset="UTF-8">
    <input type="hidden" name="testId" value="${testList.get(0).getTestId()}">
    <label>Nazwa testu:</label>        <input type="text" name="testName" value="${testList.get(0).getTestName()} required"> <br/>
    <label>Data od:</label>    <input type="datetime-local" name="odData" value="${Test.ft.format(testList.get(0).getOdData())} required"> <br/>
    <label>Data do:</label>    <input type="datetime-local" name="doData" value="${Test.ft.format(testList.get(0).getDoData())} required"> <br/>
    <label>Czas trwania w minutach:</label>    <input type="number" name="ileMin" value="${testList.get(0).getIleMin()} required"> <br/>
    <label>Ilość punktów:</label>    <input type="number" name="points" value="${testList.get(0).getPoints()}" required> <br/>
<c:forEach var="test" items="${testList}">
    <div>
        <input type="hidden" name="questionId" value="${test.questionId}">
        <label>Pytanie:</label>   <input type="text" name="question" value="${test.question}" required> <br/>
        <label>answer1</label>    <input type="text" name="answer1" value="${test.getAnswer1()} required">   <select name="correct1"> <option value="True" <c:if test = "${test.correct1==true}">selected</c:if>>True</option> <option value="False" <c:if test = "${test.correct1==false}">selected</c:if>>False</option></select> <br/>
        <label>answer2</label>    <input type="text" name="answer2" value="${test.getAnswer2()} required">   <select name="correct2"> <option value="True" <c:if test = "${test.correct2==true}">selected</c:if>>True</option> <option value="False" <c:if test = "${test.correct2==false}">selected</c:if>>False</option></select> <br/>
        <label>answer3</label>    <input type="text" name="answer3" value="${test.getAnswer3()} required">   <select name="correct3"> <option value="True" <c:if test = "${test.correct3==true}">selected</c:if>>True</option> <option value="False" <c:if test = "${test.correct3==false}">selected</c:if>>False</option></select> <br/>
        <label>answer4</label>    <input type="text" name="answer4" value="${test.getAnswer4()} required">   <select name="correct4"> <option value="True" <c:if test = "${test.correct4==true}">selected</c:if>>True</option> <option value="False" <c:if test = "${test.correct4==false}">selected</c:if>>False</option></select> <br/>
    </div>
</c:forEach>
<input type="submit" value="Zarejestruj">
</form>