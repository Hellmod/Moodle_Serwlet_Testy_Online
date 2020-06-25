<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="uzytkownik" class="ti.model.RMuzytkownik" scope="session"/>


    <ul>
        <li><a href="index.jsp?strona=glowna">Strona główna</a></li>
        <li><a href="index.jsp?strona=kwadratowe">ax<sup>2</sup> + bx + c</a></li>
        <li><a href="index.jsp?strona=pierwsze">liczby pierwsze</a></li>
        ${(uzytkownik.uprawnienia==-1)?'<li><a href="/web_war_exploded/index.jsp?strona=rejestracja">Rejestracja</a></li>' : '' }
        ${(uzytkownik.uprawnienia>0)?'<li><a href="/web_war_exploded/index.jsp?strona=ustawienia">Ustawienia</a></li>' : '' }
        ${(uzytkownik.uprawnienia==2)?'<li><a href="/web_war_exploded/index.jsp?strona=administracja">Administracja</a></li>' : '' }
    </ul>

