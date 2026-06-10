import TaskBoard from "../features/tasks/components/TaskBoard";
import { ProgressBar } from "../components/ui/Progressbar";
import ProjectDropdown from "../features/projects/components/ProjectDowndown";
import { useProjects } from "../features/projects/hooks/projectHook";
import { useTasks } from "../features/tasks/hooks/taskHook";
import { getClosedTasks } from "../features/tasks/utils/taskFilters";

export default function TaskBoardPage() {
  const {
    projects,
    selectedProject,
    selectProject,
    createNewProject,
    selectedProjectId,
    loading: projectsLoading,
    error: projectsError,
  } = useProjects();

  const {
    tasks,
    loading: tasksLoading,
    error: tasksError,
    createNewTask,
    updateExistingTask,
  } = useTasks(selectedProjectId);

  const error = projectsError || tasksError;

  const closedTasks = getClosedTasks(tasks);

  const progress =
    tasks.length === 0
      ? 0
      : Math.round((closedTasks.length / tasks.length) * 100);

  if (projectsLoading || tasksLoading) return <h1>Lädt...</h1>;

  return (
    <>
      {error && (
        <div className="mb-2 rounded bg-red-100 px-4 py-2 text-red-700">
          Fehler: {error}
        </div>
      )}

      <ProgressBar value={progress} />

      <ProjectDropdown
        selected={selectedProject}
        onSelect={selectProject}
        projects={projects}
        onProjectCreate={createNewProject}
      />

      <TaskBoard
        onTaskUpdate={updateExistingTask}
        tasks={tasks}
        onCreateTask={createNewTask}
      />
    </>
  );
}
