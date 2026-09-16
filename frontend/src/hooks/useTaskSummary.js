import React, { useEffect, useState } from 'react'
import { getTaskSummary } from '../services/DashBoardServices';

const useTaskSummary = () => {
    const[taskSummary,setTaskSummary]=useState(null);
    const[loading,setLoading]=useState(true);
    const[error,setError]=useState(null);

    useEffect(()=>{
        const fetchData=async()=>{
            try{
                const data=await getTaskSummary();
                setTaskSummary(data);
            }catch(error){
                setError(error);
            }finally{
                setLoading(false);
            }
        }

        fetchData();
    },[])
  return {
        taskSummary,
        loading,
        error
  }
}

export default useTaskSummary
