import React, { useState } from 'react'
import logo from "../assets/images/devLogo.svg"
import show from "../assets/images/show.svg"
import hide from "../assets/images/hide.svg"
import { Link } from 'react-router-dom'
import{register}from '../api/authApi'
import { useNavigate } from 'react-router-dom'

const Register = () => {
   const navigate=useNavigate();
  const[email,setEmail]=useState("");
  const[password,setPassword]=useState("");
  const[username,setUsername]=useState("");
  const[showPass,setShowPass]=useState(false);

  const handlePassword = () => {
    setShowPass(prev => !prev);
  }

  const handleSubmit=async(e)=>{
    e.preventDefault();
    const data={
      name:username,
      email,
      password
    }
    try{
    const response=await register(data);
      alert(response.data)
      navigate("/login")
    }catch(error){
      alert(error.response.data.message);
    }
  }
  return (
    <div className='min-h-screen flex items-center justify-center bg-gray-100'>
       <form onSubmit={handleSubmit} className='bg-white p-8 rounded-xl shadow-lg w-full max-w-md space-y-5'>
        <div className="flex justify-center">
          <img src={logo} alt="DevFlow" className="ml-15 w-50 max-auto mb-0" />
        </div>
        <div className='text-center mb-6'>
          <h1 className='text-3xl font-bold'>Welcome👋</h1>
          <h3 className='text-gray-500 mt-2'>Register to Join Devflow</h3>
        </div>
        <div className='flex flex-col gap-2'>
          <label className='font-medium'>Username : </label>
          <input className='border rounded-lg px-4 py-3 outline-none focus:ring-1 focus:ring-blue-500'
            type="text"
            placeholder='Enter Username'
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className='flex flex-col gap-2'>
          <label className='font-medium'>Email : </label>
          <input className='border rounded-lg px-4 py-3 outline-none focus:ring-1 focus:ring-blue-500'
            type="email"
            placeholder='you@example.com'
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div className='flex flex-col gap-2'>
          <label className='font-medium'>Password : </label>
          <div className='p-0 relative'>
            <input className='border w-full rounded-lg px-4 py-3 outline-none focus:ring-1 focus:ring-blue-500'
              type={showPass ? "text" : "password"}
              placeholder="••••••••"
              onChange={(e) => setPassword(e.target.value)}
            />
            <button
              type="button"
              onClick={handlePassword}
              className="absolute top-4 right-3 cursor-pointer hover:opacity-70"
            ><img className='w-5' src={showPass ? hide : show} alt="" />
            </button>
          </div>
        </div>

        <button className='w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition'
        >
          Sign Up
        </button>

        <div className='mb-6 mt-3'>If you have already an account? <Link to={"/login"} className='text-blue-500 hover:underline'>Sign In</Link> </div>
      </form>
    </div>
  )
}

export default Register
