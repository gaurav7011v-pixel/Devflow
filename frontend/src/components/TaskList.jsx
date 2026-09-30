import React from "react";
import useTasks from "../hooks/useTasks";
import { useState } from "react";
import TaskDetails from "./TaskDetails";
import UpdateTask from "./UpdateTask";
import DeleteTask from "./DeleteTask";
import { deleteTask,updateTask } from "../services/DashBoardServices";

const TaskList = ({ filters = {} }) => {
  const [taskTab, setTaskTab] = useState("ALL")
  const [openMenuId, setOpenMenuId] = useState(null);


  const [activeModal, setActiveModal] = useState(null);
  const [selectedTaskId, setSelectedTaskId] = useState(null);


  const finalFilters = {
    ...filters,
    taskTab
  };
  const { tasks = [], loading, error, refetch } = useTasks(finalFilters);

  const handleOpenModal = (modal, taskId) => {
    setSelectedTaskId(taskId);
    setActiveModal(modal);
  }

  const handleCloseModal = () => {
    setSelectedTaskId(null);
    setActiveModal(null);
  }

  if (loading) {
    return (
      <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-8 mt-5">
        <div className="text-center text-gray-500">
          Loading tasks...
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-red-50 border border-red-100 text-red-600 rounded-2xl p-6 mt-5 text-center">
        Failed to load tasks.
      </div>
    );
  }

  return (
    <div className="mt-5 bg-white rounded-2xl shadow-sm border border-gray-100 overflow-hidden mx-3 mr-12">

      {/* Top tabs */}
      <div className="flex items-center justify-between px-5 pt-4 border-b border-gray-100">

        <div className="flex items-center gap-8 text-sm font-semibold">

          <button
            onClick={() => setTaskTab("ALL")}
            className={`pb-3 ${taskTab === "ALL"
              ? "text-blue-600 border-b-2 border-blue-600"
              : "text-gray-500"
              }`}
          >
            All Tasks
          </button>

          <button
            onClick={() => setTaskTab("CREATED_BY_ME")}
            className={`pb-3 ${taskTab === "CREATED_BY_ME"
              ? "text-blue-600 border-b-2 border-blue-600"
              : "text-gray-500"
              }`}
          >
            Created by Me
          </button>

          <button
            onClick={() => setTaskTab("MY_TASKS")}
            className={`pb-3 ${taskTab === "MY_TASKS"
              ? "text-blue-600 border-b-2 border-blue-600"
              : "text-gray-500"
              }`}
          >
            My Tasks
          </button>

        </div>

        {/* Sort */}
        <div className="flex items-center gap-3">

          <select
            className="border border-gray-200 rounded-lg px-3 py-2 text-sm bg-gray-50 outline-none"
            defaultValue="dueDate"
          >
            <option value="dueDate">
              Sort by: Due Date
            </option>

            <option value="priority">
              Priority
            </option>

            <option value="status">
              Status
            </option>

            <option value="createdAt">
              Created Date
            </option>
          </select>

          <button className="px-3 py-2 rounded-lg bg-blue-50 text-blue-600">
            ☷
          </button>

          <button className="px-3 py-2 rounded-lg border border-gray-200 text-gray-500">
            ▦
          </button>

        </div>
      </div>


      {/* Table */}
      <div className="overflow-x-auto">

        <table className="w-full min-w-[1100px]">

          <thead className="bg-gray-50">

            <tr className="text-left text-xs font-semibold text-gray-600">

              <th className="px-5 py-4 w-10">
                <input type="checkbox" />
              </th>

              <th className="px-4 py-4">
                Task
              </th>

              <th className="px-4 py-4">
                Project
              </th>

              <th className="px-4 py-4">
                Priority
              </th>

              <th className="px-4 py-4">
                Status
              </th>

              <th className="px-4 py-4">
                Assignee
              </th>

              <th className="px-4 py-4">
                Due Date
              </th>

              <th className="px-4 py-4">
                Progress
              </th>

              <th className="px-4 py-4">
                Actions
              </th>

            </tr>

          </thead>


          <tbody>

            {tasks.length === 0 ? (

              <tr>
                <td
                  colSpan="9"
                  className="text-center py-12 text-gray-500"
                >
                  No tasks found.
                </td>
              </tr>

            ) : (

              tasks.map((task) => (

                <TaskRow
                  key={task.id}
                  task={task}
                  openMenuId={openMenuId}
                  setOpenMenuId={setOpenMenuId}
                  onOpenModal={handleOpenModal}
                  refetch={refetch}
                />

              ))

            )}

          </tbody>

        </table>

      </div>

      {activeModal === "view" && selectedTaskId && (
        <TaskDetails
          taskId={selectedTaskId}
          onClose={handleCloseModal}
        />
      )}

      {activeModal === "edit" && selectedTaskId && (
        <UpdateTask
          taskId={selectedTaskId}
          onClose={handleCloseModal}
        />
      )}


      {activeModal === "delete" && selectedTaskId && (
        <DeleteTask
          taskId={selectedTaskId}
          onClose={handleCloseModal}
        />
      )}

      {/* Bottom */}
      <div className="flex justify-between items-center px-5 py-4 border-t border-gray-100">

        <p className="text-sm text-gray-500">
          Showing 1–{tasks.length} of {tasks.length} tasks
        </p>

        <div className="flex items-center gap-1">

          <button className="px-3 py-2 border border-gray-200 rounded-lg text-gray-500">
            ‹
          </button>

          <button className="px-3 py-2 rounded-lg bg-blue-600 text-white">
            1
          </button>

          <button className="px-3 py-2 border border-gray-200 rounded-lg">
            2
          </button>

          <button className="px-3 py-2 border border-gray-200 rounded-lg">
            3
          </button>

          <button className="px-3 py-2 border border-gray-200 rounded-lg">
            4
          </button>

          <button className="px-3 py-2 border border-gray-200 rounded-lg">
            5
          </button>

          <button className="px-3 py-2 border border-gray-200 rounded-lg text-gray-500">
            ›
          </button>

        </div>

      </div>

    </div>
  );
};




const TaskRow = ({ task, openMenuId, setOpenMenuId, onOpenModal,refetch}) => {

  const handleShowMenu = (id) => {

    if (openMenuId === id) {
      setOpenMenuId(null);
    } else {
      setOpenMenuId(id);
    }
  }

  const priorityStyle = {
    LOW: "bg-blue-50 text-blue-600",
    MEDIUM: "bg-yellow-50 text-yellow-600",
    HIGH: "bg-red-50 text-red-600",
  };

  const statusStyle = {
    TODO: "bg-gray-100 text-gray-600",
    IN_PROGRESS: "bg-blue-50 text-blue-600",
    PENDING: "bg-yellow-50 text-yellow-600",
    COMPLETED: "bg-green-50 text-green-600",
    BLOCKED: "bg-red-50 text-red-600",
  };

  const progress =
    task.progress ??
    (task.status === "COMPLETED" ? 100 : 0);


    const handleCheckBoxChange=async(task)=>{
      const newStatus=
      task.status==="COMPLETED"
      ?"TODO":
      "COMPLETED"

      try{
        await updateTask(task.id,{
          status:newStatus
        });

       await refetch();
        console.log("Updated successfully",newStatus)
      }catch(error){
        console.log("Faled to update status",error)
      }
    }


  return (
      <tr className="border-t border-gray-100 hover:bg-gray-50 transition">

        {/* Checkbox */}
        <td className="px-5 py-5">
          <input type="checkbox"
          checked={task.status==="COMPLETED"}
          onChange={()=>handleCheckBoxChange(task)} />
        </td>


        {/* Task */}
        <td className="px-4 py-5">

          <div className="flex items-center gap-3">

            <div className="w-10 h-10 rounded-xl bg-purple-100 flex items-center justify-center text-lg shrink-0">
              ✓
            </div>

            <div>

              <p className="font-semibold text-gray-900 text-sm">
                {task.title}
              </p>

              <p className="text-xs text-gray-500 mt-1 max-w-[280px] truncate">
                {task.description || "No description"}
              </p>

            </div>

          </div>

        </td>


        {/* Project */}
        <td className="px-4 py-5">

          <span className="px-3 py-1.5 rounded-lg bg-blue-50 text-blue-700 text-xs font-medium">
            {task.project?.projectName ||
              task.projectName ||
              "No Project"}
          </span>

        </td>


        {/* Priority */}
        <td className="px-4 py-5">

          <span
            className={`px-3 py-1.5 rounded-full text-xs font-semibold ${priorityStyle[task.priority] ||
              "bg-gray-100 text-gray-600"
              }`}
          >
            {task.priority || "N/A"}
          </span>

        </td>


        {/* Status */}
        <td className="px-4 py-5">

          <span
            className={`px-3 py-1.5 rounded-full text-xs font-semibold ${statusStyle[task.status] ||
              "bg-gray-100 text-gray-600"
              }`}
          >

            <span className="mr-1">●</span>

            {formatStatus(task.status)}

          </span>

        </td>


        {/* Assignee */}
        <td className="px-4 py-5">

          <div className="flex items-center">

            {task.assignees?.length > 0 ? (

              task.assignees.slice(0, 2).map((member) => (

                <div
                  key={member.id}
                  className="w-8 h-8 rounded-full bg-gray-200 border-2 border-white flex items-center justify-center text-xs font-semibold -ml-1 first:ml-0"
                  title={member.name}
                >
                  {member.name?.charAt(0)}
                </div>

              ))

            ) : task.assigneeName ? (

              <div
                className="w-30 h-8 rounded-sm bg-blue-100 text-blue-600 flex items-center justify-center text-xs font-semibold"
                title={task.assigneeName}
              >
                {task.assigneeName}
              </div>

            ) : (

              <span className="text-gray-400 text-xs">
                Unassigned
              </span>

            )}

          </div>

        </td>


        {/* Due Date */}
        <td className="px-4 py-5">

          <div className="flex items-center gap-2 text-sm">

            <span className="text-gray-400">
              📅
            </span>

            <span className="text-gray-600 whitespace-nowrap">
              {formatDate(task.dueDate)}
            </span>

          </div>

        </td>


        {/* Progress */}
        <td className="px-4 py-5">

          <div className="flex items-center gap-2">

            <div className="w-16 h-2 bg-gray-200 rounded-full overflow-hidden">

              <div
                className="h-full bg-blue-600 rounded-full"
                style={{
                  width: `${progress}%`,
                }}
              />

            </div>

            <span className="text-xs font-medium text-gray-600">
              {progress}%
            </span>

          </div>

        </td>


        {/* Actions */}
        <td className="px-4 py-5 relative">

          <button onClick={() => handleShowMenu(task.id)} className="text-gray-500 hover:text-gray-900 text-xl cursor-pointer">
            ⋮
          </button>

          {openMenuId === task.id && (
            <div className="absolute right-4 top-12 w-36 bg-white border border-gray-200 rounded-xl shadow-lg py-1 z-20">

              <button
                onClick={() => {
                  setOpenMenuId(null);
                  onOpenModal("view",task.id);
                }}
                className="w-full flex items-center gap-3 px-4 py-2.5 text-sm text-gray-700 hover:bg-gray-50 transition"
              >
                <span className="text-gray-500">👁</span>
                <span>View</span>
              </button>




              <button
                onClick={() => {
                  setOpenMenuId(null);
                  onOpenModal("edit",task.id);
                }}
                className="w-full flex items-center gap-3 px-4 py-2.5 text-sm text-gray-700 hover:bg-gray-50 transition"
              >
                <span className="text-gray-500">✎</span>
                <span>Edit</span>
              </button>

              <div className="my-1 border-t border-gray-100"></div>

              <button
                onClick={() => {
                  setOpenMenuId(null);
                  onOpenModal("delete",task.id);
                }}
                className="w-full flex items-center gap-3 px-4 py-2.5 text-sm text-red-600 hover:bg-red-50 transition"
              >
                <span>🗑</span>
                <span>Delete</span>
              </button>

            </div>
          )}


        </td>

      </tr>


  );

};




const formatStatus = (status) => {

  if (!status) return "Unknown";

  return status
    .replaceAll("_", " ")
    .toLowerCase()
    .replace(/\b\w/g, (char) =>
      char.toUpperCase()
    );
};


const formatDate = (date) => {

  if (!date) {
    return "No due date";
  }

  return new Date(date).toLocaleDateString(
    "en-US",
    {
      month: "short",
      day: "numeric",
      year: "numeric",
    }
  );
};


export default TaskList;