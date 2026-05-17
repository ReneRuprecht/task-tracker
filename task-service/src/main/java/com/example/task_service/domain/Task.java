package com.example.task_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Task {

    @Getter
    private final TaskID id;
    @Getter
    private final TaskName name;
    @Getter
    private final TaskStatus status;


}
