import type { Projects } from "../../../types/Project";

export async function getProjects(): Promise<Projects> {
  const base_api_url = import.meta.env.VITE_API_BASE_URL;
  const response = await fetch(base_api_url + "/api/v1/projects");
  if (!response.ok) throw new Error("Failed to fetch projects");
  return response.json();
}
