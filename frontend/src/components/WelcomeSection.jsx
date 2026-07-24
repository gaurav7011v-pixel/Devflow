import React from 'react'

const WelcomeSection = () => {
    return (
        <div className='flex justify-between items-center p-6 '>
            <div>
                <p className="text-3xl font-bold">
                    Welcome back, User 👋
                </p>

                <p className="text-gray-500 mt-2">
                    Here's what's happening with your projects today.
                </p>
            </div>
            <div>
                <div className="bg-white shadow-sm rounded-lg px-4 py-2">
                    22 July 2026
                </div>
            </div>
        </div>
    )
}

export default WelcomeSection
