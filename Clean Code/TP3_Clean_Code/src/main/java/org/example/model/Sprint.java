package org.example.model;

import java.util.List;

public class Sprint {
    private final String name;
    private final String startDate;
    private final String endDate;
    private final List<Task> tasks;

    public Sprint(String name, String startDate, String endDate, List<Task> tasks) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tasks = List.copyOf(tasks);
    }

    public Sprint addTask(Task task) {
        List<Task> newTasks = new java.util.ArrayList<>(tasks);
        newTasks.add(task);
        return new Sprint(this.name, this.startDate, this.endDate, newTasks);
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public double calculateProgress() {
        if (tasks.isEmpty()) return 0.0;

        long completedTasks = tasks.stream()
                .filter(task -> task.getStatus() == Status.DONE)
                .count();

        return (double) completedTasks / tasks.size() * 100; // Retorna a porcentagem de conclusão
    }

    @Override
    public String toString() {
        return "Sprint{name='" + name + "', startDate='" + startDate + "', endDate='" + endDate + "', progress="
                + String.format("%.2f", calculateProgress()) + "%, tasks=" + tasks + "}";
    }
}
