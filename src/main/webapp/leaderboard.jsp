<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Leaderboard – MATH WHIZ</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
  <h1>YOUR NAME – MATH WHIZ</h1>
  <p>Top 10 Leaderboard</p>
</header>
<main>
  <div class="card">
    <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>
    <table>
      <tr><th>#</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Date / Time</th></tr>
      <c:forEach var="g" items="${scores}" varStatus="st">
        <tr class="${st.first ? 'top1' : ''}">
          <td>${st.count}</td>
          <td><c:out value="${g.playerName}"/></td>
          <td>${g.score}</td>
          <td><c:out value="${g.difficulty}"/></td>
          <td><c:out value="${g.playedAt}"/></td>
        </tr>
      </c:forEach>
      <c:if test="${empty scores}"><tr><td colspan="5">No scores yet. Be the first to play!</td></tr></c:if>
    </table>
    <div class="actions">
      <a class="btn" href="${pageContext.request.contextPath}/game">Play Game</a>
    </div>
  </div>
</main>
</body>
</html>
