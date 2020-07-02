<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="TEST?akcja=addTest" method="post" accept-charset="UTF-8">
    <table>
        <TR><td><label>Nazwa testu:</label></td>        <td><input type="text" name="testName" maxlength="255" required></td> </TR>
        <TR><td><label>Data od:</label></td>    <td><input type="datetime-local" name="odData" required></td> </TR>
        <TR><td><label>Data do:</label></td>    <td><input type="datetime-local" name="doData" required></td> </TR>
        <TR><td><label>Czas trwania w minutach:</label></td>    <td><input type="number" name="ileMin" required></TR>
        <TR><td><label>Ilość punktów:</label></td>    <td><input type="number" name="points" required></td> </TR>
    </table>
<div id='questionList'>
    <div class='question' >
        <table>
            <TR><td><label>Pytanie:</label></td>   <td><input type="text" name="question" > <br/></td></TR>
            <TR><td><label>answer1</label></td>    <td><input type="text" name="answer1" required></td>    <td><select name="correct1"> <option value="True">True</option> <option value="False">False</option></select> </td> </TR>
            <TR><td><label>answer2</label></td>    <td><input type="text" name="answer2" required></td>    <td><select name="correct2"> <option value="True">True</option> <option value="False">False</option></select> </td> </TR>
            <TR><td><label>answer3</label></td>    <td><input type="text" name="answer3" required></td>    <td><select name="correct3"> <option value="True">True</option> <option value="False">False</option></select> </td> </TR>
            <TR><td><label>answer4</label></td>    <td><input type="text" name="answer4" required></td>    <td><select name="correct4"> <option value="True">True</option> <option value="False">False</option></select> </td> </TR>
        </table>
    </div>
</div>
    <button onclick="add()">Dodaj pytanie</button>
    <button onclick="delet()">Usuń pytanie</button><br/>
    <input type="submit" value="Zarejestruj">

</form>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    function add() {
        $('#questionList').append("<div class='question' >\n" +
            "        <table>\n" +
            "            <TR><td><label>Pytanie:</label></td>   <td><input type=\"text\" name=\"question\" > <br/></td></TR>\n" +
            "            <TR><td><label>answer1</label></td>    <td><input type=\"text\" name=\"answer1\" required></td>    <td><select name=\"correct1\"> <option value=\"True\">True</option> <option value=\"False\">False</option></select> </td> </TR>\n" +
            "            <TR><td><label>answer2</label></td>    <td><input type=\"text\" name=\"answer2\" required></td>    <td><select name=\"correct2\"> <option value=\"True\">True</option> <option value=\"False\">False</option></select> </td> </TR>\n" +
            "            <TR><td><label>answer3</label></td>    <td><input type=\"text\" name=\"answer3\" required></td>    <td><select name=\"correct3\"> <option value=\"True\">True</option> <option value=\"False\">False</option></select> </td> </TR>\n" +
            "            <TR><td><label>answer4</label></td>    <td><input type=\"text\" name=\"answer4\" required></td>    <td><select name=\"correct4\"> <option value=\"True\">True</option> <option value=\"False\">False</option></select> </td> </TR>\n" +
            "        </table>\n" +
            "    </div>");
    }

    function delet() {
        $("#questionList").children("div[class=question]:last").remove();
    }


</script>
