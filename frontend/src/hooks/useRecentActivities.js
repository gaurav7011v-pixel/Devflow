import React from 'react'
import { useState,useEffect } from 'react'
import { getRecentActivities } from '../services/DashBoardServices'

const useRecentActivities = () => {
    const[recentActivities,setRecentActivities]=useState(null);
    const[loading,setLoading]=useState(true);
    const[error,setError]=useState(null);

    
        useEffect(()=>{
          const fetchData=async()=>{
            try{
             const data=await getRecentActivities();
             setRecentActivities(data);
            }catch(error){
              setError(error);
            }finally{
              setLoading(false);
            }
          };
         fetchData();
        },[]);
  return {
    recentActivities,
    loading,
    error
  }
}

export default useRecentActivities
