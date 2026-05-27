export interface Task {
  id: string;
  name: string;
  status: "OPEN" | "CLOSED";
}

export interface Tasks {
  tasks: Task[];
}

