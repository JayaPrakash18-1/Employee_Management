import { useEffect, useState } from "react";
import LeaveService from "../services/LeaveService";
import {Link} from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import { getCurrentUserRole } from "../auth";
function Leaves() {

    const [leaves, setLeaves] = useState([]);
    const [selectedEmployee, setSelectedEmployee] = useState("");
    const [employees, setEmployees] = useState([]);
    const role = getCurrentUserRole();

    useEffect(() => {
        getAllLeaves();
        getAllEmployees();
    }, []);

    const getAllLeaves = () => {

        LeaveService.getAllLeaves()
            .then((response) => {
                setLeaves(response.data);
            })
            .catch((error) => {
                console.log(error);
            });

    };
    const getAllEmployees = () => {

    EmployeeService.getAllEmployees(0, 1000)
        .then((response) => {
            setEmployees(response.data.content);
        })
        .catch((error) => {
            console.log(error);
        });
};
    const approveLeave = (id) => {

    if (!window.confirm("Are you sure you want to approve this leave?")) {
        return;
    }

    LeaveService.approveLeave(id)
        .then(() => {

            alert("Leave Approved Successfully");

            getAllLeaves();

        })
        .catch((error) => {

            console.log(error);
            alert("Unable to approve leave");

        });
};


const rejectLeave = (id) => {

    if (!window.confirm("Are you sure you want to reject this leave?")) {
        return;
    }

    LeaveService.rejectLeave(id)
        .then(() => {

            alert("Leave Rejected Successfully");

            getAllLeaves();

        })
        .catch((error) => {

            console.log(error);
            alert("Unable to reject leave");

        });
};

    return (
        <div className="container mt-4">

            <div className="d-flex justify-content-between align-items-center mb-3">

                                     <h2>Leave Management</h2>

                                    <Link
                                        to="/add-leave"
                                        className="btn btn-success"
                                    >
                                        Apply Leave
                                    </Link>

           </div>
{role=="ROLE_ADMIN" && <div className="row mb-3">

    <div className="col-md-4">

        <select
            className="form-select"
            value={selectedEmployee}
            onChange={(e) => setSelectedEmployee(e.target.value)}
        >

            <option value="">
                All Employees
            </option>

            {employees.map((employee) => (

                <option
                    key={employee.employeeId}
                    value={employee.employeeId}
                >
                    {employee.firstName} {employee.lastName}
                </option>

            ))}

        </select>

    </div>

    <div className="col-md-2">

        <button
            className="btn btn-primary"
            onClick={() => {

                if (!selectedEmployee) {
                    getAllLeaves();
                    return;
                }

                LeaveService
                    .getLeavesByEmployee(selectedEmployee)
                    .then((response) => {
                        setLeaves(response.data);
                    })
                    .catch((error) => {
                        console.log(error);
                    });

            }}
        >
            View Leaves
        </button>

    </div>

    <div className="col-md-2">

        <button
            className="btn btn-secondary"
            onClick={() => {

                setSelectedEmployee("");
                getAllLeaves();

            }}
        >
            Reset
        </button>

    </div>

</div>}

            <table className="table table-hover table-striped shadow">

                <thead>
                    <tr>
                        <th>Leave ID</th>
                        <th>Employee</th>
                        <th>Leave Type</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Reason</th>
                        <th>Status</th>
                        <th>Applied Date</th>
                        {role=="ROLE_ADMIN" && <th>Actions</th>}
                    </tr>
                </thead>

                <tbody>

                    {leaves.map((leave) => (

                        <tr key={leave.leaveId}>

                            <td>{leave.leaveId}</td>

                            <td>
                                {leave.employeeName}
                            </td>

                            <td>
                                {leave.leaveType}
                            </td>

                            <td>
                                {leave.startDate}
                            </td>

                            <td>
                                {leave.endDate}
                            </td>

                            <td>
                                {leave.reason}
                            </td>

                            <td>
                                {leave.status}
                            </td>

                            <td>
                                {leave.appliedDate}
                            </td>
                             <td>

                        {leave.status === "PENDING" && (
                            <>
                                {role=="ROLE_ADMIN"&& <button
                                    className="btn btn-success btn-sm me-2"
                                    onClick={() => approveLeave(leave.leaveId)}
                                >
                                    Approve 
                                </button>}

                                {role=="ROLE_ADMIN" && <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => rejectLeave(leave.leaveId)}
                                >
                                    Reject
                                </button>}
                            </>
                        )}

                          </td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
}

export default Leaves;