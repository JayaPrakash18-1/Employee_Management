import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import EmployeeService from "../services/EmployeeService";
import DepartmentService from "../services/DepartmentService";

function Dashboard() {

    const [employeeCount, setEmployeeCount] = useState(0);
    const [departmentCount, setDepartmentCount] = useState(0);
    const [activeEmployees, setActiveEmployees] = useState(0);
    const [inactiveEmployees, setInactiveEmployees] = useState(0);

    useEffect(() => {

        EmployeeService.getAllEmployees()
            .then((response) => {

                const employees = response.data;

                setEmployeeCount(employees.length);

                setActiveEmployees(
                    employees.filter(emp => emp.status === "ACTIVE").length
                );

                setInactiveEmployees(
                    employees.filter(emp => emp.status === "INACTIVE").length
                );

            })
            .catch((error) => {
                console.log(error);
            });

        DepartmentService.getAllDepartments()
            .then((response) => {

                setDepartmentCount(response.data.length);

            })
            .catch((error) => {
                console.log(error);
            });

    }, []);

    return (

        <div className="container mt-5">

            <h2 className="text-center mb-5">
                Employee Management Dashboard
            </h2>

            <div className="row g-4">

                <div className="col-md-3">

                    <div className="card shadow border-0 text-center">

                        <div className="card-body">

                            <h5 className="text-muted">
                                Total Employees
                            </h5>

                            <h1 className="text-primary">
                                {employeeCount}
                            </h1>

                        </div>

                    </div>

                </div>

                <div className="col-md-3">

                    <div className="card shadow border-0 text-center">

                        <div className="card-body">

                            <h5 className="text-muted">
                                Departments
                            </h5>

                            <h1 className="text-success">
                                {departmentCount}
                            </h1>

                        </div>

                    </div>

                </div>

                <div className="col-md-3">

                    <div className="card shadow border-0 text-center">

                        <div className="card-body">

                            <h5 className="text-muted">
                                Active Employees
                            </h5>

                            <h1 className="text-success">
                                {activeEmployees}
                            </h1>

                        </div>

                    </div>

                </div>

                <div className="col-md-3">

                    <div className="card shadow border-0 text-center">

                        <div className="card-body">

                            <h5 className="text-muted">
                                Inactive Employees
                            </h5>

                            <h1 className="text-danger">
                                {inactiveEmployees}
                            </h1>

                        </div>

                    </div>

                </div>

            </div>

            <div className="text-center mt-5">

                <Link
                    to="/employees"
                    className="btn btn-primary btn-lg me-3">

                    Manage Employees

                </Link>

                <Link
                    to="/departments"
                    className="btn btn-success btn-lg">

                    Manage Departments

                </Link>

            </div>

        </div>

    );

}

export default Dashboard;