export async function updateTaskStatus(
  taskID: string,
  status: string,
): Promise<void> {
  const base_api_url = import.meta.env.VITE_API_BASE_URL;
  const response = await fetch(base_api_url + `/api/v1/tasks/${taskID}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ status }),
  });
  if (!response.ok) throw new Error("Failed to fetch tasks");
}
