import type { Task, TaskStatus } from "../types/Task";
import TaskCard from "./TaskCard";

interface TaskColumnProps {
  onTaskUpdate: (taskID: string, status: TaskStatus) => void;
  tasks: Task[];
}

export default function TaskColumn({ onTaskUpdate, tasks }: TaskColumnProps) {
  const handleTaskUpdate = (taskID: string, status: TaskStatus) => {
    onTaskUpdate(taskID, status);
  };
  return (
    <>
      <div className="grid grid-cols-2 gap-6">
        <div>
          <h2 className="mb-4 text-lg font-semibold">Open</h2>

          <div className="flex flex-col gap-3">
            {tasks
              .filter((task) => task.status == "OPEN")
              .map((task) => (
                <TaskCard key={task.id} task={task} onUpdated={onTaskUpdate} />
              ))}
          </div>
        </div>

        <div>
          <h2 className="mb-4 text-lg font-semibold">Closed</h2>

          <div className="flex flex-col gap-3">
            {tasks
              .filter((task) => task.status === "CLOSED")
              .map((task) => (
                <TaskCard
                  key={task.id}
                  task={task}
                  onUpdated={handleTaskUpdate}
                />
              ))}
          </div>
        </div>
      </div>
    </>
  );
}
