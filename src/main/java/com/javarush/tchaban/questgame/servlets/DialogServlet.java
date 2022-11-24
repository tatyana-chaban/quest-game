package com.javarush.tchaban.questgame.servlets;

import com.javarush.tchaban.questgame.engine.entities.Question;
import com.javarush.tchaban.questgame.engine.repository.Repository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DialogServlet", value = "/dialog")
public class DialogServlet extends HttpServlet {

    private Repository<Integer, Question> questionRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        questionRepository = (Repository<Integer, Question>) context.getAttribute("questionRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentQuestionIdName = request.getParameter("message");

        Question currentQuestion = questionRepository.getByKey(Integer.parseInt(currentQuestionIdName));

        List<Question.Answer> availableAnswers = currentQuestion.getAnswers();

        request.setAttribute("currentQuestion", currentQuestion);
        request.setAttribute("availableAnswers", availableAnswers);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/dialog.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextQuestionIdName = request.getParameter("nextQuestionId");

        response.sendRedirect(request.getContextPath() + "/dialog?message=" + Integer.parseInt(nextQuestionIdName));
    }
}
