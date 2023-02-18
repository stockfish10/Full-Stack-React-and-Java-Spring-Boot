function RowItem(props){
    return(
    <tr>
        <th scope="row">{props.rowNum}</th>
        <td>{props.rowDesc}</td>
        <td>{props.rowAssign}</td>
    </tr>
    )
}

export default RowItem