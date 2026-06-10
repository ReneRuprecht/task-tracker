import { useState } from "react";
import type { Task, TaskStatus } from "../../../../types/Task";
import { cn } from "../../../../utils/tailwind";
import { toggleTaskStatus } from "../../utils/taskStatus";
import type { HoverMode } from "./taskCard.types";
import TaskCardStatusDot from "./TaskCardStatusDot";
import { TaskCardTitle } from "./TaskCardTitle/TaskCardTitle";
import { getTaskCardWrapperStyle } from "./taskCard.styles";

interface TaskCardProps {
  task: Task;
  onUpdated: (task: Task) => void;
}

export default function TaskCard({ task, onUpdated }: TaskCardProps) {
  const [hoverMode, setHoverMode] = useState<HoverMode>(null);
  const handleToggle = async () => {
    const newStatus: TaskStatus = toggleTaskStatus(task.status);

    const updatedTask = { ...task, status: newStatus };

    onUpdated(updatedTask);
  };
  const taskCardWrapperStyle = getTaskCardWrapperStyle(task.status, hoverMode);
  const [isEditing, setIsEditing] = useState(false);
  const [title, setTitle] = useState(task.title);

  const startEdit = () => {
    setIsEditing(true);
  };

  const cancelEdit = () => {
    setIsEditing(false);
  };

  const saveEdit = () => {
    const updatedTask = { ...task, title: title };
    onUpdated(updatedTask);
    setIsEditing(false);
  };

  return (
    <div
      role="button"
      onClick={hoverMode === "toggle" ? handleToggle : () => {}}
      className={cn(
        "group mx-10 flex cursor-pointer items-center justify-between",
        "rounded-lg border-4 border-white bg-white px-4 py-3",
        "transition-all duration-700 ease-out",
        taskCardWrapperStyle,
      )}
      onMouseEnter={() => setHoverMode("toggle")}
      onMouseLeave={() => setHoverMode(null)}
    >
      <div
        className="flex items-center gap-3"
        onMouseEnter={() => setHoverMode("edit")}
        onMouseLeave={() => setHoverMode(null)}
      >
        <TaskCardStatusDot status={task.status} hoverMode={hoverMode} />
        <TaskCardTitle
          title={title}
          isEditing={isEditing}
          onStartEdit={startEdit}
          onChange={setTitle}
          onSave={saveEdit}
          onCancel={cancelEdit}
        />
      </div>
    </div>
  );
}
