import React from 'react';
import avatar from '../assets/images/avatar.svg';
import useRecentActivities from '../hooks/useRecentActivities';
import { formatRelativeTime } from '../services/formatRelativeTime';
const RecentActivity = () => {
  const { recentActivities = [], loading, error } = useRecentActivities();

  if (loading) {
    return (
      <div className="p-4 rounded-2xl shadow-lg border border-gray-100 flex justify-center items-center h-48 bg-white">
        <span className="text-gray-500 font-medium text-sm">Loading activity...</span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="p-4 rounded-2xl shadow-lg border border-red-100 bg-red-50 text-red-600 font-medium text-center text-sm">
        Failed to load recent activity.
      </div>
    );
  }

  return (
    <div className="p-4 rounded-2xl shadow-lg border border-gray-100 bg-white">
      <h2 className="text-xl font-bold text-gray-800 mb-3">Recent Activity</h2>

      {/* Scrollable list container */}
      <div className="max-h-80 overflow-y-auto divide-y divide-gray-100 pr-1">
        {recentActivities.length === 0 ? (
          <div className="text-gray-400 text-sm py-6 text-center">
            No recent activity to show.
          </div>
        ) : (
          recentActivities.map((activity) => (
            <div
              key={activity.id}
              className="flex justify-between items-start gap-3 py-3 hover:bg-gray-50/80 px-2 rounded-lg transition-colors"
            >
              <div className="flex items-center gap-3 min-w-0">
                <div className="size-8 rounded-full bg-gray-100 shrink-0 overflow-hidden flex items-center justify-center border border-gray-200">
                  <img
                    className="size-full object-cover"
                    src={activity?.userAvatar || avatar}
                    alt={activity?.userName ? `${activity.userName}'s avatar` : 'User avatar'}
                  />
                </div>
                <div className="text-sm text-gray-700 leading-snug truncate">
                  {activity?.description}
                </div>
              </div>

              <span className="text-xs text-gray-400 whitespace-nowrap shrink-0 mt-0.5">
                {formatRelativeTime(activity?.createdAt)}
              </span>
            </div>
          ))
        )}
      </div>

      <button
        type="button"
        className="mt-3 pt-2 text-sm font-semibold text-blue-500 hover:text-blue-600 transition-colors w-full text-left"
      >
        View All Activity →
      </button>
    </div>
  );
};

export default RecentActivity;