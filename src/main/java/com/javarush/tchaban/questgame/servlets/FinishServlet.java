package com.javarush.tchaban.questgame.servlets;

import com.javarush.tchaban.questgame.engine.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FinishServlet", value = "/finish")
public class FinishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        user.incrementNumberOfParties();
        user.returnToStart();

        String finishMessage = request.getParameter("finishMessage");

        request.setAttribute("finishMessage", finishMessage);
        request.setAttribute("user", user);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/finish.jsp")
                .forward(request, response);
    }
}
