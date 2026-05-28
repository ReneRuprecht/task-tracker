import { useState } from "react";
import type { Task, TaskStatus } from "../types/Task";
import TaskCard from "./TaskCard";
import { cn } from "../utils/tailwind";

interface TaskColumnProps {
  onTaskUpdate: (taskID: string, status: TaskStatus) => void;
  tasks: Task[];
  createTask: (title: string) => void;
}

export default function TaskColumn({
  onTaskUpdate,
  tasks,
  createTask,
}: TaskColumnProps) {
  const handleTaskUpdate = (taskID: string, status: TaskStatus) => {
    onTaskUpdate(taskID, status);
  };
  const toggleNewCard = () => {
    setIsCreating(!isCreating);
  };
  const handleCreateTask = (title: string) => {
    createTask(title);
    setIsCreating(false);
  };

  const [isCreating, setIsCreating] = useState(false);
  return (
    <>
      <div className="grid grid-cols-2 gap-6">
        <div>
          <div className="relative flex items-center justify-center">
            <h2 className="mb-4 px-4 text-lg font-semibold">Open</h2>
            <button
              className={cn("absolute right-0 mr-10 mb-4 text-lg font-bold")}
              onClick={toggleNewCard}
            >
              {!isCreating ? "new" : "hide"}
            </button>
          </div>

          <div className="flex flex-col gap-3">
            {isCreating && (
              <TaskCard
                mode="create"
                onCancel={toggleNewCard}
                onCreate={handleCreateTask}
              />
            )}
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
