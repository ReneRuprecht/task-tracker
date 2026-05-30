import type { Task, TaskStatus } from "../../../types/Task";
import TaskCard from "./TaskCard";

interface ClosedTaskColumnProps {
  onTaskUpdate: (taskID: string, status: TaskStatus) => void;
  closedTasks: Task[];
}

export default function ClosedTaskColumn({
  onTaskUpdate,
  closedTasks,
}: ClosedTaskColumnProps) {
  return (
    <div>
      <h2 className="mb-4 text-lg font-semibold">Closed</h2>

      <div className="flex flex-col gap-3">
        {closedTasks.map((task) => (
          <TaskCard key={task.id} task={task} onUpdated={onTaskUpdate} />
        ))}
      </div>
    </div>
  );
}
