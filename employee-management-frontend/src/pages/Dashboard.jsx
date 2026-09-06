import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import EmployeeService from "../services/EmployeeService";
import DepartmentService from "../services/DepartmentService";
import { getCurrentUserRole } from "../auth";
import { jwtDecode } from "jwt-decode";

function Dashboard() {

    const navigate = useNavigate();

    const [employeeCount, setEmployeeCount] = useState(0);
    const [departmentCount, setDepartmentCount] = useState(0);
    const [activeEmployees, setActiveEmployees] = useState(0);
    const [inactiveEmployees, setInactiveEmployees] = useState(0);

    const role = getCurrentUserRole();

    // Get email from JWT
    const token = localStorage.getItem("token");

    let email = "";

    if (token) {
        try {
            const decoded = jwtDecode(token);
            email = decoded.sub;
        } catch (error) {
            console.log("Invalid token");
        }
    }

    useEffect(() => {

        EmployeeService.getAllEmployees()
            .then((response) => {

                const employees = response.data;

                setEmployeeCount(employees.length);

                setActiveEmployees(
                    employees.filter(
                        emp => emp.status === "ACTIVE"
                    ).length
                );

                setInactiveEmployees(
                    employees.filter(
                        emp => emp.status === "INACTIVE"
                    ).length
                );

            })
            .catch((error) => {
                console.log(error);
            });


        DepartmentService.getAllDepartments()
            .then((response) => {

                setDepartmentCount(
                    response.data.length
                );

            })
            .catch((error) => {
                console.log(error);
            });

    }, []);


    return (
        <div className="container mt-4">

            <h2>Dashboard</h2>

            {/* My Information */}
            <div
                className="card mt-4 p-4"
                style={{ maxWidth: "500px" }}
            >
                <h4>My Information</h4>

                <p className="mt-3">
                    <strong>Email:</strong> {email}
                </p>

                <p>
                    <strong>Role:</strong> {role}
                </p>
            </div>


            {/* ADMIN DASHBOARD */}
            {role === "ROLE_ADMIN" ? (
                <>
                    <h4 className="mt-4">
                        Admin Dashboard
                    </h4>

                    <div className="row mt-3">

                        <div className="col-md-4">
                            <div className="card p-3">

                                <h5>Employees</h5>

                                <p>
                                    Total Employees:
                                    {" "}{employeeCount}
                                </p>

                                <p>
                                    Active:
                                    {" "}{activeEmployees}
                                </p>

                                <p>
                                    Inactive:
                                    {" "}{inactiveEmployees}
                                </p>

                                <button
                                    className="btn btn-primary"
                                    onClick={() =>
                                        navigate("/employees")
                                    }
                                >
                                    View Employees
                                </button>

                            </div>
                        </div>


                        <div className="col-md-4">
                            <div className="card p-3">

                                <h5>Departments</h5>

                                <p>
                                    Total Departments:
                                    {" "}{departmentCount}
                                </p>

                                <button
                                    className="btn btn-primary"
                                    onClick={() =>
                                        navigate("/departments")
                                    }
                                >
                                    View Departments
                                </button>

                            </div>
                        </div>


                        <div className="col-md-4">
                            <div className="card p-3">

                                <h5>Leaves</h5>

                                <p>
                                    Manage employee leaves
                                </p>

                                <button
                                    className="btn btn-primary"
                                    onClick={() =>
                                        navigate("/leaves")
                                    }
                                >
                                    View Leaves
                                </button>

                            </div>
                        </div>

                    </div>
                </>
            ) : (

                /* EMPLOYEE DASHBOARD */

                <>
                    <h4 className="mt-4">
                        Employee Dashboard
                    </h4>

                    <div className="row mt-3">

                        <div className="col-md-6">
                            <div className="card p-3">

                                <h5>My Leaves</h5>

                                <p>
                                    View and apply for leave
                                </p>

                                <button
                                    className="btn btn-primary"
                                    onClick={() =>
                                        navigate("/leaves")
                                    }
                                >
                                    View Leaves
                                </button>

                            </div>
                        </div>


                        <div className="col-md-6">
                            <div className="card p-3">

                                <h5>My Attendance</h5>

                                <p>
                                    View your attendance
                                </p>

                                <button
                                    className="btn btn-primary"
                                    onClick={() =>
                                        navigate("/attendance")
                                    }
                                >
                                    View Attendance
                                </button>

                            </div>
                        </div>

                    </div>
                </>
            )}

        </div>
    );
}

export default Dashboard;