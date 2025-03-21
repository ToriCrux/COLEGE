package org.example.model;
import java.util.List;
import java.util.UUID;

public final class Project {
    private final String id;
    private final String name;
    private final String description;
    private final List<Sprint> sprints;

    public Project(String name, String description, List<Sprint> sprints) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.sprints = List.copyOf(sprints);
    }

    public Project addSprint(Sprint sprint) {
        List<Sprint> newSprints = new java.util.ArrayList<>(sprints);
        newSprints.add(sprint);
        return new Project(this.name, this.description, newSprints);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    @Override
    public String toString() {
        return "Project{name='" + name + "', description='" + description + "', sprints=" + sprints + "}";
    }
}
