<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Suggestions</title>
</head>
<h3>your suggestions</h3>
<body>
<div style="color: green">${succ_massage}</div>
<div style="color: red">${error_massage}</div>
<form action="choose_suggestion" method="post">
    <label>Choose an Order to see Suggestions:</label>
    <ul>
        <c:forEach var="suggestion" items="${suggestions}">
            <li><label>
                <input type="radio" value="${suggestion.identity}" name="suggestionIdentity"/>
            </label>suggestedPrice:${suggestion.suggestedPrice}
                , durationOfWork:
                    ${suggestion.durationOfWork}
                , startTime:
                    ${suggestion.startTime}
            </li>
        </c:forEach>
    </ul>

    <br><br>
    <input type="submit" value="Submit"/>
</form>
<br/>
<a href="">back to dashboard</a>

</body>
</html>