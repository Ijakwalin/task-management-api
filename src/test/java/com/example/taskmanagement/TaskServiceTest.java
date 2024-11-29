package com.example.taskmanagement;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private TaskService taskService;

  private Task task;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    task = new Task("Test Task", "Pending");
  }

  @Test
  void testCreateTask() {
    when(taskRepository.save(any(Task.class))).thenReturn(task);

    Task createdTask = taskService.createTask(task);

    assertNotNull(createdTask);
    assertEquals("Test Task", createdTask.getTitle());
    assertEquals("Pending", createdTask.getStatus());

    verify(taskRepository, times(1)).save(any(Task.class));
  }

  @Test
  void testUpdateTask() {
    task.setTitle("Updated Task");

    when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
    when(taskRepository.save(any(Task.class))).thenReturn(task);

    Task updatedTask = taskService.updateTask(1L, task);

    assertNotNull(updatedTask);
    assertEquals("Updated Task", updatedTask.getTitle());
    assertEquals("Pending", updatedTask.getStatus());

    verify(taskRepository, times(1)).save(any(Task.class));
  }

  @Test
  void testDeleteTask() {
    when(taskRepository.existsById(anyLong())).thenReturn(true);

    taskService.deleteTask(1L);

    verify(taskRepository, times(1)).deleteById(1L);
  }
}
