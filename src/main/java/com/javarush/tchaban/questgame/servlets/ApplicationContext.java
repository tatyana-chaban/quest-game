package com.javarush.tchaban.questgame.servlets;

import com.javarush.tchaban.questgame.engine.GameCreator;
import com.javarush.tchaban.questgame.engine.entities.*;
import com.javarush.tchaban.questgame.engine.entities.Character;
import com.javarush.tchaban.questgame.engine.predicates.WinCheckPredicate;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import com.javarush.tchaban.questgame.engine.services.DialogService;
import com.javarush.tchaban.questgame.engine.services.EntranceService;
import com.javarush.tchaban.questgame.engine.services.FinishService;
import com.javarush.tchaban.questgame.engine.services.LocationService;

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

        Character mrsStone = creator.createCharacter();
        context.setAttribute("character", mrsStone);


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

        EntranceService entranceService = new EntranceService(userRepository, questRepository, mrsStone);
        context.setAttribute("entranceService", entranceService);

        DialogService dialogService = new DialogService(questionRepository);
        context.setAttribute("dialogService", dialogService);

        LocationService locationService = new LocationService(locationRepository, itemRepository, new WinCheckPredicate(itemRepository.getAllKeys()));
        context.setAttribute("locationService", locationService);

        FinishService finishService = new FinishService();
        context.setAttribute("finishService", finishService);
    }
}
