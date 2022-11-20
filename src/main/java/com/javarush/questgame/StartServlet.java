package com.javarush.questgame;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StartServlet", value = "/start")
public class StartServlet extends HttpServlet {
    UserRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        repository = (UserRepository) servletContext.getContext("userRepository");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("username");

        if (userName.isBlank()) {
            request.setAttribute("blankName", true);
            getServletContext()
                    .getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute("username", userName);

            User user;
            if(repository.contains(userName)){
                user = repository.getUserByName(userName);
            } else {
                user = new User(userName);
                repository.add(user);
            }

            request.setAttribute("user", user);

            getServletContext()
                    .getRequestDispatcher("/game.jsp")
                    .forward(request, response);
        }
    }
}
