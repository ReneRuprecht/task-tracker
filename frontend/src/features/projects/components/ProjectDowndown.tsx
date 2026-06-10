import { useState } from "react";
import type { Project } from "../../../types/Project";

interface ProjectDropdownProps {
  onSelect: (id: string) => void;
  onProjectCreate: (projectName: string) => void;
  selected: Project | undefined;
  projects: Project[];
}

export default function ProjectDropdown({
  selected,
  projects,
  onSelect,
  onProjectCreate,
}: ProjectDropdownProps) {
  const [open, setOpen] = useState(false);
  const [projectName, setProjectName] = useState("");

  function handleSelect(projectID: string) {
    onSelect(projectID);
    setOpen(false);
  }

  function handleSubmit() {
    onProjectCreate(projectName);
  }

  return (
    <div className="mx-auto flex w-64 flex-col justify-center">
      <h1>Projects</h1>
      <button
        onClick={() => setOpen(!open)}
        className="flex items-center justify-between rounded-lg border bg-white px-4 py-2 shadow-sm transition hover:bg-gray-50"
      >
        <span className="text-gray-700">
          {selected ? selected.name : "Select Project"}
        </span>

        <span className="text-gray-400">▼</span>
      </button>

      {open && (
        <div className="z-10 mt-2 overflow-hidden rounded-lg border bg-white shadow-lg">
          {projects
            .filter((entry) => entry.id != selected?.id)
            .map((project: Project) => (
              <button
                key={project.id}
                onClick={() => handleSelect(project.id)}
                className="w-full px-4 py-2 text-left transition hover:bg-blue-50"
              >
                <span className="text-gray-700">{project.name}</span>
              </button>
            ))}
          <input
            className="z-10 mt-2 mb-2 overflow-hidden rounded-lg border-0 border-none bg-white text-black outline-none focus:ring-0"
            value={projectName}
            onChange={(event) => setProjectName(event.target.value)}
            onKeyDown={(event) => {
              if (event.key === "Enter") {
                event.preventDefault();
                handleSubmit();
              }
            }}
            placeholder="new Project"
          />
        </div>
      )}
      <div className="h-12" />
    </div>
  );
}
