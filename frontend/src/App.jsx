import { useState } from 'react'
import './App.css'
import { Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login'
import Register from './pages/Register'
import Dashboard from './pages/Dashboard'
import TaskMainCom from './components/TaskMainCom'
import ProjectContents from './components/ProjectContents'

function App() {
  return (
    <>
     <Routes>
      <Route path='/dashboard' element={<Dashboard/>}></Route>
      <Route path='/' element={<Navigate to="/login"/>}/>
      <Route path="/login" element={<Login/>}/>
      <Route path="/register" element={<Register/>}/>
      <Route path="/tasks" element={<TaskMainCom/>}/>
      <Route path="/projects" element={<ProjectContents/>}/>
     </Routes>
    </>
  )
}

export default App
