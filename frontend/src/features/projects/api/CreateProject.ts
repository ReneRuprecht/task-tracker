import type { Project } from "../../../types/Project";

export async function createProject(name: string): Promise<Project> {
  const base_api_url = import.meta.env.VITE_API_BASE_URL;
  const response = await fetch(base_api_url + `/api/v1/projects`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ name }),
  });
  if (!response.ok) throw new Error("Failed to create project");
  return response.json();
}
