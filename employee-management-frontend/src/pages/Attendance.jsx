import { useEffect, useState } from "react";
import AttendanceService from "../services/AttendanceService";
import EmployeeService from "../services/EmployeeService";
import { getCurrentUserRole } from "../auth";

function Attendance() {

    const [attendance, setAttendance] = useState([]);
    const [employees, setEmployees] = useState([]);
    const [filterEmployee, setFilterEmployee] = useState("");
    const [filterDate, setFilterDate] = useState("");
    const [filterStatus, setFilterStatus] = useState("");
    const [employeeId, setEmployeeId] = useState("");
   const role=getCurrentUserRole();
    useEffect(() => {
        getAllAttendance();
        getEmployees();
    }, []);
const getEmployees = () => {

    EmployeeService.getAllEmployees(0, 1000)
        .then((response) => {
            setEmployees(response.data.content);
        })
        .catch((error) => {
            console.log(error);
        });
};
    const getAllAttendance = () => {

        AttendanceService.getAllAttendance()
            .then((response) => {
                setAttendance(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };
    const checkIn = () => {


    const attendance = {
        attendanceDate: new Date().toISOString().split("T")[0]
    };

    AttendanceService.checkIn(attendance)
        .then(() => {

            alert("Check In Successful");

            setEmployeeId("");

            getAllAttendance();

        })
        .catch((error) => {

            console.log(error);

            alert(
                error.response?.data?.message ||
                "Unable to Check In"
            );

        });
};

    const checkOut = (id) => {

        if (!window.confirm("Are you sure you want to check out?")) {
            return;
        }

        AttendanceService.checkOut(id)
            .then(() => {

                alert("Check Out Successful");

                getAllAttendance();

            })
            .catch((error) => {

                console.log(error);
                alert("Unable to Check Out");

            });
    };
    const applyFilters = () => {

    if (filterEmployee) {

        AttendanceService.getAttendanceByEmployee(filterEmployee)
            .then((response) => {

                let data = response.data;

                if (filterStatus) {
                    data = data.filter(
                        (record) => record.status === filterStatus
                    );
                }

                setAttendance(data);
            })
            .catch((error) => {
                console.log(error);
            });

        return;
    }

    if (filterDate) {

        AttendanceService.getAttendanceByDate(filterDate)
            .then((response) => {

                let data = response.data;

                if (filterStatus) {
                    data = data.filter(
                        (record) => record.status === filterStatus
                    );
                }

                setAttendance(data);
            })
            .catch((error) => {
                console.log(error);
            });

        return;
    }

    getAllAttendance();
};
const resetFilters = () => {

    setFilterEmployee("");
    setFilterDate("");
    setFilterStatus("");

    getAllAttendance();
};

    return (

        <div className="container mt-4">

            <div className="d-flex justify-content-between align-items-center mb-3">

                <h2>Attendance Management</h2>

            </div>
                        <div className="row mb-3">

                <div className="col-md-4">

                </div>

                <div className="col-md-2">

                    <button
                        className="btn btn-success w-100"
                        onClick={checkIn}
                    >
                        Check In
                    </button>

                </div>

            </div>
       {role=="ROLE_ADMIN" && <div className="row mb-3">

            <div className="col-md-3">

                <select
                    className="form-select"
                    value={filterEmployee}
                    onChange={(e) => setFilterEmployee(e.target.value)}
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

            <div className="col-md-3">

                <input
                    type="date"
                    className="form-control"
                    value={filterDate}
                    onChange={(e) => setFilterDate(e.target.value)}
                />

            </div>

            <div className="col-md-3">

                <select
                    className="form-select"
                    value={filterStatus}
                    onChange={(e) => setFilterStatus(e.target.value)}
                >

                    <option value="">
                        All Status
                    </option>

                    <option value="PRESENT">
                        Present
                    </option>

                    <option value="ABSENT">
                        Absent
                    </option>

                    <option value="HALF_DAY">
                        Half Day
                    </option>

                </select>

            </div>

            <div className="col-md-3">

                <button
                    className="btn btn-primary me-2"
                    onClick={applyFilters}
                >
                    Filter
                </button>

                <button
                    className="btn btn-secondary"
                    onClick={resetFilters}
                >
                    Reset
                </button>

            </div>

        </div>}

            <table className="table table-hover table-striped shadow">

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Employee</th>
                        <th>Date</th>
                        <th>Check In</th>
                        <th>Check Out</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>

                    {attendance.map((record) => (

                        <tr key={record.attendanceId}>

                            <td>
                                {record.attendanceId}
                            </td>

                            <td>
                                {record.employeeName}
                            </td>

                            <td>
                                {record.attendanceDate}
                            </td>

                            <td>
                                {record.checkInTime || "-"}
                            </td>

                            <td>
                                {record.checkOutTime || "-"}
                            </td>

                            <td>
                                {record.status}
                            </td>

                            <td>

                                {record.checkOutTime === null && (

                                    <button
                                        className="btn btn-warning btn-sm"
                                        onClick={() =>
                                            checkOut(record.attendanceId)
                                        }
                                    >
                                        Check Out
                                    </button>

                                )}

                            </td>

                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
}

export default Attendance;