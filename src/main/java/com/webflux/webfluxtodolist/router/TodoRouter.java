package com.webflux.webfluxtodolist.router;

import com.webflux.webfluxtodolist.handler.TodoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.persistence.Column;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class TodoRouter {

    @Bean
    public RouterFunction<ServerResponse> todoRouterFuncion(TodoHandler todoHandler) {
        return RouterFunctions.route()
                .GET("/todos", request -> todoHandler.getAllTodo(request))
                .GET("/todos/{id}", request -> todoHandler.getById(request))
                .POST("/todos", accept(MediaType.APPLICATION_JSON), todoHandler::saveTodo)
                .PATCH("/todos/do/{id}", request -> todoHandler.doTodo(request))
                .DELETE("/todos/{id}", request -> todoHandler.deleteById(request))
                .build();
    }
}
