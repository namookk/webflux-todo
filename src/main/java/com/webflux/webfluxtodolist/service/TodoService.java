package com.webflux.webfluxtodolist.service;

import com.webflux.webfluxtodolist.entity.Todo;
import com.webflux.webfluxtodolist.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Slf4j
public class TodoService {
    private final TodoRepository todoRepository;

    private final EntityManager em;

    public TodoService(TodoRepository todoRepository, EntityManager em) {
        this.todoRepository = todoRepository;
        this.em = em;
    }

    public Flux<Todo> findAll() {
        return Flux.fromIterable(todoRepository.findAll());
    }

    public Flux<Todo> findById(Long id){
        return Flux.just(
                todoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("데이터가 존재하지 않습니다."))
        );
    }

    @Transactional
    public Mono<Todo> save(Todo todo){
        log.info("todo == {}", todo);
        return Mono.fromCallable(() -> todoRepository.save(todo))
                .subscribeOn(Schedulers.parallel())
                .log();
    }

}
