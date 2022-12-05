package com.javarush.tchaban.questgame.servlets;

import com.javarush.tchaban.questgame.engine.entities.Character;
import com.javarush.tchaban.questgame.engine.entities.User;
import com.javarush.tchaban.questgame.engine.services.EntranceService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EntranceServlet", value = "/entrance")
public class EntranceServlet extends HttpServlet {
    private EntranceService entranceService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        entranceService = (EntranceService) context.getAttribute("entranceService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("username");

        HttpSession session = request.getSession();

        if(userName == null || userName.isBlank()){
            request.setAttribute("incorrectName", true);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                    .forward(request, response);
        }

        User user = entranceService.getOrCreateUser(userName);
        Character mrsStone = entranceService.getCharacter();

        session.setAttribute("user", user);
        session.setAttribute("character", mrsStone);

        response.sendRedirect(request.getContextPath() + "/dialog?message=" + mrsStone.getStartQuestionId());
    }
}
