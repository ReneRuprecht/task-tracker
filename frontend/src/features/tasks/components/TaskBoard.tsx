import { useState } from "react";
import type { Task } from "../../../types/Task";
import ClosedTaskColumn from "./ClosedTaskCollumn";
import OpenTaskColumn from "./OpenTaskColumn";
import { getClosedTasks, getOpenTasks } from "../utils/taskFilters";

interface TaskBoardProps {
  tasks: Task[];
  onTaskUpdate: (task: Task) => void;
  onCreateTask: (title: string) => void;
}

export default function TaskBoard({
  tasks,
  onTaskUpdate,
  onCreateTask,
}: TaskBoardProps) {
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
