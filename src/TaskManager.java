
import java.util.*;

public class TaskManager {

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    private int nextId = 1;

    public int getNextId() {
        return nextId++;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpic() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public Epic getEpicByID(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskByID(int id) {
        return subtasks.get(id);
    }

    public void deleteAllTask() {
        tasks.clear();
    }

    public void deleteAllEpic() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtask() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            epic.setStatus(Status.NEW);
        }
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int Id) {
        return epics.get(Id);
    }

    public Subtask getSubtaskById(int Id) {
        return subtasks.get(Id);
    }

    public Task addTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic addEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(getNextId());
        Epic epic = epics.get(subtask.getEpicID());
        epic.addSubtask(subtask);
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(epic);
        return subtask;
    }


    public Epic updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Task updateTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public Subtask updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicID());
        updateEpicStatus(epic);
        return subtask;
    }

    public void deleteTaskByID(int id) {
        tasks.remove(id);
    }

    public void deleteEpicByID(int id) {
        Epic epic = epics.remove(id);
        for (Subtask subtaskId : epic.getSubtaskList()) {
            subtasks.remove(subtaskId.getId());
        }
    }

    public void deleteSubtaskByID(int id) {
        Subtask subtask = subtasks.get(id);
        int epicID = subtask.getEpicID();
        subtasks.remove(id);
        Epic epic = epics.get(epicID);
        ArrayList<Subtask> subtaskList = epic.getSubtaskList();
        subtaskList.remove(subtask);
        epic.setSubtaskList(subtaskList);
        updateEpicStatus(epic);
    }


    public void updateEpicStatus(Epic epic) {
        int allIsDone = 0;
        int allIsInNew = 0;
        ArrayList<Subtask> list = epic.getSubtaskList();

        for (Subtask subtask : list) {
            if (subtask.getStatus() == Status.DONE) {
                allIsDone++;
            }
            if (subtask.getStatus() == Status.NEW) {
                allIsInNew++;
            }
        }
        if (allIsDone == list.size()) {
            epic.setStatus(Status.DONE);
        } else if (allIsInNew == list.size()) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}






