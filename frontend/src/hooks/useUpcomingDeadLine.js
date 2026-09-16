import React from 'react'
import { useState,useEffect } from 'react';
import { getUpcomingDeadLines } from '../services/DashBoardServices';

const useUpcomingDeadLine = () => {
   const[upcomingDeadlines,setUpcomingDeadlines]=useState(null);
      const[loading,setLoading]=useState(true);
      const[error,setError]=useState(null);
  
      useEffect(()=>{
          const fetchData=async()=>{
              try{
                  const data=await getUpcomingDeadLines();
                  setUpcomingDeadlines(data);
              }catch(error){
                  setError(error);
              }finally{
                  setLoading(false);
              }
          }
  
          fetchData();
      },[])
    return {
          upcomingDeadlines,
          loading,
          error
    }
}

export default useUpcomingDeadLine;
