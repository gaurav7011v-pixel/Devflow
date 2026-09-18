import React from "react";
import useDashBoardProjects from "../hooks/useDashBoardProjects";
import useAllMembers from "../hooks/useAllMembers";
import { useState } from "react";
import { assignMemberToTask, createTask } from "../services/DashBoardServices";
const CreateTask = ({ onClose }) => {
    const { projects } = useDashBoardProjects();
    const { members } = useAllMembers();

    const [formData, setFormData] = useState({
        title: "",
        description: "",
        projectId: "",
        dueDate: "",
        status: "TODO",
        priority: "MEDIUM",
        memberId: ""
    });


    const handleChange = (e) => {
        const { name, value } = e.target;

        setFormData((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
    e.preventDefault();

    if (!formData.title.trim()) {
        alert("Task title is required");
        return;
    }

    if (!formData.projectId) {
        alert("Please select a project");
        return;
    }

    try {

        const taskData = {
            title: formData.title,
            description: formData.description,
            dueDate: formData.dueDate || null,
            status: formData.status,
            priority: formData.priority
        };

        const createdTask = await createTask(
            formData.projectId,
            taskData
        );

        if(formData.memberId){
            await assignMemberToTask(
                createdTask.id,
                formData.memberId
            )
        }

        console.log("Task created:", createdTask);

        onClose();

    } catch (error) {

        console.error("Failed to create task:", error);

        alert(
            error.response?.data?.message ||
            "Failed to create task"
        );
    }
};
    return (
        <form onSubmit={handleSubmit}>
        <div className="fixed inset-1 z-50 bg-black/40 flex items-center justify-center px-4">

            <div className="w-full max-w-2xl bg-white rounded-xl shadow-2xl border border-gray-200">

                {/* Header */}
                <div className="flex items-start justify-between px-7 py-5 border-b border-gray-100">

                    <div>
                        <h2 className="text-xl font-semibold text-gray-900">
                            Create task
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Add a new task to your project and keep your team on track.
                        </p>
                    </div>

                    <button
                        onClick={onClose}
                        className="text-gray-400 hover:text-gray-700 text-xl leading-none"
                    >
                        ×
                    </button>

                </div>


                {/* Form */}
                <div className="px-7 py-6">

                    {/* Task title */}
                    <div className="mb-5">

                        <label className="block text-sm font-medium text-gray-700 mb-2">
                            Task title
                        </label>

                        <input
                            type="text"
                            name="title"
                            value={formData.title}
                            onChange={handleChange}
                            placeholder="e.g. Implement user authentication"
                            className="w-full h-11 px-3 rounded-lg border border-gray-300
                         text-sm text-gray-900 placeholder:text-gray-400
                         outline-none transition
                         focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                        />

                    </div>


                    {/* Description */}
                    <div className="mb-5">

                        <label className="block text-sm font-medium text-gray-700 mb-2">
                            Description
                        </label>

                        <textarea
                            rows="4"
                            name="description"
                            value={formData.description}
                            onChange={handleChange}
                            placeholder="Describe what needs to be done..."
                            className="w-full px-3 py-3 rounded-lg border border-gray-300
                         text-sm text-gray-900 placeholder:text-gray-400
                         outline-none resize-none transition
                         focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                        />

                    </div>


                    {/* Project + Due date */}
                    <div className="grid grid-cols-2 gap-4 mb-5">

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Project
                            </label>

                            <select
                                name="projectId"
                                value={formData.projectId}
                                onChange={handleChange}
                                className="w-full h-11 px-3 rounded-lg border border-gray-300
                           bg-white text-sm text-gray-700 outline-none
                           focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                            >
                                <option value="">Select project</option>
                                {(projects || []).map((project) => (
                                    <option key={project.id} value={project.id}>{project.projectName}</option>
                                ))}

                            </select>
                        </div>


                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Due date
                            </label>

                            <input
                                type="date"
                                name="dueDate"
                                value={formData.dueDate}
                                onChange={handleChange}
                                className="w-full h-11 px-3 rounded-lg border border-gray-300
                           bg-white text-sm text-gray-700 outline-none
                           focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                            />
                        </div>

                    </div>


                    {/* Status + Priority */}
                    <div className="grid grid-cols-2 gap-4 mb-5">

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Status
                            </label>

                            <select
                                name="status"
                                value={formData.status}
                                onChange={handleChange}
                                defaultValue="TODO"
                                className="w-full h-11 px-3 rounded-lg border border-gray-300
                           bg-white text-sm text-gray-700 outline-none
                           focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                            >
                                <option value="TODO">To do</option>
                                <option value="IN_PROGRESS">In progress</option>
                                <option value="PENDING">Pending</option>
                                <option value="COMPLETED">Completed</option>
                                <option value="BLOCKED">Blocked</option>
                            </select>
                        </div>


                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Priority
                            </label>

                            <select
                                name="priority"
                                value={formData.priority}
                                onChange={handleChange}
                                defaultValue="MEDIUM"
                                className="w-full h-11 px-3 rounded-lg border border-gray-300
                           bg-white text-sm text-gray-700 outline-none
                           focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                            >
                                <option value="LOW">Low</option>
                                <option value="MEDIUM">Medium</option>
                                <option value="HIGH">High</option>
                            </select>
                        </div>

                    </div>


                    {/* Assignee */}
                    <div>

                        <label className="block text-sm font-medium text-gray-700 mb-2">
                            Assignee
                        </label>

                        <select
                            name="memberId"
                            value={formData.memberId}
                            onChange={handleChange}
                            className="w-full h-11 px-3 rounded-lg border border-gray-300
                         bg-white text-sm text-gray-700 outline-none
                         focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                        >
                            <option value="">Select assignee</option>
                            
                            {(members || []).map((member) => (
                                <option key={member.id} value={member.id}>
                                    {member.name}
                                </option>
                            ))}
                        </select>

                    </div>

                </div>


                {/* Footer */}
                <div className="flex justify-end items-center gap-3 px-7 py-4 border-t border-gray-100 bg-gray-50/50 rounded-b-xl">

                    <button
                        onClick={onClose}
                        className="h-10 px-4 rounded-lg border border-gray-300
                       text-sm font-medium text-gray-700
                       hover:bg-white transition"
                    >
                        Cancel
                    </button>

                    <button
                        type="submit"
                        className="h-10 px-5 rounded-lg bg-blue-600
                       text-sm font-medium text-white
                       hover:bg-blue-700 transition"
                    >
                        Create task
                    </button>

                </div>

            </div>

        </div>

        </form>
    );
};

export default CreateTask;