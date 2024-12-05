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
        Node newNode = new Node(task);
        linkLast(newNode);
        historyList.put(task.getId(), newNode);
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
            head = node.getNext();
        }
        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            tail = node.getPrev();
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


