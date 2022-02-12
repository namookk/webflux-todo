package com.webflux.webfluxtodolist.repository;

import com.webflux.webfluxtodolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
