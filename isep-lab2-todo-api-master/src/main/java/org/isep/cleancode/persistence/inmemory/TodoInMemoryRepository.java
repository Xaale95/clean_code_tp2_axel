package org.isep.cleancode.persistence.inmemory;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.ITodoRepository;

import java.util.*;

public class TodoInMemoryRepository implements ITodoRepository {

    private final List<Todo> todos = new ArrayList<>();
    private final Set<String> names = new HashSet<>();

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);
        names.add(todo.getName());
    }

    @Override
    public boolean existsByName(String name) {
        return names.contains(name);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }
}
