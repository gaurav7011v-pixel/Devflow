import React, { useState } from "react";
import TaskTxtAndBtn from "./TaskTxtAndBtn";
import TaskStatCards from "./TaskStatCards";
import SearchAndFilter from "./SearchAndFilter";
import TaskList from "./TaskList";

const TaskMainCom = () => {

  const [filters, setFilters] = useState({});

  return (
    <div>

      <TaskTxtAndBtn />

      <TaskStatCards />

      <SearchAndFilter
        onApplyFilters={setFilters}
      />

      <TaskList
        filters={filters}
      />

    </div>
  );
};

export default TaskMainCom;