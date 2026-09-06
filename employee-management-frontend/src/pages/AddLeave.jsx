import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import LeaveService from "../services/LeaveService";
import EmployeeService from "../services/EmployeeService";

function AddLeave() {

    const navigate = useNavigate();

    const [employees, setEmployees] = useState([]);

    const [leave, setLeave] = useState({
        employeeId: "",
        leaveType: "SICK",
        startDate: "",
        endDate: "",
        reason: ""
    });

    useEffect(() => {
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

    const handleChange = (e) => {

        const { name, value } = e.target;

        setLeave({
            ...leave,
            [name]: value
        });
    };

    const saveLeave = (e) => {

        e.preventDefault();

        LeaveService.applyLeave(leave)
            .then(() => {

                alert("Leave Applied Successfully");

                navigate("/leaves");

            })
            .catch((error) => {

                console.log(error);

                alert("Unable to Apply Leave");

            });
    };

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">
                    <h3>Apply Leave</h3>
                </div>

                <div className="card-body">

                    <form onSubmit={saveLeave}>

                        {/* Employee */}

                        <div className="mb-3">

                            <label className="form-label">
                                Employee
                            </label>


                        </div>

                        {/* Leave Type */}

                        <div className="mb-3">

                            <label className="form-label">
                                Leave Type
                            </label>

                            <select
                                className="form-select"
                                name="leaveType"
                                value={leave.leaveType}
                                onChange={handleChange}
                            >

                                <option value="SICK">
                                    Sick Leave
                                </option>

                                <option value="CASUAL">
                                    Casual Leave
                                </option>

                                <option value="EARNED">
                                    Earned Leave
                                </option>

                            </select>

                        </div>

                        {/* Start Date */}

                        <div className="mb-3">

                            <label className="form-label">
                                Start Date
                            </label>

                            <input
                                type="date"
                                className="form-control"
                                name="startDate"
                                value={leave.startDate}
                                onChange={handleChange}
                                required
                            />

                        </div>

                        {/* End Date */}

                        <div className="mb-3">

                            <label className="form-label">
                                End Date
                            </label>

                            <input
                                type="date"
                                className="form-control"
                                name="endDate"
                                value={leave.endDate}
                                onChange={handleChange}
                                required
                            />

                        </div>

                        {/* Reason */}

                        <div className="mb-3">

                            <label className="form-label">
                                Reason
                            </label>

                            <textarea
                                className="form-control"
                                rows="4"
                                name="reason"
                                value={leave.reason}
                                onChange={handleChange}
                            />

                        </div>

                        <button
                            type="submit"
                            className="btn btn-success me-2"
                        >
                            Apply Leave
                        </button>

                        <button
                            type="button"
                            className="btn btn-secondary"
                            onClick={() => navigate("/leaves")}
                        >
                            Cancel
                        </button>

                    </form>

                </div>

            </div>

        </div>
    );
}

export default AddLeave;