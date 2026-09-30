import api from "../api/axiosConfig";

export const getDashBoardSummary=async()=>{
    const response=await api.get("/dashboard/summary");
    return response.data;
}

export const getTaskSummary=async()=>{
    const response=await api.get("/dashboard/task-summary");
    return response.data;
}

export const getDashBoardProjects=async()=>{
    const response=await api.get("/dashboard/projects");
    return response.data;
}

export const getRecentActivities=async()=>{
    const response=await api.get("/dashboard/recent-activities");
    return response.data;
}

export const getUpcomingDeadLines=async()=>{
    const response=await api.get("/dashboard/upcoming-deadlines");
    return response.data;
}

export const searchTask=(keyword)=>{
   return api.get("/tasks/search",{
    params:{
        keyword
    }
});
    
};

export const getAllMembers=async()=>{
    const response=await api.get("/all_Members");
    return response.data;
}

export const getTasks = async (filters) => {
    const params = {};

    if (filters.keyword) {
        params.keyword = filters.keyword;
    }

    if (filters.status) {
        params.status = filters.status;
    }

    if (filters.priority) {
        params.priority = filters.priority;
    }

    if (filters.projectId) {
        params.projectId = filters.projectId;
    }

    if (filters.memberId) {
        params.memberId = filters.memberId;
    }

    if (filters.dueDate) {
        params.dueDate = filters.dueDate;
    }

    if(filters.taskTab) {
        params.taskTab=filters.taskTab;
    }

    const response = await api.get("/tasks/search", {
        params,
    });

    return response.data;
}

export const createTask = async (projectId, taskData) => {
  const response = await api.post(
    `/projects/${projectId}/tasks`,
    taskData
  );

  return response.data;
}

export const assignMemberToTask=async (taskId,userId)=>{
    const response =await api.post(
     `/tasks/${taskId}/members/${userId}`
    )

    return response.data;
}

export const getTaskById=async(taskId)=>{
    const response=await api.get(
     `/tasks/${taskId}`
    );
    return response.data;
}

export const updateTask=async(taskId,formData)=>{
    const response=await api.put(
        `/tasks/${taskId}`,
        formData
    );
    return response.data;
}

export const deleteTask=async(taskId)=>{
    await api.delete(
    `/tasks/${taskId}`
    );
}