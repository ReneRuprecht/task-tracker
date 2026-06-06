import { render, screen } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import TaskCard from "./TaskCard";
import userEvent from "@testing-library/user-event";
import type { Task } from "../../../../types/Task";

describe("TaskCard", () => {
  const openTask: Task = { id: "1", title: "refactor tests", status: "OPEN" };
  const closedTask: Task = {
    id: "2",
    title: "create test pipelines",
    status: "CLOSED",
  };
  it("renders the task title", () => {
    render(<TaskCard task={openTask} onUpdated={vi.fn()} />);

    expect(screen.getByText("refactor tests")).toBeInTheDocument();
  });
  it("updates an open task to closed", async () => {
    const onUpdated = vi.fn();
    render(<TaskCard task={openTask} onUpdated={onUpdated} />);

    const user = userEvent.setup();

    await user.click(
      screen.getByRole("button", {
        title: "refactor tests",
      }),
    );

    expect(onUpdated).toHaveBeenCalledWith("1", "refactor tests", "CLOSED");
  });
  it("updates a closed task to open", async () => {
    const onUpdated = vi.fn();
    render(<TaskCard task={closedTask} onUpdated={onUpdated} />);

    const user = userEvent.setup();

    await user.click(
      screen.getByRole("button", {
        title: "create test pipelines",
      }),
    );

    expect(onUpdated).toHaveBeenCalledWith(
      "2",
      "create test pipelines",
      "OPEN",
    );
  });

  it("enters edit mode when clicking title", async () => {
    const user = userEvent.setup();

    render(
      <TaskCard
        task={{ id: "1", title: "refactor tests", status: "OPEN" }}
        onUpdated={vi.fn()}
      />,
    );

    await user.click(screen.getByText("refactor tests"));

    expect(screen.getByDisplayValue("refactor tests")).toBeInTheDocument();
  });
  it("cancels edit and resets title", async () => {
    const user = userEvent.setup();
    const onUpdated = vi.fn();

    render(
      <TaskCard
        task={{ id: "1", title: "refactor tests", status: "OPEN" }}
        onUpdated={onUpdated}
      />,
    );

    await user.click(screen.getByText("refactor tests"));
    await user.keyboard("{Escape}");

    expect(onUpdated).not.toHaveBeenCalled();
    expect(screen.getByText("refactor tests")).toBeInTheDocument();
  });
  it("saves edited title", async () => {
    const user = userEvent.setup();
    const onUpdated = vi.fn();

    render(
      <TaskCard
        task={{ id: "1", title: "refactor tests", status: "OPEN" }}
        onUpdated={onUpdated}
      />,
    );

    await user.click(screen.getByText("refactor tests"));

    const input = screen.getByDisplayValue("refactor tests");
    await user.clear(input);
    await user.type(input, "updated title");

    await user.keyboard("{Enter}");

    expect(onUpdated).toHaveBeenCalledWith("1", "updated title", "OPEN");
  });
  it("resets hover mode on outer taskcard wrapper div mouse leave", async () => {
    const user = userEvent.setup();

    render(
      <TaskCard
        task={{ id: "1", title: "refactor tests", status: "OPEN" }}
        onUpdated={vi.fn()}
      />,
    );

    const card = screen.getByRole("button");

    await user.hover(card);
    expect(card).toHaveClass("hover:border-green-500");
    await user.unhover(card);

    expect(card).toHaveClass("border-white");
  });

  it("resets hover mode on inner taskcard wrapper div mouse leave", async () => {
    const user = userEvent.setup();

    render(
      <TaskCard
        task={{ id: "1", title: "refactor tests", status: "OPEN" }}
        onUpdated={vi.fn()}
      />,
    );

    const card = screen.getByRole("button");
    const innerDiv = screen.getByText("refactor tests");

    await user.hover(innerDiv);
    expect(card).toHaveClass("border-blue-500");
    await user.unhover(innerDiv);

    expect(card).toHaveClass("border-white");
  });
});
