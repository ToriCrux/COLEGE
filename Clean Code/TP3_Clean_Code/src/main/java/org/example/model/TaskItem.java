package org.example.model;

import java.util.UUID;

public final class TaskItem {
    private final String id;
    private final String name;
    private final int estimatedHours;
    private final double hourlyRate;

    public TaskItem(String name, int estimatedHours, double hourlyRate) {
        if (estimatedHours < 0) {
            throw new IllegalArgumentException("Horas estimadas não podem ser negativas.");
        }
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Valor por hora não pode ser negativo.");
        }

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.estimatedHours = estimatedHours;
        this.hourlyRate = hourlyRate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double calculateTotalCost() {
        return estimatedHours * hourlyRate;
    }

    public TaskItem updateEstimatedHours(int newHours) {
        return new TaskItem(this.name, newHours, this.hourlyRate);
    }

    public TaskItem updateHourlyRate(double newRate) {
        return new TaskItem(this.name, this.estimatedHours, newRate);
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", estimatedHours=" + estimatedHours +
                ", hourlyRate=" + hourlyRate +
                ", totalCost=" + calculateTotalCost() +
                '}';
    }
}
