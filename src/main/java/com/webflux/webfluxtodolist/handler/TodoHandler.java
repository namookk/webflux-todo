package com.webflux.webfluxtodolist.handler;

import com.webflux.webfluxtodolist.entity.Todo;
import com.webflux.webfluxtodolist.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

@Component
@Slf4j
public class TodoHandler {

    private final TodoService todoService;

    public TodoHandler(TodoService todoService) {
        this.todoService = todoService;
    }

    public Mono<ServerResponse> getAllTodo(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(todoService.findAll(), Todo.class)
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        Long id = Long.parseLong(serverRequest.pathVariable("id"));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(todoService.findById(id), Todo.class)
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> saveTodo(ServerRequest serverRequest) {
        //TODO: 이벤트 구독관련 자료 찾아서 이슈 해결해야함

        return serverRequest.bodyToMono(Todo.class)
                .map(it -> todoService.save(it))
                .flatMap(it -> {
                    return ServerResponse.ok().body(it, Todo.class);
                }).log();
    }

    public Mono<ServerResponse> doTodo(ServerRequest request) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(todoService.findAll(), Todo.class)
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(todoService.findAll(), Todo.class)
                .switchIfEmpty(notFound().build());
    }
}
