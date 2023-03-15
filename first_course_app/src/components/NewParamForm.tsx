import React, { useState } from "react";

export const NewParamForm: React.FC<{addParam: Function}> = (props) => {

    const [assigned, setAssigned] = useState('');
    const [description, setDescription] = useState('');

    const submitParams = () => {
        if (description !== '' && assigned !== ''){
            props.addParam(description, assigned);
            setDescription('');
            setAssigned('');
        }
    }

    return(
        <div className="mt-5">
            <form>
                <div className="mb-3">
                    <label className="form-label">Assigned</label>
                    <input className="form-control" 
                           type="text" 
                           required
                           value={assigned}
                           onChange={e => setAssigned(e.target.value)}>
                    </input>
                </div>
                <div className="mb-3">
                    <label className="form-label">Description</label>
                    <textarea className="form-control" 
                              rows={3} 
                              required
                              value={description}
                              onChange={e => setDescription(e.target.value)}></textarea>
                </div>
                <button className="btn btn-primary mt-3" onClick={submitParams}>
                    Add Param
                </button>
            </form>
        </div>
    )
}