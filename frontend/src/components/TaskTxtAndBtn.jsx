import React, { useState } from "react";
import CreateTask from "./CreateTask";
import useDashBoardProjects from "../hooks/useDashBoardProjects";
import useAllMembers from "../hooks/useAllMembers";

const TaskTxtAndBtn = () => {
  const [showCreateTask, setShowCreateTask] = useState(false);
  
  return (
    <div className="flex justify-between items-center p-4">

      <div>
        <h1 className="text-2xl font-bold">
          Tasks
        </h1>

        <p className="text-sm font-semibold text-gray-600">
          Manage and track all your project tasks
        </p>
      </div>

      <button
        onClick={() => setShowCreateTask(true)}
        className="bg-blue-500 font-semibold text-white rounded-sm px-3 h-7 text-center text-sm cursor-pointer hover:bg-blue-600"
      >
        + Create Task
      </button>

      {showCreateTask && (
        <CreateTask
          onClose={() => setShowCreateTask(false)}
        />
      )}

    </div>
  );
};

export default TaskTxtAndBtn;