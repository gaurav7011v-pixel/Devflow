import { useEffect, useState } from "react";
import { getTasks } from "../services/DashBoardServices";

const useTasks = (filters) => {

    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {

        const fetchTasks = async () => {

            try {
                setLoading(true);
                setError(null);

                const data = await getTasks(filters);

                setTasks(data);

            } catch (error) {

                console.error("Failed to load tasks:", error);

                setError(error);

            } finally {

                setLoading(false);

            }
        };

        fetchTasks();

    }, [
        filters.keyword,
        filters.status,
        filters.priority,
        filters.projectId,
        filters.memberId,
        filters.dueDate,
    ]);

    return {
        tasks,
        loading,
        error,
    };
};

export default useTasks;