import { useEffect, useState } from "react";
import type { Task, Tasks, TaskStatus } from "../types/Task";
import { getTasks } from "../features/tasks/api/GetTasks";
import TaskColumn from "../features/tasks/components/TaskColumn";
import { ProgressBar } from "../components/ui/Progressbar";
import { createTask } from "../features/tasks/api/CreateTask";
import { updateTask } from "../features/tasks/api/UpdateTask";

export default function TaskBoardPage() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const handleTaskUpdated = async (
    taskId: string,
    title: string,
    status: TaskStatus,
  ) => {
    const updatedTask: Task = { id: taskId, name: title, status: status };

    await updateTask(updatedTask);

    setTasks((prev) =>
      prev.map((task) => (task.id === taskId ? updatedTask : task)),
    );
  };

  const handleCreateTask = async (title: string) => {
    const newTask = await createTask(title);
    setTasks([newTask, ...tasks]);
  };

  const closedTask = tasks.filter((task) => task.status === "CLOSED");
  const progress =
    tasks.length === 0
      ? 0
      : Math.round((closedTask.length / tasks.length) * 100);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const data: Tasks = await getTasks();

        const extractedTasks: Task[] = [...data.tasks];

        setTasks(extractedTasks);
      } catch (err: unknown) {
        if (err instanceof Error) {
          setError(err.message);
        } else {
          setError("Unknown error");
        }
        setTasks([]);
      } finally {
        setLoading(false);
      }
    };

    fetchTasks();
  }, []);

  if (loading) return <h1>Lädt aktuelle Tasks</h1>;
  if (error) return <h1>Fehler: {error}</h1>;

  return (
    <>
      <ProgressBar value={progress} />
      <TaskColumn
        onTaskUpdate={handleTaskUpdated}
        tasks={tasks}
        onCreateTask={handleCreateTask}
      />
    </>
  );
}
