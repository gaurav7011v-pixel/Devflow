import React from 'react';
import bell from "../assets/images/notification.svg";
import logo from "../assets/images/devLogo.svg";
import avatar from "../assets/images/avatar.svg";
import useSearchTask from '../hooks/useSearchTask';
import filter from '../assets/images/filter.svg'

const Navbar = () => {

    const {
        keyword,
        setKeyword,
        tasks,
        loading,
        error
    } = useSearchTask();

    return (
        <div className="sticky top-0 z-50 flex justify-between items-center gap-10 bg-white shadow-sm px-6 py-1">

            <div className="flex items-center gap-6">

                <img
                    className="w-50"
                    src={logo}
                    alt="DevFlow"
                />
                <div className='relative'>
                <div className='flex items-center gap-2'>
                <input
                    value={keyword}
                    onChange={(e) => setKeyword(e.target.value)}
                    className="p-0.5 border border-gray-300 w-96 px-4 rounded-lg outline-none focus:ring-1 focus:ring-blue-500"
                    type="text"
                    placeholder="🔍 Search tasks..."
                />
                <img src={filter} className='size-6 cursor-pointer' />
            </div>
                {keyword && tasks.length>0 &&(
                    <div className='absolute top-full left-0 mt-2 w-96 bg-white border border-gray-200 rounded-lg shadow-lg overflow-hidden'>
                        {tasks.map((task)=>(
                            <div key={task.id}
                             className="px-4 py-3 hover:bg-gray-50 cursor-pointer">
                                <p className="font-medium">{task.title}</p>
                                <p className="text-sm text-gray-500">{task.status} • {task.priority}</p>
                            </div>
                        ))}
                    </div>
                )}
                
                </div> 

            </div>

            <div className="flex gap-5 mr-10 items-center">

                <img
                    className="w-7"
                    src={bell}
                    alt="Notifications"
                />

                <span className="text-2xl text-gray-100">|</span>

                <div className="flex items-center gap-2 px-3 py-2 rounded-lg hover:bg-gray-100 cursor-pointer transition">

                    <img
                        className="w-6"
                        src={avatar}
                        alt="Avatar"
                    />

                    <h4>Admin</h4>

                </div>

            </div>

        </div>
    );
};

export default Navbar;