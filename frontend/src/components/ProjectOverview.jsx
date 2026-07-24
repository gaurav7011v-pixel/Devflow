import React from 'react'
import more from '../assets/images/more.svg'

 const projects = [
  {
    id: 1,
    name: "DevFlow Web App",
    category: "Web Application",
    progress: 75,
    status: "In Progress",
    letter: "D",
    letterBg: "bg-blue-500",
    badgeBg: "bg-blue-200",
    badgeText: "text-blue-600",
    progressBg:"bg-blue-500",
  },
  {
    id: 2,
    name: "Mobile App",
    category: "React Native",
    progress: 60,
    status: "In Review",
    letter: "M",
    letterBg: "bg-green-500",
    badgeBg: "bg-green-100",
    badgeText: "text-green-600",
    progressBg:"bg-green-500",
  },
];

const ProjectOverview = () => {
  
    return (
        <div className="bg-white rounded-xl shadow-sm p-6 h-80">
            <div className="flex justify-between items-center mb-6">
                <h2 className="text-xl font-semibold"
                >Project Overview
                </h2>

                <button className="text-blue-600 text-sm">
                    View all projects
                </button>
            </div>
            <div className='space-y-3'>
            {projects.map((project) => (
                <div key={project.id} className=' flex items-center 
            justify-between shadow-sm p-2 
            rounded-sm hover:bg-gray-50
            transition
            cursor-pointer'>
                    <div className='flex items-center gap-3'>
                        <span className={`w-8 h-8 flex items-center 
                    justify-center text-white rounded-lg
                     ${project.letterBg}`}>{project.letter}</span>
                        <div>
                            <p className='text-sm font-medium'>{project.name}</p>
                            <p className='text-sm text-gray-400'>{project.category}</p>
                        </div>
                    </div>


                    <div className='rounded-sm bg-gray-100 h-2 w-40'>
                        <div className={`rounded-sm ${project.progressBg} h-2`}
                            style={{ width: `${project.progress}%` }}>
                                
                        </div>
                        
                    </div>
                    <span>{project.progress}%</span>

                    <span className={`
                 px-2 rounded-full 
                  font-bold ${project.badgeBg} ${project.badgeText}`}>{project.status}</span>


                    <button className="p-2 rounded-full
                     hover:bg-gray-100
                      transition">
                         <img className="w-5" src={more} alt="" />
                    </button>   
                </div>
            ))}
</div>

        </div>
    )
}

export default ProjectOverview
