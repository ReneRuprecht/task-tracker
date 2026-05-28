import { useEffect, useState } from "react";
import type { Task, Tasks, TaskStatus } from "../types/Task";
import { getTasks } from "../api/GetTasks";
import TaskColumn from "../components/TaskColumn";
import { ProgressBar } from "../components/Progressbar";
import { createTask } from "../api/CreateTask";
import { updateTaskStatus } from "../api/UpdateTaskStatus";

export default function TaskBoardPage() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const handleTaskUpdated = async (taskId: string, status: TaskStatus) => {
    await updateTaskStatus(taskId, status);

    setTasks((prev) =>
      prev.map((task) => (task.id === taskId ? { ...task, status } : task)),
    );
  };

  const handleCreateTask = async (title: string) => {
    const newTask = await createTask(title);
    setTasks([newTask, ...tasks]);
    console.log("handle create ", title);
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
      } catch (err: any) {
        setError(err.message);
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
        createTask={handleCreateTask}
      />
    </>
  );
}
