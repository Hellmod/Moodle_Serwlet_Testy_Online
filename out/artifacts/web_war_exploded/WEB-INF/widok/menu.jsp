<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <ul>
        <li><a href="index.jsp?strona=glowna">Strona główna</a></li>
        ${(user.getPermissions()<0)?'<li><a href="/web_war_exploded/index.jsp?strona=rejestracja">Rejestracja</a></li>' : '' }
        ${(user.getPermissions()>0)?'<li><a href="/web_war_exploded/index.jsp?strona=ustawienia">Ustawienia</a></li>' : '' }
        ${(user.getPermissions()==1)?'<li><a href="index.jsp?strona=Test/tests">testy</a></li>' : '' }
        ${(user.getPermissions()==2)?'<li><a href="/web_war_exploded/index.jsp?strona=administracja">Administracja</a></li>' : '' }
        ${(user.getPermissions()==2)?'<li><a href="/web_war_exploded/index.jsp?strona=Test/addTests">Dodaj test</a></li>' : '' }
        ${(user.getPermissions()==2)?'<li><a href="/web_war_exploded/index.jsp?strona=Test/listTests">Lista testów</a></li>' : '' }
    </ul>

