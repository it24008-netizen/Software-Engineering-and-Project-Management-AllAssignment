<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty totalScore}"><c:redirect url="/game"/></c:if>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Result – MATH WHIZ</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
  <h1>YOUR NAME – MATH WHIZ</h1>
  <p>Result</p>
</header>
<main>
  <div class="card">
    <h2>Player: <c:out value="${playerName}"/></h2>
    <div class="score">${totalScore} / 10</div>
    <p style="text-align:center">Difficulty: <c:out value="${difficulty}"/></p>
    <c:if test="${not empty saveError}"><div class="error"><c:out value="${saveError}"/></div></c:if>
    <table>
      <tr><th>Question</th><th>Your answer</th><th>Correct answer</th><th class="right">Marks</th></tr>
      <c:forEach var="r" items="${rows}">
        <tr>
          <td><c:out value="${r[0]}"/></td>
          <td><c:out value="${r[1]}"/></td>
          <td><c:out value="${r[2]}"/></td>
          <td class="right"><c:out value="${r[3]}"/></td>
        </tr>
      </c:forEach>
    </table>
    <div class="actions">
      <a class="btn" href="${pageContext.request.contextPath}/game">Play Again</a>
      <a class="btn secondary" href="${pageContext.request.contextPath}/game?action=leaderboard">Leaderboard</a>
    </div>
  </div>
</main>
</body>
</html>
