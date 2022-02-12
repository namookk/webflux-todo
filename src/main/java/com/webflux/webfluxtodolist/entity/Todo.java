package com.webflux.webfluxtodolist.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;

@Entity
@Table(name="todo")
public class Todo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    private Boolean done;

    @Column(name="regdate")
    private LocalDateTime createdAt;

    @Column(name = "regid")
    private String createdBy;

    @Column(name = "upddate")
    private LocalDateTime modifiedAt;

    @Column(name = "updid")
    private String modifiedBy;
}
