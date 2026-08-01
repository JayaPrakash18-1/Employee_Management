import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import DepartmentService from "../services/DepartmentService";

function Departments() {

    const [departments, setDepartments] = useState([]);

    useEffect(() => {
        getAllDepartments();
    }, []);

    const getAllDepartments = () => {

        DepartmentService.getAllDepartments()
            .then((response) => {
                setDepartments(response.data);
            })
            .catch((error) => {
                console.log(error);
            });

    };

    const deleteDepartment = (id) => {

        if (window.confirm("Are you sure you want to delete this department?")) {

            DepartmentService.deleteDepartment(id)
                .then(() => {

                    alert("Department Deleted Successfully");

                    getAllDepartments();

                })
                .catch((error) => {
                    console.log(error);
                });

        }

    };

    return (

        <div className="container mt-4">

            <div className="d-flex justify-content-between align-items-center mb-3">

                <h2>Departments</h2>

                <Link
                    to="/add-department"
                    className="btn btn-success">

                    Add Department

                </Link>

            </div>

            <table className="table table-bordered table-hover">

                <thead>

                <tr>

                    <th>ID</th>
                    <th>Department Name</th>
                    <th>Description</th>
                    <th>Actions</th>

                </tr>

                </thead>

                <tbody>

                {

                    departments.map((department) => (

                        <tr key={department.departmentId}>

                            <td>{department.departmentId}</td>

                            <td>{department.departmentName}</td>

                            <td>{department.description}</td>

                            <td>

                                <Link
                                    className="btn btn-warning btn-sm me-2"
                                    to={`/edit-department/${department.departmentId}`}>

                                    Edit

                                </Link>

                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => deleteDepartment(department.departmentId)}>

                                    Delete

                                </button>

                            </td>

                        </tr>

                    ))

                }

                </tbody>

            </table>

        </div>

    );

}

export default Departments;