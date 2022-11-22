package com.javarush.questgame;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LocationServlet", value = "/location")
public class LocationServlet extends HttpServlet {
    private Repository<String, Location> locationRepository;
    private Repository<String, Npc> npsRepository;
    private Repository<String, Item> itemRepository;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        locationRepository = (Repository<String, Location>) context.getAttribute("locationRepository");
        npsRepository = (Repository<String, Npc>) context.getAttribute("npsRepository");
        itemRepository = (Repository<String, Item>) context.getAttribute("itemRepository");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String currentLocationName = user.getCurrentLocationName();

        Location currentLocation = locationRepository.getByKey(currentLocationName);

        List<Location> availableLocations = new ArrayList<>();
        for (String locationName : currentLocation.getAvailableLocations()) {
            var location = locationRepository.getByKey(locationName);
            availableLocations.add(location);
        }

        List<Npc> availableNpc = new ArrayList<>();
        for (String npsName : currentLocation.getNps()) {
            var npc = npsRepository.getByKey(npsName);
            availableNpc.add(npc);
        }

        List<Item> availableItems = new ArrayList<>();
        for (String itemName : currentLocation.getItems()) {
            var item = itemRepository.getByKey(itemName);
            availableItems.add(item);
        }

        request.setAttribute("currentLocation", currentLocation);
        request.setAttribute("availableLocations", availableLocations);
        request.setAttribute("availableNpc", availableNpc);
        request.setAttribute("availableItems", availableItems);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/location.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //   request.setCharacterEncoding("UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        String nextLocationName = request.getParameter("nextLocation");

        if (nextLocationName != null) {
            user.setCurrentLocationName(nextLocationName);
        }

        response.sendRedirect("location");

    }
}

