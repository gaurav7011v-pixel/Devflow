import React from 'react'
import { useState,useEffect } from 'react'
import { getDashBoardSummary } from '../services/DashBoardServices'

const useDashBoardSummary = () => {
        const [summary,setSummary]=useState(null);
        const [loading,setLoading]=useState(true);
        const [error,setError]=useState(null);

        useEffect(()=>{
            const fetchSummary=async()=>{
                try{
                    const data=await getDashBoardSummary();
                    setSummary(data);
                }catch(error){
                    setError(error);
                }finally{
                    setLoading(false);
                }
            };

            fetchSummary();
        },[]);
  return {
    summary,
    loading,
    error
  }
}

export default useDashBoardSummary
