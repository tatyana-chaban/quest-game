package com.javarush.questgame;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "entranceServlet", value = "/entrance")
public class EntranceServlet extends HttpServlet {

   private Repository<String, User> userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        userRepository = (Repository<String, User>)context.getAttribute("userRepository");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("username");

        HttpSession session = request.getSession();

        if (userName.isBlank()) {
            request.setAttribute("blankName", true);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                    .forward(request, response);
        } else {
            User user;
            if(userRepository.contains(userName)){
                user = userRepository.getByKey(userName);
            } else {
                user = new User(userName);
                userRepository.add(user.getName(), user);
            }

            session.setAttribute("username", userName); // delete later
            session.setAttribute("user", user);

            response.sendRedirect("location");
        }
    }
}
