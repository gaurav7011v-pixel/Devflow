import React from 'react'
import { Link } from 'react-router-dom'
import logo from "../assets/images/devLogo.svg";
import { useState } from 'react';
import show from "../assets/images/show.svg";
import hide from "../assets/images/hide.svg";
import {login} from "../api/authApi";
import { useNavigate } from 'react-router-dom';
const Login = () => {
  const navigate=useNavigate();
  const [showPass, setShowPass] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");


  const handlePassword = () => {
    setShowPass(prev => !prev);
  }

  const handleSubmit = async(e) => {
    e.preventDefault();
    const data={
      email,
      password
    }
    try{
       const response = await login(data);
       alert(response.data.message)
    localStorage.setItem( "token",response.data.token)
    navigate("/dashboard")
    console.log(localStorage.getItem("token"));
    }
    catch(error){
      alert(error.response.data.message)
    }

   
  }


  return (
    <div className='min-h-screen flex items-center justify-center bg-gray-100'>
      <form onSubmit={handleSubmit} className='bg-white p-8 rounded-xl shadow-lg w-full max-w-md space-y-5'>
        <div className="flex justify-center">
          <img src={logo} alt="DevFlow" className="ml-15 w-60 max-auto mb-0" />
        </div>
        <div className='text-center mb-6'>
          <h1 className='text-3xl font-bold'>Welcome back 👋</h1>
          <h3 className='text-gray-500 mt-2'>Login to Continue Devflow</h3>
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
              className="absolute top-4 right-3 cursor-pointer hover:opacity-70"
              onClick={handlePassword}
            ><img className='w-5' src={showPass ? hide : show} alt="" />
            </button>
          </div>
        </div>

        <button className='w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition'
        >
          Sign In
        </button>

        <div className='mb-6 mt-3'>If you don't have an account? <Link to={"/register"} className='text-blue-500 hover:underline'>Sign Up</Link> </div>
      </form>
    </div>


  )
}

export default Login
