package com.example.task_service.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class Task {

    @Getter
    private final TaskID id;

    @Getter
    @Setter
    private TaskTitle title;

    @Getter
    @Setter
    private TaskStatus status;


}
