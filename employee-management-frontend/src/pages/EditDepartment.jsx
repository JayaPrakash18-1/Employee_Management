import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import DepartmentService from "../services/DepartmentService";

function EditDepartment() {

    const navigate = useNavigate();
    const { id } = useParams();

    const [department, setDepartment] = useState({
        departmentName: "",
        description: ""
    });

    useEffect(() => {

        DepartmentService.getDepartmentById(id)
            .then((response) => {

                setDepartment({

                    departmentName: response.data.departmentName,
                    description: response.data.description

                });

            })
            .catch((error) => {

                console.log(error);

            });

    }, [id]);

    const handleChange = (e) => {

        const { name, value } = e.target;

        setDepartment({
            ...department,
            [name]: value
        });

    };

    const updateDepartment = (e) => {

        e.preventDefault();

        DepartmentService.updateDepartment(id, department)
            .then(() => {

                alert("Department Updated Successfully");

                navigate("/departments");

            })
            .catch((error) => {

                console.log(error);

                alert("Unable to Update Department");

            });

    };

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3>Edit Department</h3>

                </div>

                <div className="card-body">

                    <form onSubmit={updateDepartment}>

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

                                Description

                            </label>

                            <textarea
                                className="form-control"
                                rows="4"
                                name="description"
                                value={department.description}
                                onChange={handleChange}
                            />

                        </div>

                        <button
                            type="submit"
                            className="btn btn-warning">

                            Update Department

                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default EditDepartment;