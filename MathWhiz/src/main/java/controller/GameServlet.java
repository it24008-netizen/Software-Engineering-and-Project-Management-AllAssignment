package controller;

import dao.ScoreDAO;
import model.GameScore;
import service.MathService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final String DIFFICULTY = "NORMAL";

    private static final int[] SIN_COS_ANGLES = {0, 30, 45, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330, 360};
    private static final int[] TAN_ANGLES = {0, 30, 45, 60, 120, 135, 150, 180, 210, 225, 240, 300, 315, 330};

    private final ScoreDAO scoreDAO = new ScoreDAO();
    private final Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("leaderboard".equals(req.getParameter("action"))) {
            showLeaderboard(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("start".equals(action)) {
            startGame(req, resp);
        } else if ("submit".equals(action)) {
            submitAnswers(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/game");
        }
    }

    // ---------- Start: validate name, generate four questions ----------
    private void startGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("playerName");
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Please enter your name.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }
        name = name.trim();
        if (name.length() > 100) name = name.substring(0, 100);

        HttpSession s = req.getSession(true);
        s.setAttribute("playerName", name);

        s.setAttribute("primeN", 2 + random.nextInt(98));      // Q1 prime
        s.setAttribute("modA", 10 + random.nextInt(90));       // Q2 modulus
        s.setAttribute("modB", 2 + random.nextInt(11));        //    divisor never zero
        List<String> fns = new ArrayList<>(Arrays.asList("sin", "cos", "tan"));
        Collections.shuffle(fns, random);                      // Q3, Q4: two different functions
        s.setAttribute("trig1Fn", fns.get(0));
        s.setAttribute("trig1Angle", pickAngle(fns.get(0)));
        s.setAttribute("trig2Fn", fns.get(1));
        s.setAttribute("trig2Angle", pickAngle(fns.get(1)));
        s.setAttribute("gameActive", Boolean.TRUE);

        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    private int pickAngle(String fn) {
        int[] pool = fn.equals("tan") ? TAN_ANGLES : SIN_COS_ANGLES;
        return pool[random.nextInt(pool.length)];
    }

    // ---------- Submit: validate, evaluate with Java methods, save ----------
    private void submitAnswers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("gameActive") == null) {
            resp.sendRedirect(req.getContextPath() + "/game");
            return;
        }

        int primeN = (Integer) s.getAttribute("primeN");
        int modA = (Integer) s.getAttribute("modA");
        int modB = (Integer) s.getAttribute("modB");
        String fn1 = (String) s.getAttribute("trig1Fn");
        String fn2 = (String) s.getAttribute("trig2Fn");
        int ang1 = (Integer) s.getAttribute("trig1Angle");
        int ang2 = (Integer) s.getAttribute("trig2Angle");

        int primeAns, modAns;
        double trig1Ans, trig2Ans;
        try {
            primeAns = Integer.parseInt(req.getParameter("primeAnswer").trim());
            if (primeAns != 0 && primeAns != 1) throw new NumberFormatException();
        } catch (Exception e) {
            error(req, resp, "Question 1: choose 1 (prime) or 0 (not prime).");
            return;
        }
        try {
            modAns = Integer.parseInt(req.getParameter("modAnswer").trim());   // integers only
        } catch (Exception e) {
            error(req, resp, "Question 2: the remainder must be a whole number (integer).");
            return;
        }
        try {
            trig1Ans = Double.parseDouble(req.getParameter("trig1Answer").trim());
        } catch (Exception e) {
            error(req, resp, "Question 3: enter a valid decimal number.");
            return;
        }
        try {
            trig2Ans = Double.parseDouble(req.getParameter("trig2Answer").trim());
        } catch (Exception e) {
            error(req, resp, "Question 4: enter a valid decimal number.");
            return;
        }
        if (modB == 0) {
            error(req, resp, "Validation error: divisor must not be zero.");
            return;
        }

        // correct answers come from the Java methods
        int primeExp = MathService.isPrime(primeN);
        int modExp = MathService.calculateMod(modA, modB);
        double trig1Exp = MathService.calculateTrig(fn1, ang1);
        double trig2Exp = MathService.calculateTrig(fn2, ang2);

        boolean primeCorrect = primeAns == primeExp;
        boolean modCorrect = modAns == modExp;
        boolean trig1Correct = MathService.isClose(trig1Exp, trig1Ans);
        boolean trig2Correct = MathService.isClose(trig2Exp, trig2Ans);

        int totalScore = 0;
        if (primeCorrect) totalScore += 2;
        if (modCorrect)   totalScore += 2;
        if (trig1Correct) totalScore += 3;
        if (trig2Correct) totalScore += 3;

        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"Q1. Is " + primeN + " prime?", String.valueOf(primeAns), String.valueOf(primeExp), primeCorrect ? "2 / 2" : "0 / 2"});
        rows.add(new String[]{"Q2. " + modA + " % " + modB, String.valueOf(modAns), String.valueOf(modExp), modCorrect ? "2 / 2" : "0 / 2"});
        rows.add(new String[]{"Q3. " + fn1 + "(" + ang1 + "\u00B0)", fmt(trig1Ans), fmt(trig1Exp), trig1Correct ? "3 / 3" : "0 / 3"});
        rows.add(new String[]{"Q4. " + fn2 + "(" + ang2 + "\u00B0)", fmt(trig2Ans), fmt(trig2Exp), trig2Correct ? "3 / 3" : "0 / 3"});

        String playerName = (String) s.getAttribute("playerName");
        try {
            scoreDAO.save(new GameScore(playerName, totalScore, DIFFICULTY));
        } catch (SQLException e) {
            log("Could not save score", e);
            req.setAttribute("saveError", "Your score could not be saved: " + e.getMessage());
        }

        // game finished: clear it so a refresh cannot save the score twice
        for (String k : new String[]{"gameActive", "primeN", "modA", "modB", "trig1Fn", "trig1Angle", "trig2Fn", "trig2Angle"}) {
            s.removeAttribute(k);
        }

        req.setAttribute("playerName", playerName);
        req.setAttribute("totalScore", totalScore);
        req.setAttribute("difficulty", DIFFICULTY);
        req.setAttribute("rows", rows);
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    private String fmt(double v) {
        return Double.isNaN(v) ? "undefined" : String.format("%.4f", v);
    }

    private void error(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("error", msg);
        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    // ---------- Leaderboard ----------
    private void showLeaderboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("scores", scoreDAO.getTop10());
        } catch (SQLException e) {
            log("Could not load leaderboard", e);
            req.setAttribute("error", "Could not load the leaderboard: " + e.getMessage());
        }
        req.getRequestDispatcher("/leaderboard.jsp").forward(req, resp);
    }
}
