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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContext implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
    private final GameCreator creator = new GameCreator();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        logger.info("Start context initializing.");

        Repository<String, User> userRepository = new Repository<>();
        context.setAttribute("userRepository", userRepository);

        Character mrsStone = createCharacter();
        context.setAttribute("character", mrsStone);

        Repository<String, Location> locationRepository = fillLocationRepository();
        context.setAttribute("locationRepository", locationRepository);

        Repository<Integer, Question> questionRepository = fillQuestionRepository();
        context.setAttribute("questionRepository", questionRepository);

        Repository<String, Item> itemRepository = fillItemRepository();
        context.setAttribute("itemRepository", itemRepository);


        Repository<Integer, Quest> questRepository = fillQuestRepository();
        context.setAttribute("questRepository", questRepository);

        EntranceService entranceService = new EntranceService(userRepository, questRepository, mrsStone);
        context.setAttribute("entranceService", entranceService);

        DialogService dialogService = new DialogService(questionRepository);
        context.setAttribute("dialogService", dialogService);

        LocationService locationService = new LocationService(locationRepository, itemRepository,
                new WinCheckPredicate(itemRepository.getAllKeys()));
        context.setAttribute("locationService", locationService);

        FinishService finishService = new FinishService();
        context.setAttribute("finishService", finishService);

        logger.info("Finish context initializing.");
    }

    private Character createCharacter() {
        return creator.createCharacter();
    }

    private Repository<String, Location> fillLocationRepository() {
        Repository<String, Location> locationRepository = new Repository<>();

        creator.createMap()
                .forEach(location -> locationRepository.add(location.getName(), location));

        return locationRepository;
    }

    private Repository<Integer, Question> fillQuestionRepository() {
        Repository<Integer, Question> questionRepository = new Repository<>();

        creator.createDialogWithMrsStone()
                .forEach(question -> questionRepository.add(question.getId(), question));

        return questionRepository;
    }

    private Repository<String, Item> fillItemRepository() {
        Repository<String, Item> itemRepository = new Repository<>();

        creator.createItems()
                .forEach(item -> itemRepository.add(item.getName(), item));

        return itemRepository;
    }

    private Repository<Integer, Quest> fillQuestRepository() {
        Repository<Integer, Quest> questRepository = new Repository<>();

        creator.createQuests()
                .forEach(quest -> questRepository.add(quest.getId(), quest));

        return questRepository;
    }
}
