import React, { useEffect, useState } from 'react'
import { getDashBoardProjects } from '../services/DashBoardServices';

const useDashBoardProjects = () => {
    const[projects,setProjects]=useState(null);
    const[loading,setLoading]=useState(true);
    const[error,setError]=useState(null);

    useEffect(()=>{
      const fetchData=async()=>{
        try{
         const data=await getDashBoardProjects();
         setProjects(data);
        }catch(error){
          setError(error);
        }finally{
          setLoading(false);
        }
      };
     fetchData();
    },[]);
  return {
    projects,
    loading,
    error
  }
}

export default useDashBoardProjects
