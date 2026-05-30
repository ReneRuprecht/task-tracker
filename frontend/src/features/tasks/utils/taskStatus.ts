import type { TaskStatus } from "../../../types/Task";

export function toggleTaskStatus(status: TaskStatus): TaskStatus {
  return status === "CLOSED" ? "OPEN" : "CLOSED";
}
