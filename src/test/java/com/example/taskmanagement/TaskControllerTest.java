package com.example.taskmanagement;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import com.example.taskmanagement.controller.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TaskService taskService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @WithMockUser(roles = "USER")
  void testCreateTask() throws Exception {
    Task task = new Task("Test Task", "Pending");

    when(taskService.createTask(task)).thenReturn(task);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\":\"Test Task\",\"status\":\"Pending\"}"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task"));
  }

  @Test
  @WithMockUser(roles = "USER")
  void testGetAllTasks() throws Exception {
    when(taskService.getAllTasks()).thenReturn(Collections.emptyList());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  // Temporary Security Configuration for Testing
  @Configuration
  static class TestSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for testing
          .authorizeHttpRequests(authz -> authz
              .anyRequest().permitAll()); // Allow all requests without authentication
      return http.build();
    }
  }
}