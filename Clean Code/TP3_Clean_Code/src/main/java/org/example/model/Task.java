package org.example.model;

public class Task {
    private final String title;
    private final String description;
    private final Status status;
    private final User assignedUser;

    public Task(String title, String description, Status status, User assignedUser) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedUser = assignedUser;
    }

    public Task updateStatus(Status newStatus) {
        return new Task(this.title, this.description, newStatus, this.assignedUser);
    }

    public Task assignUser(User newUser) {
        return new Task(this.title, this.description, this.status, newUser);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    @Override
    public String toString() {
        return "Task{title='" + title + "', status=" + status + ", assignedUser=" + assignedUser + "}";
    }
}
