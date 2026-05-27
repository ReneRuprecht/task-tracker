import { useEffect, useState } from "react";
import type { Task, Tasks } from "../types/Task";
import { getTasks } from "../api/GetTasks";
import TaskCard from "./TaskCard";

export default function TaskColumn() {
    const [tasks, setTasks] = useState<Task[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {

        const fetchTasks = async () => {
            try {
                const data: Tasks = await getTasks()

                const extractedTasks: Task[] = [...data.tasks];

                setTasks(extractedTasks)
            }
            catch (err: any) {
                console.error(err)
                setError(err.message)
                setTasks([])
            }
            finally {
                setLoading(false)
            }
        }

        fetchTasks()

    }, []);

    if (loading) return <h1>Lädt aktuelle Tasks</h1>;
    if (error) return <h1>Fehler: {error}</h1>;
    if (tasks === undefined || tasks.length === 0) return <h1>Keine Tasks</h1>;

    return (
        <div className="grid grid-cols-2 gap-6">

            <div>
                <h2 className="mb-4 text-lg font-semibold">
                    Open
                </h2>

                <div className="flex flex-col gap-3">
                    {tasks.filter(task => task.status == "OPEN").map(task => (
                        <TaskCard key={task.id} task={task} />
                    ))}
                </div>
            </div>

            <div>
                <h2 className="mb-4 text-lg font-semibold">
                    Closed
                </h2>

                <div className="flex flex-col gap-3">
                    {tasks.filter(task => task.status === "CLOSED").map(task => (
                        <TaskCard key={task.id} task={task} />
                    ))}
                </div>
            </div>

        </div>
    );
}
