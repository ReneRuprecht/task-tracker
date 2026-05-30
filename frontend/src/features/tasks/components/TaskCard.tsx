import type { Task, TaskStatus } from "../../../types/Task";
import { cn } from "../../../utils/tailwind";
import { toggleTaskStatus } from "../utils/taskStatus";

interface TaskCardProps {
  task: Task;
  onUpdated: (taskID: string, status: TaskStatus) => void;
}

export default function TaskCard({ task, onUpdated }: TaskCardProps) {
  const handleToggle = async () => {
    const newStatus: TaskStatus = toggleTaskStatus(task.status);

    onUpdated(task.id, newStatus);
  };

  return (
    <div
      role="button"
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
