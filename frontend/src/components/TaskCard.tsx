import type { Task } from "../types/Task";

interface TaskItemProps {
    task: Task;
}

export default function TaskCard({ task }: TaskItemProps) {
    return (
        <div className="flex items-center justify-between rounded-lg border border-gray-200 bg-white mx-10 px-4 py-3 transition hover:bg-gray-200">
            <div className="flex items-center gap-3">
                <div
                    className={`h-2.5 w-2.5 rounded-full ${task.status === "CLOSED"
                            ? "bg-green-500"
                            : task.status === "OPEN"
                                ? "bg-yellow-500"
                                : "bg-gray-400"
                        }`}
                />

                <span className="font-medium text-gray-900">
                    {task.name}
                </span>
            </div>

            <span className="text-xs font-medium uppercase tracking-wide text-gray-500">
                {task.status}
            </span>
        </div>
    );
}
