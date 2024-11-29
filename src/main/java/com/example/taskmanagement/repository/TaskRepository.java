package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT DISTINCT t FROM Task t JOIN t.tags tag WHERE (:status IS NULL OR t.status = :status) AND (:tags IS NULL OR tag.name IN :tags)")
    List<Task> findByStatusAndTags(@Param("status") String status, @Param("tags") List<String> tags);
}

