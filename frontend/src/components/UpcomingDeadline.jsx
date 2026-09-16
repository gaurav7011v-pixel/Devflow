import React from 'react';
import useUpcomingDeadLine from '../hooks/useUpcomingDeadLine';

const UpcomingDeadline = () => {
  const { upcomingDeadlines = [], loading, error } = useUpcomingDeadLine();

  if (loading) {
    return (
      <div className="p-4 rounded-2xl shadow-lg border border-gray-100 flex justify-center items-center h-48">
        <span className="text-gray-500 font-medium">Loading deadlines...</span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="p-4 rounded-2xl shadow-lg border border-red-100 bg-red-50 text-red-600 font-medium text-center">
        Unable to load deadlines.
      </div>
    );
  }

  // Dynamic priority styling helper
  const getPriorityBadgeStyle = (priority) => {
    switch (priority?.toLowerCase()) {
      case 'high':
        return 'bg-red-100 text-red-600';
      case 'medium':
        return 'bg-amber-100 text-amber-600';
      case 'low':
        return 'bg-green-100 text-green-600';
      default:
        return 'bg-gray-100 text-gray-600';
    }
  };

  return (
    <div className="p-4 relative rounded-2xl shadow-lg border border-gray-100 bg-white">
      <h2 className="text-xl font-bold text-gray-800 mb-3">Upcoming Deadlines</h2>

      {/* Scrollable container for items */}
      <div className="max-h-80 overflow-y-auto space-y-2 pr-1">
        {upcomingDeadlines.length === 0 ? (
          <div className="text-gray-400 text-sm py-6 text-center">
            No upcoming deadlines found.
          </div>
        ) : (
          upcomingDeadlines.map((item) => (
            <div
              key={item.id}
              className="flex justify-between items-center p-3 rounded-xl border border-gray-100 shadow-sm hover:bg-gray-50 transition-colors"
            >
              <div className="flex items-center gap-3">
                <span className="bg-purple-600 rounded-full size-2.5 shrink-0" />
                <div>
                  <div className="font-semibold text-gray-800 text-sm">
                    {item.title}
                  </div>
                  <div className="text-gray-500 text-xs">
                    {item.projectName}
                  </div>
                </div>
              </div>

              <div className="flex items-center gap-3">
                <div className="font-semibold text-xs text-gray-700">
                  {item.deadline}
                </div>
                <span
                  className={`text-xs font-bold rounded-md px-2.5 py-1 ${getPriorityBadgeStyle(
                    item.priority
                  )}`}
                >
                  {item.priority}
                </span>
              </div>
            </div>
          ))
        )}
      </div>

      <button
        type="button"
        className="mt-3 pt-2 absolute bottom-0 text-sm font-semibold text-blue-500 hover:text-blue-600 transition-colors w-full text-left"
      >
        View Calendar →
      </button>
    </div>
  );
};

export default UpcomingDeadline;