function RowItem(props){
    return(
    <tr onClick={()=>props.deleteParam(props.rowNum)}>
        <th scope="row">{props.rowNum}</th>
        <td>{props.rowDesc}</td>
        <td>{props.rowAssign}</td>
    </tr>
    )
}

export default RowItem