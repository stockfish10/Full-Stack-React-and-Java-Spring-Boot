import React from "react"

export const RowItem: React.FC<{
    rowNum: number, 
    rowDesc: string, 
    rowAssign: string, 
    deleteParam: Function
}> = (props) => {
    return(
    <tr onClick={()=>props.deleteParam(props.rowNum)}>
        <th scope="row">{props.rowNum}</th>
        <td>{props.rowDesc}</td>
        <td>{props.rowAssign}</td>
    </tr>
    )
}