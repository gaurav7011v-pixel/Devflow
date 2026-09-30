
import { useEffect, useState, useCallback } from "react";
import { getTasks } from "../services/DashBoardServices";

const useTasks = (filters) => {

    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchTasks = useCallback(async () => {

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

    }, [
        filters.keyword,
        filters.status,
        filters.priority,
        filters.projectId,
        filters.memberId,
        filters.dueDate,
        filters.taskTab,
    ]);

    useEffect(() => {
        fetchTasks();
    }, [fetchTasks]);

    return {
        tasks,
        loading,
        error,
        refetch: fetchTasks,
    };
};

export default useTasks;

