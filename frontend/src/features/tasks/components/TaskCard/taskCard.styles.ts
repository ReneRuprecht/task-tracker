import type { TaskStatus } from "../../../../types/Task";
import type { HoverMode } from "./taskCard.types";

export function getTaskCardDotStyle(status: TaskStatus, hoverMode: HoverMode) {
  const style =
    hoverMode === "edit"
      ? "bg-blue-500"
      : status === "CLOSED"
        ? "bg-green-500 group-hover:bg-yellow-500"
        : "bg-yellow-500 group-hover:bg-green-500";

  return style;
}
export function getTaskCardWrapperStyle(
  status: TaskStatus,
  hoverMode: HoverMode,
) {
  const border =
    hoverMode === "edit"
      ? "border-blue-500"
      : status === "CLOSED"
        ? "hover:border-yellow-500"
        : "hover:border-green-500";

  return border;
}
