import React, { useEffect, useState } from 'react'
import { getAllMembers } from '../services/DashBoardServices';

const useAllMembers = () => {
    const[members,setMembers]=useState(null);
    const[loading,setLoading]=useState(false);
    const[error,setError]=useState(null);

    useEffect(()=>{
        const fetchData=async()=>{
            try{
                setLoading(true);
                const data=await getAllMembers();
                setMembers(data);
            }catch(error){
                setError(error);
            }finally{
                setLoading(false)
            }
            
        }

        fetchData();
    },[])
  return {
    members,
    loading,
    error
  }
}

export default useAllMembers
