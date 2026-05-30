import { describe, expect, it, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import ClosedTaskColumn from "./ClosedTaskCollumn";
import userEvent from "@testing-library/user-event";

describe("ClosedTaskColumn", () => {
  it("renders closed tasks", () => {
    render(
      <ClosedTaskColumn
        closedTasks={[{ id: "1", name: "refactor tests", status: "CLOSED" }]}
        onTaskUpdate={vi.fn()}
      />,
    );

    expect(screen.getByText("refactor tests")).toBeInTheDocument();
  });
  it("calls onTaskUpdate when a task is toggled", async () => {
    const user = userEvent.setup();
    const onTaskUpdate = vi.fn();

    render(
      <ClosedTaskColumn
        closedTasks={[{ id: "1", name: "refactor tests", status: "CLOSED" }]}
        onTaskUpdate={onTaskUpdate}
      />,
    );

    await user.click(screen.getByText("refactor tests"));

    expect(onTaskUpdate).toHaveBeenCalledWith("1", "OPEN");
  });
});
