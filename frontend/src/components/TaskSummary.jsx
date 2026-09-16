import React from 'react';
import useTaskSummary from '../hooks/useTaskSummary';

const TaskSummary = () => {
  const { taskSummary, loading, error } = useTaskSummary();

  if (loading) {
    return (
      <div className="bg-white rounded-2xl shadow-lg border border-gray-100 p-6 flex justify-center items-center h-72">
        <span className="text-gray-500 font-medium text-sm">Loading task summary...</span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="bg-white-100 rounded-2xl shadow-lg border border-red-100 bg-red-50 p-6 text-red-600 font-medium text-center text-sm">
        Failed to load task summary.
      </div>
    );
  }

  // Safe task extractions (Fixed assignments and key matching)
  const todo = taskSummary?.todo || 0;
  const completed = taskSummary?.completed || 0;
  const inProgress = taskSummary?.inProgress || 0;
  const pending = taskSummary?.pending || 0;
  const overdue = taskSummary?.overdue ?? taskSummary?.blocked ?? 0;

  const totalTasks = todo + completed + inProgress + pending + overdue;

  // Calculate percentage angles for conic gradient chart across all 5 statuses
  const p1 = totalTasks ? (todo / totalTasks) * 360 : 0;
  const p2 = p1 + (totalTasks ? (completed / totalTasks) * 360 : 0);
  const p3 = p2 + (totalTasks ? (inProgress / totalTasks) * 360 : 0);
  const p4 = p3 + (totalTasks ? (pending / totalTasks) * 360 : 0);

  const chartStyle = {
    background: totalTasks
      ? `conic-gradient(
          #06b6d4 0deg ${p1}deg, 
          #10b981 ${p1}deg ${p2}deg, 
          #3b82f6 ${p2}deg ${p3}deg, 
          #f59e0b ${p3}deg ${p4}deg, 
          #ef4444 ${p4}deg 360deg
        )`
      : '#f3f4f6',
  };

  const stats = [
    { label: 'TODO', count: todo, color: 'bg-cyan-500' },
    { label: 'Completed', count: completed, color: 'bg-emerald-500' },
    { label: 'In Progress', count: inProgress, color: 'bg-blue-500' },
    { label: 'Pending', count: pending, color: 'bg-amber-500' },
    { label: 'Overdue', count: overdue, color: 'bg-rose-500' },
  ];

  return (
    <div className="bg-white rounded-2xl shadow-lg border border-gray-100 p-6">
      {/* Header */}
      <div className="mb-6">
        <h2 className="text-xl font-bold text-gray-800">Task Summary</h2>
        <p className="text-xs text-gray-400 mt-0.5">Overview of active task distributions</p>
      </div>

      <div className="flex flex-col sm:flex-row items-center justify-between gap-6">
        {/* Dynamic Pure CSS Donut Chart */}
        <div className="relative size-36 flex items-center justify-center shrink-0">
          <div
            className="size-full rounded-full transition-all duration-500"
            style={chartStyle}
          />
          {/* Inner cutout circle */}
          <div className="absolute size-24 bg-white rounded-full flex flex-col items-center justify-center shadow-inner">
            <span className="text-2xl font-bold text-gray-800">{totalTasks}</span>
            <span className="text-[10px] uppercase font-semibold text-gray-400 tracking-wider">
              Total Tasks
            </span>
          </div>
        </div>

        {/* Task statistics */}
        <div className="flex flex-col gap-2.5 w-full sm:w-auto flex-1 max-w-xs">
          {stats.map((item) => (
            <div
              key={item.label}
              className="flex items-center justify-between gap-4 text-sm"
            >
              <div className="flex items-center gap-2">
                <span className={`size-2.5 rounded-full ${item.color}`} />
                <span className="text-gray-600 font-medium">{item.label}</span>
              </div>
              <span className="font-bold text-gray-800">{item.count}</span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default TaskSummary;