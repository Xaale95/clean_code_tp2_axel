package org.isep.cleancode;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Todo {

    private String name;
    private String dueDate; // format ISO (ex: 2025-05-12)

    public Todo(String name, String dueDate) {
        setName(name);
        setDueDate(dueDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (name.length() > 63) {
            throw new IllegalArgumentException("Name must be shorter than 64 characters.");
        }
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        if (dueDate != null && !dueDate.isBlank()) {
            try {
                LocalDate.parse(dueDate); // throws if invalid
                this.dueDate = dueDate;
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Due date is invalid. Expected format: yyyy-MM-dd");
            }
        } else {
            this.dueDate = null;
        }
    }
}
