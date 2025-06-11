package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Task;
import com.example.demo.model.TaskExecution;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public List<Task> findTasksByName(String name) {
        return taskRepository.findByNameContainingIgnoreCase(name);
    }

    public Task createTask(Task task) {
        if (!isSafeCommand(task.getCommand())) {
            throw new IllegalArgumentException("Unsafe command detected!");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public Task executeTask(String id) throws Exception {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Date start = new Date();
        String output = runShellCommand(task.getCommand());
        Date end = new Date();

        TaskExecution exec = new TaskExecution();
        exec.setStartTime(start);
        exec.setEndTime(end);
        exec.setOutput(output);

        task.getTaskExecutions().add(exec);
        return taskRepository.save(task);


    }

    private String runShellCommand(String command) throws Exception {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", command);

        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        process.waitFor();
        return output.toString().trim();
    }

    private boolean isSafeCommand(String cmd) {
        return !(cmd.contains("rm") || cmd.contains("shutdown") || cmd.contains("kill") || cmd.contains("wget") || cmd.contains("curl"));
    }
}