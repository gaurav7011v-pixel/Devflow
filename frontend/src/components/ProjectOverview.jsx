import React from 'react';
import more from '../assets/images/more.svg';
import useDashBoardProjects from '../hooks/useDashBoardProjects';

const ProjectOverview = () => {
  const { projects = [], loading, error } = useDashBoardProjects();

  if (loading) {
    return (
      <div className="bg-white 
      rounded-2xl 
      shadow-lg border
       border-gray-100 p-6 
       flex justify-center 
       items-center h-48">
        <span className="text-gray-500 font-medium text-sm">Loading projects...</span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-white rounded-2xl shadow-lg border border-red-100 bg-red-50 p-6 text-red-600 font-medium text-center text-sm">
        Failed to load projects.
      </div>
    );
  }

  // Dynamic status badge color mapping
  const getStatusBadgeStyle = (status) => {
    switch (status?.toLowerCase()) {
      case 'completed':
        return 'bg-emerald-100 text-emerald-700';
      case 'in_progress':
        return 'bg-blue-100 text-blue-700';
      case 'in review':
        return 'bg-amber-100 text-amber-700';
      case 'on hold':
        return 'bg-red-100 text-red-700';
      default:
        return 'bg-gray-100 text-gray-700';
    }
  };

  // Avatar background colors based on letter
  const getAvatarColor = (char = '') => {
    const colors = [
      'bg-indigo-100 text-indigo-700',
      'bg-blue-100 text-blue-700',
      'bg-purple-100 text-purple-700',
      'bg-emerald-100 text-emerald-700',
      'bg-rose-100 text-rose-700',
    ];
    const index = char.charCodeAt(0) % colors.length || 0;
    return colors[index];
  };

  return (
    <div className="bg-white rounded-2xl shadow-lg border border-gray-100 p-6">
      {/* Header */}
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-bold text-gray-800">Project Overview</h2>
        <button
          type="button"
          className="text-blue-500 hover:text-blue-600 text-sm font-semibold transition-colors"
        >
          View all projects →
        </button>
      </div>
  
      {/* Projects List */}
      <div className="max-h-80 overflow-y-auto space-y-2 pr-1">
        {projects.length === 0 ? (
          <div className="text-gray-400 text-sm py-8 text-center">
            No active projects found.
          </div>
        ) : (
          projects.map((project) => {
            const initial = project.projectName?.charAt(0)?.toUpperCase() || '?';
            const progress = Math.min(Math.max(project.progress || 0, 0), 100);

            return (
              <div
                key={project.id}
                className="flex items-center justify-between p-3 rounded-xl border border-gray-100 shadow-sm hover:bg-gray-50 transition-colors"
              >
                {/* Left: Avatar & Project Info */}
                <div className="flex items-center gap-3 min-w-0 flex-1 pr-2">
                  <span
                    className={`size-9 flex items-center justify-center font-bold text-sm rounded-lg shrink-0 ${getAvatarColor(
                      initial
                    )}`}
                  >
                    {initial}
                  </span>
                  <div className="truncate">
                    <p className="text-sm font-semibold text-gray-800 truncate">
                      {project.projectName}
                    </p>
                    <p className="text-xs text-gray-400 truncate">
                      {project.category}
                    </p>
                  </div>
                </div>

                {/* Center: Progress Bar & Percentage */}
                <div className="hidden sm:flex items-center gap-3 w-1/3 mx-3">
                  <div className="rounded-full bg-gray-100 h-2 w-full overflow-hidden">
                    <div
                      className="rounded-full bg-blue-600 h-2 transition-all duration-300"
                      style={{ width: `${progress}%` }}
                    />
                  </div>
                  <span className="text-xs font-semibold text-gray-600 w-9 text-right">
                    {progress}%
                  </span>
                </div>

                {/* Right: Status Badge & Options */}
                <div className="flex items-center gap-3 shrink-0">
                  <span
                    className={`text-xs font-bold px-2.5 py-1 rounded-md ${getStatusBadgeStyle(
                      project.status
                    )}`}
                  >
                    {project.status}
                  </span>

                  <button
                    type="button"
                    className="p-1.5 rounded-lg hover:bg-gray-100 text-gray-400 hover:text-gray-600 transition-colors"
                    aria-label={`Options for ${project.projectName}`}
                  >
                    <img className="size-4" src={more} alt="" />
                  </button>
                </div>
              </div>
            );
          })
        )}
      </div>
    </div>
  );
};

export default ProjectOverview;