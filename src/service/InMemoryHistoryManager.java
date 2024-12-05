package service;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryHistoryManager implements HistoryManager {

    private Node head;
    private Node tail;
    private final HashMap<Integer, Node> historyList = new HashMap<>();

    @Override
    public void add(Task task) {
        if (historyList.containsKey(task.getId())) {
            removeNode(historyList.get(task.getId()));
        }

        // Создаем новый узел для задачи
        Node newNode = new Node(task);
        linkLast(newNode); // Добавляем узел в конец списка
        historyList.put(task.getId(), newNode); // Обновляем HashMap
    }

    @Override
    public void remove(int id) {
        Node nodeToRemove = historyList.remove(id);
        if (nodeToRemove != null) {
            removeNode(nodeToRemove);
        }
    }


    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Node current = head;
        while (current != null) {
            tasks.add(current.getTask());
            current = current.getNext();
        }
        return List.copyOf(tasks);
    }

    private void removeNode(Node node) {
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            head = node.getNext(); // Если удаляем head, обновляем head
        }
        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            tail = node.getPrev(); // Если удаляем tail, обновляем tail
        }
    }

    private void linkLast(Node node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
    }

}


class Node {
    private Task task;
    private Node prev;
    private Node next;

    public Node(Task task) {
        this.task = task;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public Task getTask() {
        return task;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
