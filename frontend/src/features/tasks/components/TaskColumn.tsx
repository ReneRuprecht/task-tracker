import { useState } from "react";
import type { Task, TaskStatus } from "../../../types/Task";
import ClosedTaskColumn from "./ClosedTaskCollumn";
import OpenTaskColumn from "./OpenTaskColumn";
import { getClosedTasks, getOpenTasks } from "../utils/taskFilters";

interface TaskColumnProps {
  tasks: Task[];
  onTaskUpdate: (taskID: string, title: string, status: TaskStatus) => void;
  onCreateTask: (title: string) => void;
}

export default function TaskColumn({
  tasks,
  onTaskUpdate,
  onCreateTask,
}: TaskColumnProps) {
  const [isCreating, setIsCreating] = useState(false);

  const handleCreateTask = (title: string) => {
    onCreateTask(title);
    setIsCreating(false);
  };

  const openTasks = getOpenTasks(tasks);
  const closedTasks = getClosedTasks(tasks);

  const openCreate = () => setIsCreating(true);
  const closeCreate = () => setIsCreating(false);

  return (
    <>
      <div className="grid grid-cols-2 gap-6">
        <OpenTaskColumn
          openTasks={openTasks}
          isCreating={isCreating}
          onTaskUpdate={onTaskUpdate}
          onCreateTask={handleCreateTask}
          onOpenCreate={openCreate}
          onCloseCreate={closeCreate}
        />
        <ClosedTaskColumn
          closedTasks={closedTasks}
          onTaskUpdate={onTaskUpdate}
        />
      </div>
    </>
  );
}
