package service;

import model.*;
import service.memory.ManagerSaveException;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();

    List<Epic> getAllEpic();

    List<Subtask> getAllSubtask();

    List<Subtask> getEpicSubtasks(Epic epic);

    void deleteAllTask();

    void deleteAllEpic();

    void deleteAllSubtask();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    Task addTask(Task task) throws ManagerSaveException;

    Epic addEpic(Epic epic) throws ManagerSaveException;

    Subtask addSubtask(Subtask subtask) throws ManagerSaveException;

    Epic updateEpic(Epic epic) throws ManagerSaveException;

    Task updateTask(Task task) throws ManagerSaveException;

    Subtask updateSubtask(Subtask subtask) throws ManagerSaveException;

    Task deleteTaskByID(int id) throws ManagerSaveException;

    Epic deleteEpicByID(int id) throws ManagerSaveException;

    Subtask deleteSubtaskByID(int id) throws ManagerSaveException;

    List<Task> getHistory();
}
