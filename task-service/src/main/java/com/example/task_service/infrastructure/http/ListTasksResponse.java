package com.example.task_service.infrastructure.http;


import java.util.List;

public record ListTasksResponse(List<TaskResponse> tasks) {
}

