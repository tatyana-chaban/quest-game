package com.javarush.tchaban.questgame.servlets;

import com.javarush.tchaban.questgame.engine.GameCreator;
import com.javarush.tchaban.questgame.engine.entities.*;
import com.javarush.tchaban.questgame.engine.repository.Repository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        GameCreator creator = new GameCreator();

        Repository<String, User> userRepository = new Repository<>();
        context.setAttribute("userRepository", userRepository);

        Housekeeper mrsStone = creator.createHousekeeper();
        context.setAttribute("housekeeper", mrsStone);


        Repository<String, Location> locationRepository = new Repository<>();
        for (Location location : creator.createMap()) {
            locationRepository.add(location.getName(), location);
        }
        context.setAttribute("locationRepository", locationRepository);


        Repository<Integer, Question> questionRepository = new Repository<>();
        for (Question question : creator.createDialogWithMrsStone()) {
            questionRepository.add(question.getId(), question);
        }
        context.setAttribute("questionRepository", questionRepository);


        Repository<String, Item> itemRepository = new Repository<>();
        for (Item item : creator.createItems()) {
            itemRepository.add(item.getName(), item);
        }
        context.setAttribute("itemRepository", itemRepository);


        Repository<Integer, Quest> questRepository = new Repository<>();
        for (Quest quest : creator.createQuests()) {
            questRepository.add(quest.getId(), quest);
        }
        context.setAttribute("questRepository", questRepository);


    }
}
