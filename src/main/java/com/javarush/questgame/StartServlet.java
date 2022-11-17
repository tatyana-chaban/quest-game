package com.javarush.questgame;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StartServlet", value = "/start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");

        if (username.isBlank()) {
            request.setAttribute("blankName", true);
            getServletContext()
                    .getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        } else {


            request.setAttribute("username", username);

            getServletContext()
                    .getRequestDispatcher("/game.jsp")
                    .forward(request, response);
        }
    }
}
