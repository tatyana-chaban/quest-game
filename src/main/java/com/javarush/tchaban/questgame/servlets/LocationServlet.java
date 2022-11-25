package com.javarush.tchaban.questgame.servlets;

import com.javarush.tchaban.questgame.engine.entities.Item;
import com.javarush.tchaban.questgame.engine.entities.Location;
import com.javarush.tchaban.questgame.engine.predicates.WinCheckPredicate;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import com.javarush.tchaban.questgame.engine.entities.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet(name = "LocationServlet", value = "/location")
public class LocationServlet extends HttpServlet {
    private Repository<String, Location> locationRepository;
    private Repository<String, Item> itemRepository;
    private WinCheckPredicate winCheck;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        locationRepository = (Repository<String, Location>) context.getAttribute("locationRepository");
        itemRepository = (Repository<String, Item>) context.getAttribute("itemRepository");
        winCheck = new WinCheckPredicate(itemRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        var user = (User) session.getAttribute("user");

        if (winCheck.test(user)) {
            response.sendRedirect(request.getContextPath() + "/finish?finishMessage=" + user.getWinMessage());
            return;
        }

        String currentLocationName = user.getCurrentLocationName();
        Location currentLocation = locationRepository.getByKey(currentLocationName);

        List<Location> availableLocations = new ArrayList<>();
        for (String locationName : currentLocation.getAvailableLocations()) {
            var location = locationRepository.getByKey(locationName);
            availableLocations.add(location);
        }

        List<Item> availableItems = new ArrayList<>();
        for (String itemName : currentLocation.getItems()) {
            var item = itemRepository.getByKey(itemName);
            availableItems.add(item);
        }

        request.setAttribute("currentLocation", currentLocation);
        request.setAttribute("availableLocations", availableLocations);
        request.setAttribute("availableItems", availableItems);
        request.setAttribute("user", user);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/location.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String nextLocationName = request.getParameter("nextLocation");
        String itemName = request.getParameter("item");

        if (nextLocationName != null) {
            user.setCurrentLocationName(nextLocationName);
        }

        if (itemName != null) {
            user.putInInventory(itemName);
        }

        response.sendRedirect(request.getContextPath() + "/location");
    }
}

