package com.javarush.questgame;

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


        Repository<String, Location> locationRepository = new Repository<>();
        for (Location location : creator.createMap()) {
            locationRepository.add(location.getName(), location);
        }
        context.setAttribute("locationRepository", locationRepository);


        Repository<String, Nps> npsRepository = new Repository<>();
        for (Nps nps: creator.createNps()) {
            npsRepository.add(nps.getName(), nps);
        }
        context.setAttribute("npsRepository", npsRepository);


        Repository<Integer, Question> questionRepository = new Repository<>();
        for (Question question: creator.createDialogWithMrsStone()) {
            questionRepository.add(question.getId(), question);
        }
        context.setAttribute("questionRepository", questionRepository);


        Repository<String, Item> itemRepository = new Repository<>();
        for (Item item:creator.createItems()) {
            itemRepository.add(item.getName(), item);
        }
        context.setAttribute("itemRepository", itemRepository);


        Repository<Integer, Task> taskRepository = new Repository<>();
        for (Task task: creator.createTasks()) {
            taskRepository.add(task.getId(), task);
        }
        context.setAttribute("taskRepository", taskRepository);


    }
}
