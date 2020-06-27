<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="TEST?akcja=addTest" method="post" accept-charset="UTF-8">
    <label>Nazwa testu:</label>        <input type="text" name="testName" > <br/>

    <label>Pytanie:</label>        <input type="text" name="question" > <br/>
    <label>Ilość punktów:</label>    <input type="number" name="points" > <br/>

    <label>answer1</label>    <input type="text" name="answer1" >   <select name="correct1"> <option value="True">True</option> <option value="False">False</option></select> <br/>
    <label>answer2</label>    <input type="text" name="answer2" >   <select name="correct2"> <option value="True">True</option> <option value="False">False</option></select> <br/>
    <label>answer3</label>    <input type="text" name="answer3" >   <select name="correct3"> <option value="True">True</option> <option value="False">False</option></select> <br/>
    <label>answer4</label>    <input type="text" name="answer4" >   <select name="correct4"> <option value="True">True</option> <option value="False">False</option></select> <br/>

    <br/><br/>
    <label>Pytanie:</label>        <input type="text" name="question" > <br/>
    <label>Ilość punktów:</label>    <input type="number" name="points" > <br/>

    <label>answer1</label>    <input type="text" name="answer1" >   <select name="correct1"> <option value="True">True</option> <option value="False">False</option></select> <br/>
    <label>answer2</label>    <input type="text" name="answer2" >   <select name="correct2"> <option value="True">True</option> <option value="False">False</option></select> <br/>
    <label>answer3</label>    <input type="text" name="answer3" >   <select name="correct3"> <option value="True">True</option> <option value="False">False</option></select> <br/>
    <label>answer4</label>    <input type="text" name="answer4" >   <select name="correct4"> <option value="True">True</option> <option value="False">False</option></select> <br/>
    <input type="submit" value="Zarejestruj">
</form>
