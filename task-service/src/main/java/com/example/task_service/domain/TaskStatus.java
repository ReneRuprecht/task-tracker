package com.example.task_service.domain;

public class TaskStatus {

    private Status taskStatus;

    private TaskStatus(Status status) {
        this.taskStatus = status;
    }

    public static TaskStatus newTaskStatus() {
        return new TaskStatus(Status.OPEN);
    }

    public static TaskStatus fromStatus(Status status) {
        return new TaskStatus(status);
    }

    public void close() {
        this.taskStatus = Status.CLOSED;
    }

    public boolean isClosed() {
        return this.taskStatus == Status.CLOSED;
    }

    public Status value() {
        return this.taskStatus;
    }

    public enum Status {
        OPEN,
        CLOSED
    }

}
