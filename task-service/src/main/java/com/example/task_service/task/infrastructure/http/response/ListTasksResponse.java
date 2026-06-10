package com.example.task_service.task.infrastructure.http.response;


import java.util.List;

public record ListTasksResponse(List<TaskResponse> tasks) {
}

