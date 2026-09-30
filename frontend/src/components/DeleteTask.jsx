import React from 'react';
import { deleteTask } from '../services/DashBoardServices';

const DeleteTask = ({ onClose, taskId }) => {


    return (
        <div className="fixed inset-0 z-50 bg-black/50 flex items-center justify-center px-4">

            <div className="bg-white w-full max-w-md rounded-xl shadow-2xl overflow-hidden">

                {/* Header */}
                <div className="flex items-center justify-between px-6 py-5 border-b border-gray-200">

                    <h2 className="text-lg font-semibold text-gray-900">
                        Delete Task
                    </h2>

                    <button
                        onClick={onClose}
                        className="text-gray-400 hover:text-gray-700 text-2xl leading-none transition cursor-pointer"
                    >
                        ×
                    </button>

                </div>


                {/* Content */}
                <div className="px-6 py-6">

                    {/* Warning Icon */}
                    <div className="flex justify-center mb-5">
                        <div className="w-14 h-14 rounded-full bg-red-100 flex items-center justify-center">
                            <span className="text-red-600 text-2xl">
                                !
                            </span>
                        </div>
                    </div>

                    <h3 className="text-center text-lg font-semibold text-gray-900 mb-2">
                        Are you sure?
                    </h3>

                    <p className="text-center text-gray-500 text-sm leading-6">
                        Do you really want to delete this task?
                        <br />
                        This action cannot be undone.
                    </p>

                </div>


                {/* Footer */}
                <div className="flex justify-end gap-3 px-6 py-4 bg-gray-50 border-t border-gray-200">

                    <button
                        onClick={onClose}
                        className="px-5 py-2.5 rounded-lg border border-gray-300 
                                   text-gray-700 text-sm font-medium
                                   hover:bg-gray-100 transition cursor-pointer"
                    >
                        Cancel
                    </button>

                    <button
                        onClick={async () => {
                            try {
                                await deleteTask(taskId);
                                onClose();
                            } catch (error) {
                                console.error("Failed to delete task:", error);
                            }
                        }}
                        className="px-5 py-2.5 rounded-lg bg-red-600 
                                   text-white text-sm font-medium
                                   hover:bg-red-700 transition cursor-pointer"
                    >
                        Delete Task
                    </button>

                </div>

            </div>

        </div>
    );
};

export default DeleteTask;