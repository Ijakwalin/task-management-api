package com.example.taskmanagement.service;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.Tag;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.TagRepository;
import com.example.taskmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {

        @Autowired
        private TaskRepository taskRepository;

        @Autowired
        private TagRepository tagRepository;

        @Transactional
        public Task createTask(Task task) {
                Set<Tag> tags = task.getTags().stream()
                                .map(tag -> tagRepository.findByName(tag.getName())
                                                .orElseGet(() -> tagRepository.save(new Tag(tag.getName()))))
                                .collect(Collectors.toSet());
                task.setTags(tags);
                return taskRepository.save(task);
        }

        @Transactional
        public Task updateTask(Long taskId, Task taskDetails) {
                Task task = taskRepository.findById(taskId)
                                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

                task.setTitle(taskDetails.getTitle());
                task.setStatus(taskDetails.getStatus());

                Set<Tag> updatedTags = taskDetails.getTags().stream()
                                .map(tag -> tagRepository.findByName(tag.getName())
                                                .orElseGet(() -> tagRepository.save(new Tag(tag.getName()))))
                                .collect(Collectors.toSet());

                task.getTags().clear();
                task.getTags().addAll(updatedTags);

                return taskRepository.save(task);
        }

        public void deleteTask(Long taskId) {
                if (!taskRepository.existsById(taskId)) {
                        throw new ResourceNotFoundException("Task not found with id: " + taskId);
                }
                taskRepository.deleteById(taskId);
        }

        public List<Task> getAllTasks() {
                return taskRepository.findAll();
        }

        public List<Task> getTasks(String status, List<String> tags) {
                return taskRepository.findByStatusAndTags(status, tags);
        }
}
