import React, { useEffect, useState } from 'react'
import { searchTask } from '../services/DashBoardServices';

const useSearchTask = () => {
    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [keyword, setKeyword] = useState("");

    useEffect(() => {
        const fectchTask = async (keyword) => {
            try {
                setLoading(true);

                const response = await searchTask(keyword);

                setTasks(response.data);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        }



        fectchTask(keyword);
    }, [keyword])

    return {
        tasks,
        loading,
        error,
        keyword,
        setKeyword
    }
}

export default useSearchTask
