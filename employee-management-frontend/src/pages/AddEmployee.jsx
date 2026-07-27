import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import DepartmentService from "../services/DepartmentService";

function AddEmployee() {

    const navigate = useNavigate();

    const [departments, setDepartments] = useState([]);

    const [employee, setEmployee] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        gender: "MALE",
        dateOfBirth: "",
        bloodGroup: "O_POSITIVE",
        address: "",
        designation: "",
        joiningDate: "",
        salary: "",
        status: "ACTIVE",
        departmentId: ""
    });

    useEffect(() => {

        DepartmentService.getAllDepartments()
            .then((response) => {
                setDepartments(response.data);
            })
            .catch((error) => console.log(error));

    }, []);

    const handleChange = (e) => {

        const { name, value } = e.target;

        setEmployee({
            ...employee,
            [name]: value
        });

    };

    const saveEmployee = (e) => {

        e.preventDefault();

        EmployeeService.createEmployee(employee)
            .then(() => {

                alert("Employee Added Successfully");

                navigate("/employees");

            })
            .catch((error) => {

                console.log(error);

                alert("Unable to Save Employee");

            });

    };

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3>Add Employee</h3>

                </div>

                <div className="card-body">

                    <form onSubmit={saveEmployee}>

                        <div className="row">

                            <div className="col-md-6 mb-3">
                                <label>First Name</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="firstName"
                                    value={employee.firstName}
                                    onChange={handleChange}
                                />
                            </div>

                            <div className="col-md-6 mb-3">
                                <label>Last Name</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="lastName"
                                    value={employee.lastName}
                                    onChange={handleChange}
                                />
                            </div>

                            <div className="col-md-6 mb-3">
                                <label>Email</label>

                                <input
                                    type="email"
                                    className="form-control"
                                    name="email"
                                    value={employee.email}
                                    onChange={handleChange}
                                />
                            </div>

                            <div className="col-md-6 mb-3">
                                <label>Phone Number</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="phoneNumber"
                                    value={employee.phoneNumber}
                                    onChange={handleChange}
                                />
                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Gender</label>

                                <select
                                    className="form-select"
                                    name="gender"
                                    value={employee.gender}
                                    onChange={handleChange}
                                >

                                    <option value="MALE">MALE</option>
                                    <option value="FEMALE">FEMALE</option>

                                </select>

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Blood Group</label>

                                <select
                                    className="form-select"
                                    name="bloodGroup"
                                    value={employee.bloodGroup}
                                    onChange={handleChange}
                                >

                                    <option value="A_POSITIVE">A_POSITIVE</option>
                                    <option value="A_NEGATIVE">A_NEGATIVE</option>
                                    <option value="B_POSITIVE">B_POSITIVE</option>
                                    <option value="B_NEGATIVE">B_NEGATIVE</option>
                                    <option value="AB_POSITIVE">AB_POSITIVE</option>
                                    <option value="AB_NEGATIVE">AB_NEGATIVE</option>
                                    <option value="O_POSITIVE">O_POSITIVE</option>
                                    <option value="O_NEGATIVE">O_NEGATIVE</option>

                                </select>

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Date Of Birth</label>

                                <input
                                    type="date"
                                    className="form-control"
                                    name="dateOfBirth"
                                    value={employee.dateOfBirth}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Joining Date</label>

                                <input
                                    type="date"
                                    className="form-control"
                                    name="joiningDate"
                                    value={employee.joiningDate}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Department</label>

                                <select
                                    className="form-select"
                                    name="departmentId"
                                    value={employee.departmentId}
                                    onChange={handleChange}
                                >

                                    <option value="">Select Department</option>

                                    {
                                        departments.map((department) => (

                                            <option
                                                key={department.departmentId}
                                                value={department.departmentId}
                                            >

                                                {department.departmentName}

                                            </option>

                                        ))
                                    }

                                </select>

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Designation</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="designation"
                                    value={employee.designation}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Salary</label>

                                <input
                                    type="number"
                                    className="form-control"
                                    name="salary"
                                    value={employee.salary}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Status</label>

                                <select
                                    className="form-select"
                                    name="status"
                                    value={employee.status}
                                    onChange={handleChange}
                                >

                                    <option value="ACTIVE">ACTIVE</option>
                                    <option value="INACTIVE">INACTIVE</option>

                                </select>

                            </div>

                            <div className="col-md-12 mb-3">

                                <label>Address</label>

                                <textarea
                                    className="form-control"
                                    rows="3"
                                    name="address"
                                    value={employee.address}
                                    onChange={handleChange}
                                />

                            </div>

                        </div>

                        <button
                            type="submit"
                            className="btn btn-success">

                            Save Employee

                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default AddEmployee;