package model;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task writeCode = new Task("Написать проект", "Успевая в срок");
        Task washFloorCreated = taskManager.addTask(writeCode);
        System.out.println(washFloorCreated);

        Task writeCodeAfterReview = new Task(writeCode.getId(), "Слегка поправить", "Переделать половину",
                Status.IN_PROGRESS);
        Task writeCodeUpdated = taskManager.updateTask(writeCodeAfterReview);
        System.out.println(writeCodeUpdated);


        Epic createClass = new Epic("Создать класс", "С методами");
        taskManager.addEpic(createClass);
        System.out.println(createClass);
        Subtask createClassSubtask1 = new Subtask("Инкапсуляция", "Подумать какие модификаторы доступа применить",
                createClass.getId());
        Subtask createClassSubtask2 = new Subtask("Коллекции", "Решить как хранить подзадачи",
                createClass.getId());
        taskManager.addSubtask(createClassSubtask1);
        taskManager.addSubtask(createClassSubtask2);
        System.out.println(createClass);
        createClassSubtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(createClassSubtask2);
        System.out.println(createClass);
        System.out.println(taskManager.getEpicSubtasks(createClass));
    }
}