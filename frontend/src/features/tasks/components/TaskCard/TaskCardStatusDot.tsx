import type { TaskStatus } from "../../../../types/Task";
import { cn } from "../../../../utils/tailwind";
import { getTaskCardDotStyle } from "./taskCard.styles";
import type { HoverMode } from "./taskCard.types";

interface TaskCardStatusDotProps {
  status: TaskStatus;
  hoverMode: HoverMode;
}

export default function TaskCardStatusDot({
  status,
  hoverMode,
}: TaskCardStatusDotProps) {
  const taskCardDotStyle = getTaskCardDotStyle(status, hoverMode);
  return (
    <div
      className={cn(
        "h-2.5 w-2.5 rounded-full",
        "transition-all duration-700 ease-out",
        taskCardDotStyle,
      )}
    />
  );
}
