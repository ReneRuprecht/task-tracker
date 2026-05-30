import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

import { describe, expect, it, vi } from "vitest";
import CreateTaskCard from "./CreateTaskCard";

describe("CreateTaskCard", () => {
  it("renders input and buttons", () => {
    render(<CreateTaskCard onCreate={vi.fn()} onCancel={vi.fn()} />);

    expect(screen.getByPlaceholderText("New task...")).toBeInTheDocument();

    expect(screen.getByRole("button", { name: "Save" })).toBeInTheDocument();

    expect(screen.getByRole("button", { name: "Cancel" })).toBeInTheDocument();
  });

  it("updates input value when typing", async () => {
    render(<CreateTaskCard onCreate={vi.fn()} onCancel={vi.fn()} />);

    const user = userEvent.setup();
    const input = screen.getByPlaceholderText("New task...");

    await user.type(input, "refactor tests");
    expect(input).toHaveValue("refactor tests");
  });
  it("calls onCreate with title when Save is clicked", async () => {
    const user = userEvent.setup();
    const onCreate = vi.fn();

    render(<CreateTaskCard onCreate={onCreate} onCancel={vi.fn()} />);

    const input = screen.getByPlaceholderText("New task...");
    const saveButton = screen.getByRole("button", { name: "Save" });

    await user.type(input, "refactor tests");
    await user.click(saveButton);

    expect(onCreate).toHaveBeenCalledWith("refactor tests");
  });
  it("renders save button as disabled when input is empty", async () => {
    render(<CreateTaskCard onCreate={vi.fn()} onCancel={vi.fn()} />);

    const saveButton = screen.getByRole("button", { name: "Save" });

    expect(saveButton).toBeDisabled();
  });

  it("calls onCancel when Cancel is clicked", async () => {
    const user = userEvent.setup();
    const onCancel = vi.fn();

    render(<CreateTaskCard onCreate={vi.fn()} onCancel={onCancel} />);

    const cancelButton = screen.getByRole("button", { name: "Cancel" });

    await user.click(cancelButton);

    expect(onCancel).toHaveBeenCalled();
  });
  it("creates task when Enter is pressed", async () => {
    const user = userEvent.setup();
    const onCreate = vi.fn();

    render(<CreateTaskCard onCreate={onCreate} onCancel={vi.fn()} />);

    const input = screen.getByPlaceholderText("New task...");

    await user.type(input, "refactor tests{Enter}");

    expect(onCreate).toHaveBeenCalledWith("refactor tests");
  });
  it("calls onCancel when Escape is pressed", async () => {
    const user = userEvent.setup();
    const onCancel = vi.fn();

    render(<CreateTaskCard onCreate={vi.fn()} onCancel={onCancel} />);

    const input = screen.getByPlaceholderText("New task...");

    await user.type(input, "{Escape}");

    expect(onCancel).toHaveBeenCalled();
  });
});
