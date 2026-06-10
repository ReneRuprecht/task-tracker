import { useState, useEffect } from "react";
import type { Task } from "../../../types/Task";
import { getTasksByProjectsID } from "../../projects/api/GetTasksByProjectID";
import { createTask } from "../api/CreateTask";
import { updateTask } from "../api/UpdateTask";

export function useTasks(projectID: string | null) {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!projectID) {
      return;
    }

    const fetchTasks = async () => {
      try {
        setLoading(true);

        const data = await getTasksByProjectsID(projectID);

        setTasks(data.tasks);
        setError(null);
      } catch (err) {
        setError(err instanceof Error ? err.message : "Unknown error");

        setTasks([]);
      } finally {
        setLoading(false);
      }
    };

    fetchTasks();
  }, [projectID]);

  const createNewTask = async (title: string) => {
    if (!projectID) return;

    const newTask = await createTask(title, projectID);

    setTasks((prev) => [newTask, ...prev]);
  };

  const updateExistingTask = async (task: Task) => {
    await updateTask(task);

    setTasks((prev) => prev.map((t) => (t.id === task.id ? task : t)));
  };

  return {
    tasks,
    loading,
    error,
    createNewTask,
    updateExistingTask,
  };
}
