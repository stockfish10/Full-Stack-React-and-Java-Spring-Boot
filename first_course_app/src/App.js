import './App.css';
import TableItem from './components/TableItem.js';
import NewParamForm from './components/NewParamForm.js';
import React, { useState } from 'react';

function App() {

  const [showAddForm, setShowAddForm] = useState(false);

  const [myParams,setParams] = useState([
    {rowNum: 1, rowDesc: "Feed puppy", rowAssign: "Peter"},
    {rowNum: 2, rowDesc: "Water plants", rowAssign: "Peter"},
    {rowNum: 3, rowDesc: "Make dinner", rowAssign: "Josh"},
    {rowNum: 4, rowDesc: "Make smt", rowAssign: "Josh"}
  ])

  const addParam = (description, assigned) => {

    let rowNum = 0;

    if (myParams.length > 0){
      rowNum = myParams[myParams.length-1].rowNum + 1;
    }else{
      rowNum = 1;
    }
      const newParam = {rowNum: myParams.length+1, rowDesc: description, rowAssign: assigned};
    
      setParams([...myParams, newParam])

      console.log("New Param pushed")
      console.log(myParams)
    
  }

  const deleteParam = (deleteRowNumber) => {
    let filtered = myParams.filter(function(value){
      return value.rowNum !== deleteRowNumber;
    });
    setParams(filtered);
  }

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header">
          <p>Your todo's</p>
        </div>
        <div className="card-body">
          <TableItem myParams={myParams} deleteParam={deleteParam}/>
          <button onClick={() => setShowAddForm(!showAddForm)} className="btn btn-primary">
            {showAddForm ? 'Close New Param' : 'New Param'}
            </button>

        {showAddForm && <NewParamForm addParam={addParam}/>}
          

        </div>
      </div>
    </div>
  );
}

export default App;
