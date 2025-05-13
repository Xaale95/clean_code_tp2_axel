package org.isep.cleancode.application;

import org.isep.cleancode.Todo;

import java.util.List;

public class TodoManager {

    private final ITodoRepository repository;

    public TodoManager(ITodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.getAllTodos();
    }

    public Todo createTodo(String name, String dueDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (name.length() > 63) {
            throw new IllegalArgumentException("Name must be shorter than 64 characters.");
        }
        if (repository.existsByName(name)) {
            throw new IllegalArgumentException("Todo name must be unique.");
        }

        Todo todo = new Todo(name, dueDate);
        repository.addTodo(todo);
        return todo;
    }
}
