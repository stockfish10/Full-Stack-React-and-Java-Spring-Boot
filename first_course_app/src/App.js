import './App.css';
import TableItem from './components/TableItem.js';
import NewParamForm from './components/NewParamForm.js';
import React, { useState } from 'react';

function App() {

  const [myParams,setParams] = useState([
    {rowNum: 1, rowDesc: "Feed puppy", rowAssign: "Peter"},
    {rowNum: 2, rowDesc: "Water plants", rowAssign: "Peter"},
    {rowNum: 3, rowDesc: "Make dinner", rowAssign: "Josh"},
    {rowNum: 4, rowDesc: "Make smt", rowAssign: "Josh"}
  ])

  const addParam = (description, assigned) => {
    if (myParams.length > 0){
      const newParam = {rowNum: myParams.length+1, rowDesc: description, rowAssign: assigned};
    
      setParams([...myParams, newParam])

      console.log("New Param pushed")
      console.log(myParams)
    }
  }

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header">
          <p>Your todo's</p>
        </div>
        <div className="card-body">
          <TableItem myParams={myParams}/>
          <NewParamForm addParam={addParam}/>
        </div>
      </div>
    </div>
  );
}

export default App;
