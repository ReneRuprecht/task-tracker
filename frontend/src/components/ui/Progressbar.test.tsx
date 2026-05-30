import { describe, it, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import { ProgressBar } from "./Progressbar";

describe("Progressbar", () => {
  it("displays the progress percentage", () => {
    render(<ProgressBar value={42} />);
    const progressValue = screen.getByText("42% complete");
    expect(progressValue).toBeInTheDocument();
  });

  it("sets the fill width based on the value", () => {
    render(<ProgressBar value={42} />);

    const fill = screen.getByRole("progressbar");

    expect(fill).toHaveStyle({ width: "42%" });
  });

  it("renders the blue gradient fill when progress is incomplete", () => {
    render(<ProgressBar value={42} />);

    const fill = screen.getByRole("progressbar");

    expect(fill).toHaveClass("bg-linear-to-r", "from-blue-500", "to-blue-400");
  });

  it("renders a green fill when progress is complete", () => {
    render(<ProgressBar value={100} />);

    const fill = screen.getByRole("progressbar");

    expect(fill).toHaveClass("bg-green-500");
  });
});
