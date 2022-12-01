package com.javarush.tchaban.questgame.servlets;

import com.javarush.tchaban.questgame.engine.entities.Item;
import com.javarush.tchaban.questgame.engine.entities.Location;
import com.javarush.tchaban.questgame.engine.entities.User;
import com.javarush.tchaban.questgame.engine.services.LocationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LocationServlet", value = "/location")
public class LocationServlet extends HttpServlet {
    private LocationService locationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        locationService = (LocationService) context.getAttribute("locationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (locationService.isWin(user)) {
            response.sendRedirect(request.getContextPath() + "/finish?finishMessage=" + user.getWinMessage());
            return;
        }



        Location currentLocation = locationService.getLocation(user.getCurrentLocationName());
        List<Location> availableLocations = locationService.getAvailableLocations(currentLocation);
        List<Item> availableItems = locationService.getAvailableItems(user, currentLocation);

        request.setAttribute("currentLocation", currentLocation);
        request.setAttribute("availableLocations", availableLocations);
        request.setAttribute("availableItems", availableItems);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/location.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        String nextLocationName = request.getParameter("nextLocation");
        String itemName = request.getParameter("item");

        if (nextLocationName != null) {
            locationService.moveToLocation(user, nextLocationName);
        }

        if (itemName != null) {
            locationService.takeItem(user, itemName);
        }

        response.sendRedirect(request.getContextPath() + "/location");
    }
}

