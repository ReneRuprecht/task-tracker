import { describe, expect, it, vi } from "vitest";
import OpenTaskColumn from "./OpenTaskColumn";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

describe("OpenTaskColumn", () => {
  it("renders without create form by default", () => {
    render(
      <OpenTaskColumn
        openTasks={[]}
        isCreating={false}
        onOpenCreate={vi.fn()}
        onCloseCreate={vi.fn()}
        onCreateTask={vi.fn()}
        onTaskUpdate={vi.fn()}
      />,
    );

    expect(
      screen.queryByPlaceholderText("New Task..."),
    ).not.toBeInTheDocument();
  });
  it("calls onOpenCreate when clicking new", async () => {
    const user = userEvent.setup();
    const onOpenCreate = vi.fn();

    render(
      <OpenTaskColumn
        openTasks={[]}
        isCreating={false}
        onOpenCreate={onOpenCreate}
        onCloseCreate={vi.fn()}
        onCreateTask={vi.fn()}
        onTaskUpdate={vi.fn()}
      />,
    );

    await user.click(screen.getByRole("button", { name: "new" }));

    expect(onOpenCreate).toHaveBeenCalled();
  });
  it("calls onCloseCreate when clicking hide", async () => {
    const user = userEvent.setup();
    const onCloseCreate = vi.fn();

    render(
      <OpenTaskColumn
        openTasks={[]}
        isCreating={true}
        onOpenCreate={vi.fn()}
        onCloseCreate={onCloseCreate}
        onCreateTask={vi.fn()}
        onTaskUpdate={vi.fn()}
      />,
    );

    await user.click(screen.getByRole("button", { name: "hide" }));

    expect(onCloseCreate).toHaveBeenCalled();
  });

  it("calls onTaskUpdate when a task is toggled", async () => {
    const user = userEvent.setup();
    const onTaskUpdate = vi.fn();

    render(
      <OpenTaskColumn
        openTasks={[{ id: "1", name: "refactor tests", status: "OPEN" }]}
        isCreating={false}
        onOpenCreate={vi.fn()}
        onCloseCreate={vi.fn()}
        onCreateTask={vi.fn()}
        onTaskUpdate={onTaskUpdate}
      />,
    );

    await user.click(screen.getByText("refactor tests"));

    expect(onTaskUpdate).toHaveBeenCalledWith("1", "CLOSED");
  });
  it("creates task and closes form", async () => {
    const user = userEvent.setup();
    const onCreateTask = vi.fn();

    render(
      <OpenTaskColumn
        openTasks={[]}
        isCreating={true}
        onOpenCreate={vi.fn()}
        onCloseCreate={vi.fn()}
        onCreateTask={onCreateTask}
        onTaskUpdate={vi.fn()}
      />,
    );

    await user.type(
      screen.getByPlaceholderText("New task..."),
      "refactor tests",
    );

    await user.click(screen.getByRole("button", { name: "Save" }));

    expect(onCreateTask).toHaveBeenCalledWith("refactor tests");
  });

  it("renders open tasks", () => {
    render(
      <OpenTaskColumn
        openTasks={[{ id: "1", name: "refactor tests", status: "OPEN" }]}
        isCreating={false}
        onOpenCreate={vi.fn()}
        onCloseCreate={vi.fn()}
        onCreateTask={vi.fn()}
        onTaskUpdate={vi.fn()}
      />,
    );

    expect(screen.getByText("refactor tests")).toBeInTheDocument();
  });
});
