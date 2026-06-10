import { useEffect, useMemo, useState } from "react";
import type { Project } from "../../../types/Project";
import { getProjects } from "../api/GetProjects";
import { createProject } from "../api/CreateProject";

export function useProjects() {
  const [projects, setProjects] = useState<Project[]>([]);
  const [selectedProjectId, setSelectedProjectId] = useState<string | null>(
    () => {
      return localStorage.getItem("selectedProjectId");
    },
  );

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const data = await getProjects();
        setProjects(data.projects);
        setError(null);
      } catch (err) {
        setError(err instanceof Error ? err.message : "Unknown error");
      } finally {
        setLoading(false);
      }
    };

    fetchProjects();
  }, []);

  useEffect(() => {
    if (selectedProjectId) {
      localStorage.setItem("selectedProjectId", selectedProjectId);
    } else {
      localStorage.removeItem("selectedProjectId");
    }
  }, [selectedProjectId]);

  const selectedProject = useMemo(() => {
    return projects.find((p) => p.id === selectedProjectId);
  }, [projects, selectedProjectId]);

  const selectProject = (projectId: string) => {
    setSelectedProjectId(projectId);
  };

  const createNewProject = async (name: string) => {
    if (!name.trim()) return;

    const newProject = await createProject(name);

    setProjects((prev) => [newProject, ...prev]);

    setSelectedProjectId(newProject.id);
  };

  return {
    projects,
    selectedProject,
    selectedProjectId,
    selectProject,
    createNewProject,
    loading,
    error,
  };
}
