package org.isep.cleancode;

import org.isep.cleancode.application.ITodoRepository;
import org.isep.cleancode.application.TodoManager;
import org.isep.cleancode.persistence.csvfiles.TodoCsvFilesRepository;
import org.isep.cleancode.persistence.inmemory.TodoInMemoryRepository;
import org.isep.cleancode.presentation.TodoController;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(4567);

        // Test for the step 3
        // ITodoRepository repository = new TodoInMemoryRepository();

        //Test for the step 4, with CSV file
        ITodoRepository repository = new TodoCsvFilesRepository();


        TodoManager manager = new TodoManager(repository);
        TodoController controller = new TodoController(manager);

        get("/todos", controller::getAllTodos);
        post("/todos", controller::createTodo);
    }
}
