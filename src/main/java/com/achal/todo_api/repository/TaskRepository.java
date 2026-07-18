package com.achal.todo_api.repository;

import com.achal.todo_api.entity.Task;
import com.achal.todo_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByUser(User user);

    Optional<Task> findByIdAndUser(Long id,User user);
}
