package service;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;
import java.util.Map;

public interface HistoryManager {

    void add(Task task);

    void remove(int id);

    public void removeTasks(Map<Integer, Task> tasks);

    public void removeEpics(Map<Integer, Epic> epics, Map<Integer, Subtask> subtasks);

    public void removeSubtasks(Map<Integer, Subtask> subtasks);

    List<Task> getHistory();
}
