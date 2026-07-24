import React from 'react'
import bell from "../assets/images/notification.svg"
import logo from "../assets/images/devLogo.svg"
import avatar from "../assets/images/avatar.svg"
import hamburger from "../assets/images/hamburger.svg"

const Navbar = () => {
    return (
        <div className='sticky top-0 z-50 flex justify-between items-center gap-10 bg-white shadow-sm px-6 py-1'>

            <div className='flex items-center gap-6'>
                <button className="cursor-pointer hover:bg-gray-100 rounded-lg transition">
                    <img className="w-12" src={hamburger} alt="Menu" />
                </button>
                <img className='w-50 '
                    src={logo} alt="" />
                <input
                    className='p-0.5 border border-gray-300 w-96 px-4 rounded-lg outline-none focus:ring-1 focus:ring-blue-500'
                    type="text" placeholder='🔍 Search project,tasks,teams....' />
            </div>

            <div className='flex gap-5 mr-10 items-center'>
                <img className='w-7' src={bell} alt="" />
                <span className='text-2xl text-gray-100'>|</span>
                <div className='flex items-center gap-2 px-3 py-2 rounded-lg hover:bg-gray-100 cursor-pointer transition'>
                    <img
                        className='w-6'
                        src={avatar} alt="" />
                    <h4>Admin</h4>
                </div>
            </div>
        </div>

    )
}

export default Navbar
