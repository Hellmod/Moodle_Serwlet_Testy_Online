<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="TEST?akcja=addTest" method="post" accept-charset="UTF-8">
    <label>Nazwa testu:</label>        <input type="text" name="testName" value="${testList.get(0).getTestName()}"> <br/>
    <label>Data od:</label>    <input type="datetime-local" name="odData" value="${testList.get(0).getOdData()}"> <br/>
    <label>Data do:</label>    <input type="datetime-local" name="doData" value="${testList.get(0).getDoData()}"> <br/>
    <label>Czas trwania w minutach:</label>    <input type="number" name="ileMin" value="${testList.get(0).getIleMin()}"> <br/>
    <label>Ilość punktów:</label>    <input type="number" name="points" value="${testList.get(0).getPoints()}"> <br/>
<c:forEach var="test" items="${testList}">
    <div>
        <label>Pytanie:</label>   <input type="text" name="question" value="${test.question}"> <br/>
        <label>answer1</label>    <input type="text" name="answer1" value="${test.getAnswer1()}">   <select name="correct1"> <option value="True" <c:if test = "${test.correct1==true}">selected</c:if>>True</option> <option value="False" <c:if test = "${test.correct1==false}">selected</c:if>>False</option></select> <br/>
        <label>answer2</label>    <input type="text" name="answer2" value="${test.getAnswer2()}">   <select name="correct2"> <option value="True" <c:if test = "${test.correct2==true}">selected</c:if>>True</option> <option value="False" <c:if test = "${test.correct2==false}">selected</c:if>>False</option></select> <br/>
        <label>answer3</label>    <input type="text" name="answer3" value="${test.getAnswer3()}">   <select name="correct3"> <option value="True" <c:if test = "${test.correct3==true}">selected</c:if>>True</option> <option value="False" <c:if test = "${test.correct3==false}">selected</c:if>>False</option></select> <br/>
        <label>answer4</label>    <input type="text" name="answer4" value="${test.getAnswer4()}">   <select name="correct4"> <option value="True" <c:if test = "${test.correct4==true}">selected</c:if>>True</option> <option value="False" <c:if test = "${test.correct4==false}">selected</c:if>>False</option></select> <br/>
    </div>
</c:forEach>
<input type="submit" value="Zarejestruj">
</form>