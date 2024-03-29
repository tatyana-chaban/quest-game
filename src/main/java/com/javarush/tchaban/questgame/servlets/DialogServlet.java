package com.javarush.tchaban.questgame.servlets;

import com.javarush.tchaban.questgame.engine.entities.Question;
import com.javarush.tchaban.questgame.engine.services.DialogService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DialogServlet", value = "/dialog")
public class DialogServlet extends HttpServlet {
    private DialogService dialogService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        dialogService = (DialogService) context.getAttribute("dialogService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentQuestionIdName = request.getParameter("message");

        Question currentQuestion = dialogService.getQuestion(currentQuestionIdName);

        request.setAttribute("currentQuestion", currentQuestion);
        request.setAttribute("availableAnswers", currentQuestion.getAnswers());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/dialog.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nextQuestionIdName = request.getParameter("nextQuestionId");

        response.sendRedirect(request.getContextPath()
                + "/dialog?message="
                + dialogService.getQuestionId(nextQuestionIdName));
    }
}
