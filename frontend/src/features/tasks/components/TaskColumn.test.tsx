import { render, screen } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import TaskColumn from "./TaskColumn";
import type { Task } from "../../../types/Task";
import userEvent from "@testing-library/user-event";

describe("TaskColumn", () => {
  const openTask: Task = { id: "1", title: "refactor tests", status: "OPEN" };
  const closedTask: Task = {
    id: "2",
    title: "create test pipelines",
    status: "CLOSED",
  };
  const tasks: Task[] = [openTask, closedTask] as const;

  it("splits tasks into open and closed columns", () => {
    render(
      <TaskColumn
        tasks={tasks}
        onCreateTask={vi.fn()}
        onTaskUpdate={vi.fn()}
      />,
    );

    expect(screen.getByText("refactor tests")).toBeInTheDocument();
    expect(screen.getByText("create test pipelines")).toBeInTheDocument();
  });
  it("opens and closes create task form", async () => {
    const user = userEvent.setup();

    render(
      <TaskColumn tasks={[]} onTaskUpdate={vi.fn()} onCreateTask={vi.fn()} />,
    );

    await user.click(screen.getByRole("button", { name: "new" }));

    expect(screen.getByPlaceholderText("New task...")).toBeInTheDocument();

    await user.click(screen.getByRole("button", { name: "hide" }));

    expect(
      screen.queryByPlaceholderText("New task..."),
    ).not.toBeInTheDocument();
  });
  it("creates task and closes form", async () => {
    const user = userEvent.setup();
    const onCreateTask = vi.fn();

    render(
      <TaskColumn
        tasks={[]}
        onTaskUpdate={vi.fn()}
        onCreateTask={onCreateTask}
      />,
    );

    await user.click(screen.getByRole("button", { name: "new" }));

    await user.type(
      screen.getByPlaceholderText("New task..."),
      "refactor tests",
    );

    await user.click(screen.getByRole("button", { name: "Save" }));

    expect(onCreateTask).toHaveBeenCalledWith("refactor tests");

    expect(
      screen.queryByPlaceholderText("New task..."),
    ).not.toBeInTheDocument();
  });
});
