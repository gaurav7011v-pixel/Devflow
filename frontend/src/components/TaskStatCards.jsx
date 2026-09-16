import React from 'react'
import upArrow from '../assets/images/TopArrow.svg'
import checkbox from '../assets/images/checkbox.svg'
import useDashBoardSummary from '../hooks/useDashBoard'
import useTaskSummary from '../hooks/useTaskSummary'
import completedd from '../assets/images/completedd.svg'
import toDo from '../assets/images/todo.svg'
import in_Progress from '../assets/images/inProgress.svg'
import block from '../assets/images/blocked.svg'
const TaskStatCards = () => {
    const { taskSummary, error, loading } = useTaskSummary();

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


  const todoCount=taskSummary?.todo??0;
  const completedCount=taskSummary?.completed??0;
  const inProgressCount=taskSummary?.inProgress??0;
  const pendingCount=taskSummary?.pending??0;
  const blockedCount=taskSummary?.blocked??0;


  const totalCount=todoCount+completedCount+inProgressCount+pendingCount+blockedCount;

  const statCard=[
    {
        id:"tasks",
        title:"Total Tasks",
        count:totalCount,
        icon:checkbox,
        bgColor:'bg-blue-200'

    },
     {
        id:"todo",
        title:"ToDo",
        count:todoCount,
        icon:toDo,
        bgColor:'bg-cyan-200'

    },
     {
        id:"inprogress",
        title:"In-Progress",
        count:inProgressCount,
        icon:in_Progress,
        bgColor:'bg-violet-200'

    },
     {
        id:"completed",
        title:"Completed",
        count:completedCount,
        icon:completedd,
        bgColor:'bg-green-200'

    },
     {
        id:"blocked",
        title:"Blocked",
        count:blockedCount,
        icon:block,
        bgColor:'bg-red-200'

    }
]
    return (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-4">
          {statCard.map((card)=>(
            <div key={card.id} className='flex items-center font-semibold gap-2 p-3 shadow-lg w-55 rounded-lg m-3'>
                <div
                    className={`size-9 flex items-center justify-center rounded-xl shrink-0 ${card.bgColor}`}
                >
                    <img className="size-6 object-contain" src={card.icon} alt="" />
                </div>
                <div>
                <p className='text-sm'>{card.title}</p>
                <h1 className='text-lg font-bold'>{card.count}</h1>
                </div>
            </div>
          ))}
            
        </div>
    )
}

export default TaskStatCards
