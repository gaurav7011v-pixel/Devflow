import React from 'react'
import upArrow from "../assets/images/topArrow.svg"
import folder from "../assets/images/folder.svg"
import users from "../assets/images/users.svg"
import clock from "../assets/images/clock.svg"
import checkbox from "../assets/images/checkbox.svg"

const StatsCard = () => {
  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
      <div className='rounded-xl w-72 flex items-center shadow-sm gap-3 p-4'>
        <div className='w-14 h-14 flex items-center justify-center rounded-lg bg-violet-200'>
        <img className='w-10' src={folder} alt="" />
        </div>
        <div>
            <p className="text-gray-500 text-sm">Total Projects</p>
            <p className="text-3xl font-bold">12</p>
            <div className="flex items-center gap-1 text-sm">
            <img className='w-4' src={upArrow}alt="" />
            <span className="text-green-600 font-medium">15%</span>
            <p className="text-gray-400">from last week</p>
            </div>
        </div>
      </div>

       <div className='rounded-xl w-72 flex items-center shadow-sm gap-3 p-4'>
        <div className='w-14 h-14 flex items-center justify-center rounded-lg bg-blue-200'>
        <img className='w-10' src={checkbox} alt="" />
        </div>
        <div>
            <p className="text-gray-500 text-sm">Task Completed</p>
            <p className="text-3xl font-bold">12</p>
            <div className="flex items-center gap-1 text-sm">
            <img className='w-4' src={upArrow}alt="" />
            <span className="text-green-600 font-medium">15%</span>
            <p className="text-gray-400">from last week</p>
            </div>
        </div>
      </div>

       <div className='rounded-xl w-72 flex items-center shadow-sm gap-3 p-4'>
        <div className='w-14 h-14 flex items-center justify-center rounded-lg bg-green-200'>
        <img className='w-10' src={clock} alt="" />
        </div>
        <div>
            <p className="text-gray-500 text-sm">In-Progress</p>
            <p className="text-3xl font-bold">12</p>
            <div className="flex items-center gap-1 text-sm">
            <img className='w-4' src={upArrow}alt="" />
            <span className="text-green-600 font-medium">15%</span>
            <p className="text-gray-400">from last week</p>
            </div>
        </div>
      </div>

       <div className='rounded-xl w-72 flex items-center shadow-sm gap-3 p-4'>
        <div className='w-14 h-14 flex items-center justify-center rounded-lg bg-orange-200'>
        <img className='w-10' src={users} alt="" />
        </div>
        <div>
            <p className="text-gray-500 text-sm">Team Members</p>
            <p className="text-3xl font-bold">12</p>
            <div className="flex items-center gap-1 text-sm">
            <img className='w-4' src={upArrow}alt="" />
            <span className="text-green-600 font-medium">15%</span>
            <p className="text-gray-400">from last week</p>
            </div>
        </div>
      </div>
    </div>
  )
}

export default StatsCard
