import React from "react"
import { RowItem } from "./RowItem"

export const TableItem: React.FC<{myParams: MyModel[], deleteParam: Function}> = (props) => {
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
                key={myParam.rowNum}
                rowNum={myParam.rowNum}
                rowDesc={myParam.rowDesc}
                rowAssign={myParam.rowAssign}
                deleteParam={props.deleteParam}/>
            ))}

        </tbody>
      </table>
    )
}