package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class WorkLog {
    private final String id;
    private final User user;
    private final LocalDateTime timestamp;
    private final int hoursWorked;
    private final BigDecimal hourlyRate;
    private final String description;

    public WorkLog(User user, int hoursWorked, BigDecimal hourlyRate, String description) {
        if (hoursWorked <= 0) {
            throw new IllegalArgumentException("Horas trabalhadas devem ser maiores que zero.");
        }
        if (hourlyRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A taxa horária não pode ser negativa.");
        }
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.timestamp = LocalDateTime.now();
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.description = description;
    }

    public BigDecimal calculateTotalCost() {
        return hourlyRate.multiply(BigDecimal.valueOf(hoursWorked));
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "WorkLog{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", timestamp=" + timestamp +
                ", hoursWorked=" + hoursWorked +
                ", hourlyRate=" + hourlyRate +
                ", totalCost=" + calculateTotalCost() +
                ", description='" + description + '\'' +
                '}';
    }
}

