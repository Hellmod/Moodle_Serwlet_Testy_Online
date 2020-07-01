<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="TEST?akcja=addTest" method="post" accept-charset="UTF-8">
    <label>Nazwa testu:</label>        <input type="text" name="testName" > <br/>
    <label>Data od:</label>    <input type="datetime-local" name="odData" > <br/>
    <label>Data do:</label>    <input type="datetime-local" name="doData" > <br/>
    <label>Czas trwania w minutach:</label>    <input type="number" name="ileMin" > <br/>
    <label>Ilość punktów:</label>    <input type="number" name="points" > <br/>
<div id="questionList">
    <div class='question' >
        <label>Pytanie:</label>        <input type="text" name="question" > <br/>
        <label>answer1</label>    <input type="text" name="answer1" >   <select name="correct1"> <option value="True">True</option> <option value="False">False</option></select> <br/>
        <label>answer2</label>    <input type="text" name="answer2" >   <select name="correct2"> <option value="True">True</option> <option value="False">False</option></select> <br/>
        <label>answer3</label>    <input type="text" name="answer3" >   <select name="correct3"> <option value="True">True</option> <option value="False">False</option></select> <br/>
        <label>answer4</label>    <input type="text" name="answer4" >   <select name="correct4"> <option value="True">True</option> <option value="False">False</option></select> <br/>
    </div>
</div>

    <input type="submit" value="Zarejestruj">

</form>
<button onclick="add()">Dodaj pytanie</button>
<button onclick="delet()">Usuń pytanie</button>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    function add() {
        $('#questionList').append("    <div class='question' >\n" +
            "        <label>Pytanie:</label>        <input type=\"text\" name=\"question\" > <br/>\n" +
            "        <label>answer1</label>    <input type=\"text\" name=\"answer1\" >   <select name=\"correct1\"> <option value=\"True\">True</option> <option value=\"False\">False</option></select> <br/>\n" +
            "        <label>answer2</label>    <input type=\"text\" name=\"answer2\" >   <select name=\"correct2\"> <option value=\"True\">True</option> <option value=\"False\">False</option></select> <br/>\n" +
            "        <label>answer3</label>    <input type=\"text\" name=\"answer3\" >   <select name=\"correct3\"> <option value=\"True\">True</option> <option value=\"False\">False</option></select> <br/>\n" +
            "        <label>answer4</label>    <input type=\"text\" name=\"answer4\" >   <select name=\"correct4\"> <option value=\"True\">True</option> <option value=\"False\">False</option></select> <br/>\n" +
            "    </div>");
    }

    function delet() {
        $("#questionList").children("div[class=question]:last").remove();
        //$('#questionList #question:last').remove();
    }


</script>
