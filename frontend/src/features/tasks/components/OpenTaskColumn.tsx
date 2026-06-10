import type { Task } from "../../../types/Task";
import { cn } from "../../../utils/tailwind";
import CreateTaskCard from "./CreateTaskCard";
import TaskCard from "./TaskCard/TaskCard";

interface OpenTaskColumnProps {
  openTasks: Task[];
  isCreating: boolean;
  onOpenCreate: () => void;
  onCloseCreate: () => void;
  onCreateTask: (title: string) => void;
  onTaskUpdate: (task: Task) => void;
}

export default function OpenTaskColumn({
  onCreateTask,
  onTaskUpdate,
  onOpenCreate,
  onCloseCreate,
  openTasks,
  isCreating,
}: OpenTaskColumnProps) {
  return (
    <div>
      <div className="relative flex items-center justify-center">
        <h2 className="mb-4 px-4 text-lg font-semibold">Open</h2>
        <button
          className={cn(
            "absolute right-0 mr-10 mb-4 cursor-pointer text-lg font-bold",
          )}
          onClick={isCreating ? onCloseCreate : onOpenCreate}
        >
          {!isCreating ? "new" : "hide"}
        </button>
      </div>

      <div className="flex flex-col gap-3">
        {isCreating && (
          <CreateTaskCard onCancel={onCloseCreate} onCreate={onCreateTask} />
        )}
        {openTasks.map((task) => (
          <TaskCard key={task.id} task={task} onUpdated={onTaskUpdate} />
        ))}
      </div>
    </div>
  );
}
