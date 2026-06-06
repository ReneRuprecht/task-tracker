import { describe, expect, it } from "vitest";
import { getClosedTasks, getOpenTasks } from "./taskFilters";

describe("taskFilters", () => {
  it("filters open tasks", () => {
    const result = getOpenTasks([
      { id: "1", title: "a", status: "OPEN" },
      { id: "2", title: "b", status: "CLOSED" },
    ]);

    expect(result).toHaveLength(1);
  });
  it("filters closed tasks", () => {
    const result = getClosedTasks([
      { id: "1", title: "a", status: "OPEN" },
      { id: "2", title: "b", status: "CLOSED" },
      { id: "3", title: "c", status: "CLOSED" },
    ]);

    expect(result).toHaveLength(2);
  });
});
