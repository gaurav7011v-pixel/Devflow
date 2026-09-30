import React from 'react'

const WelcomeSection = () => {
    const currentDate = () => {
    const today = new Date();

    const day = String(today.getDate()).padStart(2, "0");

    const month = today.toLocaleString("en-US", {
        month: "short"
    });

    const year = today.getFullYear();

    return `${day} ${month} ${year}`;
};
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
                    {currentDate()}
                </div>
            </div>
        </div>
    )
}

export default WelcomeSection
