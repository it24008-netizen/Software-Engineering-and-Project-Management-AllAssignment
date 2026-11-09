<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.gameActive}"><c:redirect url="/game"/></c:if>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Game – MATH WHIZ</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
  <h1>YOUR NAME – MATH WHIZ</h1>
  <p>Good luck, <c:out value="${sessionScope.playerName}"/>! &nbsp;|&nbsp; Difficulty: NORMAL</p>
</header>
<main>
  <form class="card" method="post" action="${pageContext.request.contextPath}/game">
    <input type="hidden" name="action" value="submit">
    <c:if test="${not empty error}"><div class="error"><c:out value="${error}"/></div></c:if>

    <div class="q">
      <h3><span class="marks">2 marks</span>Q1. Prime Number</h3>
      <div class="hint">Is <strong>${sessionScope.primeN}</strong> a prime number? (1 = Prime, 0 = Not Prime)</div>
      <select name="primeAnswer" required>
        <option value="">-- choose --</option>
        <option value="1">1 (Prime)</option>
        <option value="0">0 (Not Prime)</option>
      </select>
    </div>

    <div class="q">
      <h3><span class="marks">2 marks</span>Q2. Modulus</h3>
      <div class="hint">What is the remainder of <strong>${sessionScope.modA} % ${sessionScope.modB}</strong>? (integer)</div>
      <input type="number" name="modAnswer" step="1" required placeholder="Integer answer">
    </div>

    <div class="q">
      <h3><span class="marks">3 marks</span>Q3. Trigonometry</h3>
      <div class="hint">Find <strong>${sessionScope.trig1Fn}(${sessionScope.trig1Angle}°)</strong> (angle in degrees, answer to 2 decimals).</div>
      <input type="number" name="trig1Answer" step="any" required placeholder="e.g. 0.50">
    </div>

    <div class="q">
      <h3><span class="marks">3 marks</span>Q4. Trigonometry</h3>
      <div class="hint">Find <strong>${sessionScope.trig2Fn}(${sessionScope.trig2Angle}°)</strong> (angle in degrees, answer to 2 decimals).</div>
      <input type="number" name="trig2Answer" step="any" required placeholder="e.g. 0.87">
    </div>

    <button class="btn" type="submit">Submit Answers</button>
  </form>
</main>
</body>
</html>
