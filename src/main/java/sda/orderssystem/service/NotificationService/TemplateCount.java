package sda.orderssystem.service.NotificationService;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a singleton class that counts the number of messages sent.
 * it has a hashmap of int and int that stores the number of messages sent by each type.
 * It has a static method that returns the instance of the class.
 * It has a method that returns the number of messages sent by a specific type.
 */
public class TemplateCount {

    // The hashmap that stores the number of messages sent by each type.
    public static HashMap<Integer, Integer> templateCount = new HashMap<>();

    // The instance of the class.
    private static TemplateCount templateCountInstance;

    // The constructor of the class.

    private TemplateCount() {
    }

    // The method that returns the instance of the class.
    public static TemplateCount getInstance() {
        if (templateCountInstance == null) {
            templateCountInstance = new TemplateCount();
            templateCount.put(1, 0);
            templateCount.put(2, 0);
            templateCount.put(3, 0);
            templateCount.put(4, 0);
        }
        return templateCountInstance;
    }
    public Map<Integer, Integer> getTemplateCount() {
        return templateCount;
    }

    public void incrementCount(int templateNumber) {
        templateCount.put(templateNumber, templateCount.get(templateNumber) + 1);
    }


    
}
