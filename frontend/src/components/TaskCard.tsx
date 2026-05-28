import { useState } from "react";
import type { Task, TaskStatus } from "../types/Task";
import { cn } from "../utils/tailwind";

type TaskCardMode = "view" | "create";

interface TaskCardProps {
  mode?: TaskCardMode;

  task?: Task;

  onUpdated?: (taskID: string, status: TaskStatus) => void;

  onCreate?: (title: string) => void;

  onCancel?: () => void;
}

export default function TaskCard({
  mode = "view",
  task,
  onUpdated,
  onCreate,
  onCancel,
}: TaskCardProps) {
  const [title, setTitle] = useState("");

  if (mode === "create") {
    const handleSave = () => {
      if (!title.trim()) {
        return;
      }

      onCreate?.(title);

      setTitle("");
    };

    return (
      <div
        className={cn(
          "mx-10 rounded-lg border border-dashed border-gray-300",
          "bg-gray-50 px-4 py-3",
          "transition-all duration-300 ease-out",
        )}
      >
        <div className="flex items-center gap-3">
          <div className="h-2.5 w-2.5 rounded-full bg-yellow-500" />

          <input
            autoFocus
            type="text"
            value={title}
            placeholder="New task..."
            onChange={(e) => setTitle(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter") {
                handleSave();
              }

              if (e.key === "Escape") {
                onCancel?.();
              }
            }}
            className={cn(
              "w-full rounded-md border border-gray-200",
              "bg-white px-3 py-2 text-sm",
              "transition-all outline-none",
              "focus:border-blue-400",
            )}
          />
        </div>

        <div className="mt-3 flex justify-end gap-3">
          <button
            onClick={onCancel}
            className={cn(
              "text-sm text-gray-500",
              "transition-colors hover:text-gray-700",
            )}
          >
            Cancel
          </button>

          <button
            onClick={handleSave}
            className={cn(
              "text-sm font-medium text-green-600",
              "transition-colors hover:text-green-700",
            )}
          >
            Save
          </button>
        </div>
      </div>
    );
  }

  if (!task) {
    return null;
  }

  const handleToggle = async () => {
    const newStatus: TaskStatus = task.status === "CLOSED" ? "OPEN" : "CLOSED";

    onUpdated?.(task.id, newStatus);
  };

  return (
    <div
      onClick={handleToggle}
      className={cn(
        "group mx-10 flex cursor-pointer items-center justify-between",
        "rounded-lg border-4 border-white bg-white px-4 py-3",
        "transition-all duration-700 ease-out",
        task.status === "OPEN"
          ? "hover:border-green-500"
          : "hover:border-yellow-500",
      )}
    >
      <div className="flex items-center gap-3">
        <div
          className={cn(
            "h-2.5 w-2.5 rounded-full",
            "transition-all duration-700 ease-out",
            task.status === "CLOSED"
              ? "bg-green-500 group-hover:bg-yellow-500"
              : "bg-yellow-500 group-hover:bg-green-500",
          )}
        />

        <span className="font-medium text-gray-900">{task.name}</span>
      </div>
    </div>
  );
}
