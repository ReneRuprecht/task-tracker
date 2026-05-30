import { describe, expect, it } from "vitest";
import { toggleTaskStatus } from "./taskStatus";

describe("taskStatus", () => {
  it("toggles OPEN to CLOSED", () => {
    expect(toggleTaskStatus("OPEN")).toBe("CLOSED");
  });

  it("toggles CLOSED to OPEN", () => {
    expect(toggleTaskStatus("CLOSED")).toBe("OPEN");
  });
});
