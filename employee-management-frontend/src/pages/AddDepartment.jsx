import { useState } from "react";
import { useNavigate } from "react-router-dom";
import DepartmentService from "../services/DepartmentService";

function AddDepartment() {

    const navigate = useNavigate();

    const [department, setDepartment] = useState({
        departmentName: "",
        departmentCode: "",
        location:""
    });

    const handleChange = (e) => {

        const { name, value } = e.target;

        setDepartment({
            ...department,
            [name]: value
        });

    };

    const saveDepartment = (e) => {

        e.preventDefault();

        DepartmentService.createDepartment(department)
            .then(() => {

                alert("Department Added Successfully");

                navigate("/departments");

            })
            .catch((error) => {

                console.log(error);

                alert("Unable to Add Department");

            });

    };

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3>Add Department</h3>

                </div>

                <div className="card-body">

                    <form onSubmit={saveDepartment}>

                        <div className="mb-3">

                            <label className="form-label">
                                Department Name
                            </label>

                            <input
                                type="text"
                                className="form-control"
                                name="departmentName"
                                value={department.departmentName}
                                onChange={handleChange}
                            />

                        </div>
                         <div className="mb-3">

                            <label className="form-label">
                                Location
                            </label>
                             <input
                                type="text"
                                className="form-control"
                                name="location"
                                value={department.location}
                                onChange={handleChange}
                            />

                            

                        </div>

                        <div className="mb-3">

                            <label className="form-label">
                                Department Code
                            </label>
                             <input
                                type="text"
                                className="form-control"
                                name="departmentCode"
                                value={department.departmentCode}
                                onChange={handleChange}
                            />

                            

                        </div>

                        <button
                            type="submit"
                            className="btn btn-success">

                            Save Department

                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default AddDepartment;