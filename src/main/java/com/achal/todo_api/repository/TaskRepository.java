package com.achal.todo_api.repository;

import com.achal.todo_api.entity.Task;
import com.achal.todo_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByUser(User user);
}
