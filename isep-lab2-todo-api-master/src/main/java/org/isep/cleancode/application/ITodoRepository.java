package org.isep.cleancode.application;

import org.isep.cleancode.Todo;

import java.util.List;

public interface ITodoRepository {
    void addTodo(Todo todo);
    boolean existsByName(String name);
    List<Todo> getAllTodos();
}
