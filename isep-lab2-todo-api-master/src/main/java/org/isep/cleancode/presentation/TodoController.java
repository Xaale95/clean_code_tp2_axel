package org.isep.cleancode.presentation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.isep.cleancode.Todo;
import org.isep.cleancode.application.TodoManager;
import spark.Request;
import spark.Response;

public class TodoController {

    private static final Gson gson = new Gson();
    private final TodoManager todoManager;

    public TodoController(TodoManager todoManager) {
        this.todoManager = todoManager;
    }

    public Object getAllTodos(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(todoManager.getAllTodos());
    }

    public Object createTodo(Request req, Response res) {
        try {
            JsonObject json = JsonParser.parseString(req.body()).getAsJsonObject();
            String name = json.has("name") ? json.get("name").getAsString() : null;
            String dueDate = json.has("dueDate") ? json.get("dueDate").getAsString() : null;

            Todo newTodo = todoManager.createTodo(name, dueDate);

            res.status(201);
            res.type("application/json");
            return gson.toJson(newTodo);

        } catch (IllegalArgumentException e) {
            res.status(400);
            return e.getMessage();
        } catch (Exception e) {
            res.status(400);
            return "Invalid request format.";
        }
    }
}
