import RowItem from "./RowItem.js"

function TableItem(props){
    return(
        <table className="table table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Description</th>
            <th scope="col">Assigned</th>
          </tr>
        </thead>
        <tbody>

            {props.myParams.map(myParam => (
                <RowItem
                rowNum={myParam.rowNum}
                rowDesc={myParam.rowDesc}
                rowAssign={myParam.rowAssign}/>
            ))}

        </tbody>
      </table>
    )
}

export default TableItem