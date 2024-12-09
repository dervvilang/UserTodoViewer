package org.example;
public class Todo {
    final int userId;
    final int id;
    final String title;
    final boolean completed;

    public Todo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    void print() {
        System.out.println("userId - " + userId);
        System.out.println("id - " + id);
        System.out.println("title - " + title);
        System.out.println("completed - " + completed);
        System.out.println();
    }
}