import React, { useState } from "react";
import useDashBoardProjects from "../hooks/useDashBoardProjects";
import useAllMembers from "../hooks/useAllMembers";

const SearchAndFilter = ({ onApplyFilters }) => {

  const { projects, loading: projectLoading } = useDashBoardProjects();
  const { members, loading: memberLoading } = useAllMembers();

  const [search, setSearch] = useState("");
  const [status, setStatus] = useState("");
  const [priority, setPriority] = useState("");
  const [project, setProject] = useState("");
  const [assignee, setAssignee] = useState("");
  const [dueDate, setDueDate] = useState("");

  const projectList = projects ?? [];
  const memberList = members ?? [];

  // Apply filters
  const handleAppliedFilter = () => {

    onApplyFilters({
      keyword: search,
      status: status,
      priority: priority,
      projectId: project,
      memberId: assignee,
      dueDate: dueDate
    });

  };

  // Clear filters
  const clearFilters = () => {

    setSearch("");
    setStatus("");
    setPriority("");
    setProject("");
    setAssignee("");
    setDueDate("");

    onApplyFilters({});
  };

  return (
    <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-4 mx-3 md:mx-6 mt-5">

      <div className="flex flex-col xl:flex-row gap-3">

        {/* Search */}
        <div className="w-full xl:w-72">

          <input
            type="text"
            placeholder="Search tasks..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="w-full border border-gray-200 rounded-lg px-3 py-2 text-sm outline-none focus:border-blue-500"
          />

        </div>

        {/* Filters */}
        <div className="grid grid-cols-2 sm:grid-cols-3 xl:flex gap-3 flex-1">

          {/* Status */}
          <select
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            className="border border-gray-200 rounded-lg px-3 py-2 text-sm outline-none"
          >
            <option value="">Status</option>
            <option value="TODO">Todo</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="PENDING">Pending</option>
            <option value="COMPLETED">Completed</option>
            <option value="BLOCKED">Blocked</option>
          </select>

          {/* Priority */}
          <select
            value={priority}
            onChange={(e) => setPriority(e.target.value)}
            className="border border-gray-200 rounded-lg px-3 py-2 text-sm outline-none"
          >
            <option value="">Priority</option>
            <option value="LOW">Low</option>
            <option value="MEDIUM">Medium</option>
            <option value="HIGH">High</option>
          </select>

          {/* Project */}
          <select
            value={project}
            onChange={(e) => setProject(e.target.value)}
            className="border border-gray-200 rounded-lg px-3 py-2 text-sm outline-none"
          >
            <option value="">Project</option>

            {projectLoading ? (
              <option disabled>Loading...</option>
            ) : (
              projectList.map((project) => (
                <option
                  key={project.id}
                  value={project.id}
                >
                  {project.projectName}
                </option>
              ))
            )}

          </select>

          {/* Assignee */}
          <select
            value={assignee}
            onChange={(e) => setAssignee(e.target.value)}
            className="border border-gray-200 rounded-lg px-3 py-2 text-sm outline-none"
          >
            <option value="">Assignee</option>

            {memberLoading ? (
              <option disabled>Loading...</option>
            ) : (
              memberList.map((member) => (
                <option
                  key={member.id}
                  value={member.id}
                >
                  {member.name}
                </option>
              ))
            )}

          </select>

          {/* Due Date */}
          <select
            value={dueDate}
            onChange={(e) => setDueDate(e.target.value)}
            className="border border-gray-200 rounded-lg px-3 py-2 text-sm outline-none"
          >
            <option value="">Due Date</option>
            <option value="OVERDUE">Overdue</option>
            <option value="TODAY">Today</option>
            <option value="TOMORROW">Tomorrow</option>
            <option value="THIS_WEEK">This Week</option>
            <option value="NEXT_WEEK">Next Week</option>
            <option value="NO_DUE_DATE">No Due Date</option>
          </select>

        </div>

        {/* Buttons */}
        <div className="flex gap-2 justify-end">

          <button
            onClick={clearFilters}
            className="px-4 py-2 text-sm border border-gray-200 rounded-lg hover:bg-gray-50"
          >
            Clear
          </button>

          <button
            onClick={handleAppliedFilter}
            className="px-4 py-2 text-sm bg-blue-600 text-white rounded-lg hover:bg-blue-700"
          >
            Filter
          </button>

        </div>

      </div>

    </div>
  );
};

export default SearchAndFilter;