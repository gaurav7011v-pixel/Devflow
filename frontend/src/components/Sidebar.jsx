import React from 'react'
import home from "../assets/images/home.svg"
import projects from "../assets/images/project.svg"
import tasks from "../assets/images/task.svg"
import teams from "../assets/images/team.svg"
import calendar from "../assets/images/calender.svg"
import messages from "../assets/images/messages.svg"
import files from "../assets/images/files.svg"
import statistics from "../assets/images/statistics.svg"
import settings from "../assets/images/settings.svg"
import add from "../assets/images/add.svg"
const Sidebar = () => {
  return (
    <div className="w-64 h-screen bg-white shadow-sm p-5 flex flex-col">

      <div className="flex items-center gap-3 px-4 py-3 rounded-lg bg-blue-100 text-blue-600 font-semibold">
        <img className='w-5' src={home} alt="" />
        <h1>Dashboard</h1>
      </div>
       <div className='flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={projects} alt="" />
        <h1>Projects</h1>
      </div>
       <div className='flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={tasks} alt="" />
        <h1>Tasks</h1>
      </div>
       <div className='flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={teams} alt="" />
        <h1>Teams</h1>
      </div>
       <div className='flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={calendar} alt="" />
        <h1>Calendar</h1>
      </div>
       <div className='flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={messages} alt="" />
        <h1>Messages</h1>
      </div>
       <div className='flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={files} alt="" />
        <h1>Files</h1>
      </div>
       <div className='flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={statistics} alt="" />
        <h1>Statistics</h1>
      </div>
       <div className='flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={settings} alt="" />
        <h1>Settings</h1>
      </div>
      <div className='mt-auto flex items-center gap-3 px-4 py-3 rounded-lg cursor-pointer hover:bg-gray-100 transition'>
        <img className='w-5' src={add} alt="" />
        <h1>New Project</h1>
      </div>

    </div>
  )
}

export default Sidebar
