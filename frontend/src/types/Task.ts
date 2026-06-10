export interface Task {
  id: string;
  title: string;
  status: TaskStatus;
  projectID: string
}

export interface Tasks {
  tasks: Task[];
}

export type TaskStatus = "OPEN" | "CLOSED";
