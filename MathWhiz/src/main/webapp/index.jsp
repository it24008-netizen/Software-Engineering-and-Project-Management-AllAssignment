<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>YOUR NAME – MATH WHIZ</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
  <h1>YOUR NAME – MATH WHIZ</h1>
  <p>Prime numbers, modulus and trigonometry. How many marks can you earn?</p>
</header>
<main>
  <div class="card">
    <h2>Welcome!</h2>
    <p>Answer four questions (10 marks in total). Difficulty: <strong>NORMAL</strong>.</p>
    <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>
    <form method="post" action="${pageContext.request.contextPath}/game">
      <input type="hidden" name="action" value="start">
      <label for="playerName"><strong>Player Name</strong></label><br><br>
      <input type="text" id="playerName" name="playerName" maxlength="100" placeholder="Enter your name" required autofocus>
      <div class="actions">
        <button class="btn" type="submit">Start Game</button>
        <a class="btn secondary" href="${pageContext.request.contextPath}/game?action=leaderboard">View Leaderboard</a>
      </div>
    </form>
  </div>
</main>
</body>
</html>
