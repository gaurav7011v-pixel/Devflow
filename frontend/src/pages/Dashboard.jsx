import React from 'react';
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import MainContent from "../components/MainContent";

const Dashboard = () => {
  return (
    <div className="h-screen overflow-hidden">

      {/* Fixed Sidebar */}
      <Sidebar />

      {/* Right Side */}
      <div className="ml-[257px] h-screen flex flex-col">

        {/* Navbar */}
        <Navbar />

        {/* Only MainContent scrolls */}
       <main className="flex-1 min-w-0 overflow-y-auto p-8"> 
          <MainContent />
         </main>


      </div>

    </div>
  );
};

export default Dashboard;
