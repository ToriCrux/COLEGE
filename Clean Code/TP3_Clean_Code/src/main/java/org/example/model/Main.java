package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User dev = new User("Eva", "eva_dev@example.com", "Desenvolvedora");

        TaskItem item1 = new TaskItem("Modelar banco de dados", 5, 50.0);
        TaskItem item2 = new TaskItem("Criar endpoints REST", 8, 50.0);
        TaskItem item3 = new TaskItem("Testar API", 4, 50.0);

        Task task1 = new Task("Criação Login", "Criação da etapa de autenticação", Status.TODO, dev);
        Task task2 = new Task("Desenvolver Backend", "Implementação da lógica do servidor", Status.IN_PROGRESS, dev);
        Task task3 = new Task("Testes Automatizados", "Criar testes unitários", Status.DONE, dev);

        Sprint sprint1 = new Sprint("Sprint 1", "2025-03-20", "2025-04-03", new ArrayList<>());
        sprint1 = sprint1.addTask(task1)
                .addTask(task2)
                .addTask(task3);

        Project project = new Project("Sistema de Gerenciamento Ágil",
                "Sistema para gerenciamento de projetos seguindo os padrões ágeis", new ArrayList<>());
        project = project.addSprint(sprint1);

        System.out.println(formatProjectOutput(project));

        System.out.println("----------------------------------");

        System.out.println("\n Testando TaskItem:");
        System.out.println("Subtarefas (TaskItems):");
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);

        TaskItem updatedItem = item1.updateEstimatedHours(7);
        TaskItem updatedItem2 = item2.updateHourlyRate(55.0);

        System.out.println("\n Após Atualização:");
        System.out.println("Original: " + item1);
        System.out.println("Novo (Horas Atualizadas): " + updatedItem);
        System.out.println("Original: " + item2);
        System.out.println("Novo (Taxa Atualizada): " + updatedItem2);
    }

    private static String formatProjectOutput(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Projeto: ").append(project.getName()).append("\n");
        sb.append("Descrição: ").append(project.getDescription()).append("\n\n");
        sb.append("Sprints:\n");

        for (Sprint sprint : project.getSprints()) {
            sb.append("  Sprint: ").append(sprint.getName()).append("\n");
            sb.append("    Início: ").append(sprint.getStartDate()).append("\n");
            sb.append("    Fim: ").append(sprint.getEndDate()).append("\n");
            sb.append("    Progresso: ").append(String.format("%.2f", sprint.calculateProgress())).append("%\n");
            sb.append("    Tarefas:\n");

            for (Task task : sprint.getTasks()) {
                sb.append("      - ").append(task.getTitle()).append(" (").append(task.getStatus()).append(")\n");
                sb.append("        Responsável: ").append(task.getAssignedUser().getName()).append("\n");
                sb.append("        Email: ").append(task.getAssignedUser().getEmail()).append("\n");
                sb.append("        Cargo: ").append(task.getAssignedUser().getRole()).append("\n\n");
            }
        }
        return sb.toString();
    }
}
