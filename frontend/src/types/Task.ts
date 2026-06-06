export interface Task {
  id: string;
  title: string;
  status: TaskStatus;
}

export interface Tasks {
  tasks: Task[];
}

export type TaskStatus = "OPEN" | "CLOSED";
