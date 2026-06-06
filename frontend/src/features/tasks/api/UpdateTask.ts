import type { Task } from "../../../types/Task";

export async function updateTask(task: Task): Promise<void> {
  const base_api_url = import.meta.env.VITE_API_BASE_URL;
  const response = await fetch(base_api_url + `/api/v1/tasks/${task.id}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ title: task.title, status: task.status }),
  });
  if (!response.ok) throw new Error("Failed to fetch tasks");
}
