import React from 'react'
import WelcomeSection from './WelcomeSection'
import StatsCard from './StatsCard'
import ProjectOverview from './ProjectOverview'
import RecentActivity from './RecentActivity'
import TaskSummary from './TaskSummary'
import UpcomingDeadline from './UpcomingDeadline'

const MainContent = () => {
  return (
    <div className='space-y-6'>
      <WelcomeSection/>
      <StatsCard/>
      <div className='grid grid-cols-3 gap-5'>
        <div className='col-span-2'>
          <ProjectOverview/>
          </div>
        <div className='col-span-1'>
          <TaskSummary/>
          </div>
      
      </div>
      
      <div className='grid grid-cols-2 gap-5'>
        <RecentActivity/>
      <UpcomingDeadline/>
      </div>
      
    </div>
  )
}

export default MainContent
