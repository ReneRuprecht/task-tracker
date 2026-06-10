import type { Tasks } from "../../../types/Task";

export async function getTasksByProjectsID(
  projectID: string,
): Promise<Tasks> {
  const base_api_url = import.meta.env.VITE_API_BASE_URL;
  const response = await fetch(base_api_url + `/api/v1/projects/${projectID}`);
  if (!response.ok) throw new Error("Failed to fetch projects");
  return response.json();
}
