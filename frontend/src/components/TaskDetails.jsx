import React, { useEffect ,useState} from "react";
import { getTaskById } from "../services/DashBoardServices";

const TaskDetails = ({ onClose ,taskId}) => {
  const[task,setTask]=useState(null);

  useEffect(()=>{
    const fetchData=async()=>{

      try{
        const response=await getTaskById(taskId);
        setTask(response);
    }catch(error){
      alert(error);
    }
      
    }

    fetchData();
  },[taskId])

 return (
  <div className="fixed inset-0 z-50 bg-black/40 backdrop-blur-sm flex items-center justify-center p-4">

    <div className="bg-white w-full max-w-xl rounded-2xl shadow-2xl overflow-hidden">

      {/* Header */}
      <div className="flex items-center justify-between px-6 py-5 border-b border-gray-100">

        <div>
          <p className="text-xs font-medium text-gray-400 uppercase tracking-wide">
            Task Details
          </p>

          <h2 className="text-xl font-semibold text-gray-900 mt-1">
            {task?.title || "Loading..."}
          </h2>
        </div>

        <button
          onClick={onClose}
          className="w-9 h-9 flex items-center justify-center rounded-lg
                     text-gray-400 hover:text-gray-700 hover:bg-gray-100
                     transition cursor-pointer"
        >
          ✕
        </button>

      </div>


      {/* Body */}
      <div className="px-6 py-6">

        {/* Description */}
        <div className="mb-6">

          <h3 className="text-sm font-semibold text-gray-800 mb-2">
            Description
          </h3>

          <div className="bg-gray-50 rounded-xl p-4 text-sm text-gray-600 leading-6">
            {task?.description || "No description provided."}
          </div>

        </div>


        {/* Status + Priority */}
        <div className="grid grid-cols-2 gap-4 mb-6">

          {/* Status */}
          <div>
            <p className="text-xs font-medium text-gray-400 uppercase tracking-wide mb-2">
              Status
            </p>

            <div className="inline-flex items-center px-3 py-1.5 rounded-lg
                            bg-blue-50 text-blue-700 text-sm font-medium">
              {task?.status || "—"}
            </div>
          </div>


          {/* Priority */}
          <div>
            <p className="text-xs font-medium text-gray-400 uppercase tracking-wide mb-2">
              Priority
            </p>

            <div className="inline-flex items-center px-3 py-1.5 rounded-lg
                            bg-orange-50 text-orange-700 text-sm font-medium">
              {task?.priority || "—"}
            </div>
          </div>

        </div>


        {/* Task Information */}
        <div>

          <h3 className="text-sm font-semibold text-gray-800 mb-3">
            Task Information
          </h3>

          <div className="border border-gray-100 rounded-xl overflow-hidden">

            {/* Project */}
            <div className="flex items-center justify-between px-4 py-3
                            border-b border-gray-100">

              <span className="text-sm text-gray-500">
                Project
              </span>

              <span className="text-sm font-medium text-gray-800">
                {task?.projectName || "—"}
              </span>

            </div>


            {/* Due Date */}
            <div className="flex items-center justify-between px-4 py-3
                            border-b border-gray-100">

              <span className="text-sm text-gray-500">
                Due Date
              </span>

              <span className="text-sm font-medium text-gray-800">
                {task?.dueDate || "No due date"}
              </span>

            </div>


            {/* Assignee */}
            <div className="flex items-center justify-between px-4 py-3">

              <span className="text-sm text-gray-500">
                Assigned To
              </span>

              <div className="flex items-center gap-2">

                <div className="w-7 h-7 rounded-full bg-gray-200
                                flex items-center justify-center
                                text-xs font-semibold text-gray-600">
                  {task?.assigneeName
                    ? task.assigneeName.charAt(0).toUpperCase()
                    : "?"}
                </div>

                <span className="text-sm font-medium text-gray-800">
                  {task?.assigneeName || "Unassigned"}
                </span>

              </div>

            </div>

          </div>

        </div>

      </div>


      {/* Footer */}
      <div className="flex justify-end px-6 py-4 bg-gray-50 border-t border-gray-100">

        <button
          onClick={onClose}
          className="px-5 py-2.5 rounded-lg bg-gray-900 text-white
                     text-sm font-medium hover:bg-gray-800
                     transition cursor-pointer"
        >
          Close
        </button>

      </div>

    </div>

  </div>
);
};

export default TaskDetails;