import React from 'react';
import upArrow from '../assets/images/topArrow.svg';
import folder from '../assets/images/folder.svg';
import users from '../assets/images/users.svg';
import clock from '../assets/images/clock.svg';
import checkbox from '../assets/images/checkbox.svg';
import useDashBoardSummary from '../hooks/useDashBoard';

const StatsCard = () => {
  const { summary, error, loading } = useDashBoardSummary();

  if (loading) {
    return (
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 md:gap-6">
        {[...Array(4)].map((_, i) => (
          <div
            key={i}
            className="bg-white rounded-2xl shadow-lg border border-gray-100 p-5 h-28 animate-pulse flex items-center gap-4"
          >
            <div className="size-12 rounded-xl bg-gray-100 shrink-0" />
            <div className="flex-1 space-y-2">
              <div className="h-3 bg-gray-100 rounded w-1/2" />
              <div className="h-6 bg-gray-100 rounded w-1/3" />
            </div>
          </div>
        ))}
      </div>
    );
  }

  if (error) {
    return (
      <div className="p-4 rounded-2xl shadow-lg border border-red-100 bg-red-50 text-red-600 font-medium text-center text-sm">
        Failed to load dashboard metrics.
      </div>
    );
  }

  // Card Configuration Data
  const statCards = [
    {
      id: 'projects',
      title: 'Total Projects',
      value: summary?.totalProjects ?? 0,
      trend: summary?.projectsTrend || '15%',
      icon: folder,
      bgColor: 'bg-violet-100',
    },
    {
      id: 'completed',
      title: 'Tasks Completed',
      value: summary?.completedTasks ?? 0,
      trend: summary?.completedTrend || '15%',
      icon: checkbox,
      bgColor: 'bg-blue-100',
    },
    {
      id: 'in-progress',
      title: 'In-Progress',
      value: summary?.inProgressTasks ?? 0,
      trend: summary?.inProgressTrend || '15%',
      icon: clock,
      bgColor: 'bg-emerald-100',
    },
    {
      id: 'team',
      title: 'Team Members',
      value: summary?.teamMembers ?? 0,
      trend: summary?.teamTrend || '15%',
      icon: users,
      bgColor: 'bg-amber-100',
    },
  ];

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 md:gap-6">
      {statCards.map((card) => (
        <div
          key={card.id}
          className="bg-white rounded-2xl shadow-lg border border-gray-100 p-5 flex items-center gap-4 hover:border-gray-200 transition-colors"
        >
          {/* Icon Badge */}
          <div
            className={`size-12 flex items-center justify-center rounded-xl shrink-0 ${card.bgColor}`}
          >
            <img className="size-6 object-contain" src={card.icon} alt="" />
          </div>

          {/* Stats & Metric */}
          <div className="min-w-0 flex-1">
            <p className="text-gray-500 text-xs font-medium truncate">
              {card.title}
            </p>
            <p className="text-2xl font-bold text-gray-800 my-0.5">
              {card.value}
            </p>
            <div className="flex items-center gap-1 text-xs">
              <img className="size-3.5" src={upArrow} alt="" />
              <span className="text-emerald-600 font-semibold">
                {card.trend}
              </span>
              <span className="text-gray-400 truncate">from last week</span>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default StatsCard;