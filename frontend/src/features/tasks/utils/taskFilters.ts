import type { Task } from "../../../types/Task";

export function getOpenTasks(tasks: Task[]) {
  return tasks.filter((task) => task.status === "OPEN");
}

export function getClosedTasks(tasks: Task[]) {
  return tasks.filter((task) => task.status === "CLOSED");
}
