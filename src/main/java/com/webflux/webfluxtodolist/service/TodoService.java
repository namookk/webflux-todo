package com.webflux.webfluxtodolist.service;

import com.webflux.webfluxtodolist.entity.Todo;
import com.webflux.webfluxtodolist.repository.TodoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Flux<Todo> getAll() {
        return Flux.fromIterable(todoRepository.findAll());
    }

    public Mono<Todo> save(Todo todo){
        return Mono.fromCallable(() -> todoRepository.save(todo)).subscribeOn(Schedulers.parallel());
    }

}
