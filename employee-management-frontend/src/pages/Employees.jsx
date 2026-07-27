import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";

function Employees() {

    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        EmployeeService.getAllEmployees()
            .then((response) => {
                setEmployees(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    return (

        <div className="container mt-4">

            <h2>Employees</h2>
            <Link
        to="/add-employee"
        className="btn btn-success">

        Add Employee

    </Link>

            <table className="table table-bordered table-hover">

                <thead>

                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Department</th>
                    <th>Designation</th>
                    <th>Salary</th>
                </tr>

                </thead>

                <tbody>

                {employees.map(employee => (

                    <tr key={employee.employeeId}>

                        <td>{employee.employeeId}</td>

                        <td>
                            {employee.firstName} {employee.lastName}
                        </td>

                        <td>{employee.email}</td>

                        <td>{employee.departmentName}</td>

                        <td>{employee.designation}</td>

                        <td>{employee.salary}</td>

                    </tr>

                ))}

                </tbody>

            </table>

        </div>

    );

}

export default Employees;