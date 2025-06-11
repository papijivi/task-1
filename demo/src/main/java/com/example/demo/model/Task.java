package com.example.demo.model;


import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




public class Task {
    @Id
    private String id;
    private String name;
    private String owner;
    private String command;

    private List<TaskExecution> taskExecutions = new ArrayList<>();
    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }
// Getters and Setters

    public String getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    public List<TaskExecution> getTaskExecutions() {
        return taskExecutions;
    }

    public void setTaskExecutions(List<TaskExecution> taskExecutions) {
        this.taskExecutions = taskExecutions;
    }


}




