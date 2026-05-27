export interface Task {
  id: string;
  name: string;
  status: TaskStatus;
}

export interface Tasks {
  tasks: Task[];
}

export type TaskStatus = "OPEN" | "CLOSED";
