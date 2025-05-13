package org.isep.cleancode.persistence.csvfiles;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.ITodoRepository;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TodoCsvFilesRepository implements ITodoRepository {

    private final Path filePath;
    private final Set<String> existingNames = new HashSet<>();

    public TodoCsvFilesRepository() {
        String appData = System.getenv("APPDATA");
        Path folder = Paths.get(appData, "isep-todo-api");
        this.filePath = folder.resolve("todos.csv");

        try {
            Files.createDirectories(folder);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            } else {
                // Charger les noms existants en mémoire
                try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",", 2);
                        if (parts.length >= 1) {
                            existingNames.add(parts[0]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize CSV file repository", e);
        }
    }

    @Override
    public void addTodo(Todo todo) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            writer.write(todo.getName() + "," + (todo.getDueDate() != null ? todo.getDueDate() : "") + "\n");
            existingNames.add(todo.getName());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write Todo to CSV file", e);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return existingNames.contains(name);
    }

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                String name = parts[0];
                String dueDate = parts.length > 1 ? parts[1] : null;
                todos.add(new Todo(name, dueDate != null && !dueDate.isBlank() ? dueDate : null));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Todos from CSV file", e);
        }
        return todos;
    }
}
