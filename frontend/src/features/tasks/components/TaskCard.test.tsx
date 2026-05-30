import { render, screen } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import TaskCard from "./TaskCard";
import type { Task } from "../../../types/Task";

describe("TaskCard", () => {
  const openTask: Task = { id: "1", name: "refactor tests", status: "OPEN" };
  const closedTask: Task = {
    id: "2",
    name: "create test pipelines",
    status: "CLOSED",
  };
  it("renders the task name", () => {
    render(<TaskCard task={openTask} onUpdated={vi.fn()} />);

    expect(screen.getByText("refactor tests")).toBeInTheDocument();
  });
  it("updates an open task to closed", () => {
    const onUpdated = vi.fn();
    render(<TaskCard task={openTask} onUpdated={onUpdated} />);

    screen
      .getByRole("button", {
        name: "refactor tests",
      })
      .click();

    expect(onUpdated).toHaveBeenCalledWith("1", "CLOSED");
  });
  it("updates a closed task to open", () => {
    const onUpdated = vi.fn();
    render(<TaskCard task={closedTask} onUpdated={onUpdated} />);

    screen
      .getByRole("button", {
        name: "create test pipelines",
      })
      .click();

    expect(onUpdated).toHaveBeenCalledWith("2", "OPEN");
  });
});
