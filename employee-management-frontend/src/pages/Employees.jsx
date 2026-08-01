
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import DepartmentService from "../services/DepartmentService";

function Employees() {

    const [employees, setEmployees] = useState([]);
    const [searchText, setSearchText] = useState("");
    const [searchType, setSearchType] = useState("name");  
    const [sortField, setSortField] = useState("firstName");
    const [sortDirection, setSortDirection] = useState("asc");
    const [departments, setDepartments] = useState([]);
    const [departmentId, setDepartmentId] = useState("");
    const [status, setStatus] = useState("");
    const [gender, setGender] = useState("");
    const [minSalary, setMinSalary] = useState("");
    const [maxSalary, setMaxSalary] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize] = useState(1);
    const [totalPages, setTotalPages] = useState(0);

   useEffect(() => {
    getAllEmployees();
}, [currentPage]);

useEffect(() => {
    getAllDepartments();
}, []);

    const getAllEmployees = () => {

    EmployeeService.getAllEmployees(currentPage, pageSize)
        .then((response) => {

            setEmployees(response.data.content);
            setTotalPages(response.data.totalPages);

        })
        .catch((error) => {
            console.log(error);
        });

};
const nextPage = () => {

    if (currentPage < totalPages - 1) {
        setCurrentPage(currentPage + 1);
    }

};

const previousPage = () => {

    if (currentPage > 0) {
        setCurrentPage(currentPage - 1);
    }

};
const changePage = (page) => {
    setCurrentPage(page);
};

    const getAllDepartments = () => {
        DepartmentService.getAllDepartments()
            .then((response) => {
                setDepartments(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const deleteEmployee = (id) => {
        if(window.confirm("Are you sure you want to delete this employee?")){
            EmployeeService.deleteEmployee(id)
                .then(() => {
                    alert("Employee Deleted Successfully");
                    getAllEmployees();
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    };

    const searchEmployee = () => {
        if (searchText.trim() === "") {
            getAllEmployees();
            return;
        }

        if (searchType === "name") {
            EmployeeService.searchEmployeeByName(searchText)
                .then((response) => {
                    setEmployees(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        } else {
            EmployeeService.searchEmployeeByEmail(searchText)
                .then((response) => {
                    setEmployees(response.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    };

    const sortEmployees = () => {
        EmployeeService.sortEmployees(sortField, sortDirection)
            .then((response) => {
                setEmployees(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const applyFilters = () => {
        EmployeeService.filterEmployees(
            departmentId || null,
            status || null,
            gender || null,
            minSalary || null,
            maxSalary || null,
            startDate || null,
            endDate || null
        )
            .then((response) => {
                setEmployees(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="container-fluid px-4 py-4">
            <div className="row">
                {/* Left Side: Controls Panel (taking 4 columns for side-by-side spacing) */}
                <div className="col-lg-4 col-md-5 mb-4">
                    {/* Top Row: Search and Sort Side-by-Side */}
                    <div className="row g-2 mb-3">
                        {/* Search Card */}
                        <div className="col-6">
                            <div className="card shadow-sm h-100">
                                <div className="card-header bg-primary text-white py-2">
                                    <h6 className="mb-0 fs-6">Search</h6>
                                </div>
                                <div className="card-body p-2 d-flex flex-column justify-content-between">
                                    <div>
                                        <label className="form-label mb-1">Search By</label>
                                        <select
                                            className="form-select form-select-sm mb-2"
                                            value={searchType}
                                            onChange={(e) => setSearchType(e.target.value)}
                                        >
                                            <option value="name">Name</option>
                                            <option value="email">Email</option>
                                        </select>

                                        <label className="form-label mb-1">Keyword</label>
                                        <input
                                            className="form-control form-control-sm mb-2"
                                            value={searchText}
                                            onChange={(e) => setSearchText(e.target.value)}
                                            placeholder="Keyword..."
                                        />
                                    </div>

                                    <div className="d-flex gap-1 mt-2">
                                        <button
                                            className="btn btn-primary btn-sm flex-fill"
                                            onClick={searchEmployee}
                                        >
                                            Search
                                        </button>
                                        <button
                                            className="btn btn-outline-secondary btn-sm flex-fill"
                                            onClick={() => {
                                                setSearchText("");
                                                getAllEmployees();
                                            }}
                                        >
                                            Reset
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        {/* Sort Card */}
                        <div className="col-6">
                            <div className="card shadow-sm h-100">
                                <div className="card-header bg-info text-white py-2">
                                    <h6 className="mb-0 fs-6">Sorting</h6>
                                </div>
                                <div className="card-body p-2 d-flex flex-column justify-content-between">
                                    <div>
                                        <label className="form-label mb-1">Sort By</label>
                                        <select
                                            className="form-select form-select-sm mb-2"
                                            value={sortField}
                                            onChange={(e) => setSortField(e.target.value)}
                                        >
                                            <option value="firstName">Name</option>
                                            <option value="salary">Salary</option>
                                            <option value="joiningDate">Joining Date</option>
                                        </select>

                                        <label className="form-label mb-1">Order</label>
                                        <select
                                            className="form-select form-select-sm mb-2"
                                            value={sortDirection}
                                            onChange={(e) => setSortDirection(e.target.value)}
                                        >
                                            <option value="asc">Ascending</option>
                                            <option value="desc">Descending</option>
                                        </select>
                                    </div>

                                    <button
                                        className="btn btn-info btn-sm text-white w-100 mt-2"
                                        onClick={sortEmployees}
                                    >
                                        Sort
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Bottom: Filter Card (Spans across the full sidebar width) */}
                    <div className="card shadow-sm">
                        <div className="card-header bg-success text-white py-2">
                            <h6 className="mb-0 fs-6">Filters</h6>
                        </div>
                        <div className="card-body p-3">
                            <div className="row g-2">
                                <div className="col-md-6 mb-2">
                                    <label className="form-label mb-1">Department</label>
                                    <select
                                        className="form-select form-select-sm"
                                        value={departmentId}
                                        onChange={(e) => setDepartmentId(e.target.value)}
                                    >
                                        <option value="">All Departments</option>
                                        {departments.map((department) => (
                                            <option
                                                key={department.departmentId}
                                                value={department.departmentId}
                                            >
                                                {department.departmentName}
                                            </option>
                                        ))}
                                    </select>
                                </div>

                                <div className="col-md-6 mb-2">
                                    <label className="form-label mb-1">Status</label>
                                    <select
                                        className="form-select form-select-sm"
                                        value={status}
                                        onChange={(e) => setStatus(e.target.value)}
                                    >
                                        <option value="">All Status</option>
                                        <option value="ACTIVE">ACTIVE</option>
                                        <option value="INACTIVE">INACTIVE</option>
                                    </select>
                                </div>

                                <div className="col-md-6 mb-2">
                                    <label className="form-label mb-1">Gender</label>
                                    <select
                                        className="form-select form-select-sm"
                                        value={gender}
                                        onChange={(e) => setGender(e.target.value)}
                                    >
                                        <option value="">All Gender</option>
                                        <option value="MALE">Male</option>
                                        <option value="FEMALE">Female</option>
                                    </select>
                                </div>

                                <div className="col-md-6 mb-2">
                                    <label className="form-label mb-1">Min Salary</label>
                                    <input
                                        type="number"
                                        className="form-control form-control-sm"
                                        value={minSalary}
                                        onChange={(e) => setMinSalary(e.target.value)}
                                        placeholder="Min"
                                    />
                                </div>

                                <div className="col-md-6 mb-3">
                                    <label className="form-label mb-1">Max Salary</label>
                                    <input
                                        type="number"
                                        className="form-control form-control-sm"
                                        value={maxSalary}
                                        onChange={(e) => setMaxSalary(e.target.value)}
                                        placeholder="Max"
                                    />
                                </div>

                                <div className="col-md-6 mb-3">
                                    <label className="form-label mb-1">Joining From</label>
                                    <input
                                        type="date"
                                        className="form-control form-control-sm"
                                        value={startDate}
                                        onChange={(e) => setStartDate(e.target.value)}
                                    />
                                </div>

                                <div className="col-md-12 mb-3">
                                    <label className="form-label mb-1">Joining To</label>
                                    <input
                                        type="date"
                                        className="form-control form-control-sm"
                                        value={endDate}
                                        onChange={(e) => setEndDate(e.target.value)}
                                    />
                                </div>
                            </div>

                            <div className="d-flex gap-2">
                                <button
                                    className="btn btn-success btn-sm flex-fill"
                                    onClick={applyFilters}
                                >
                                    Apply Filters
                                </button>
                                <button
                                    className="btn btn-outline-secondary btn-sm flex-fill"
                                    onClick={() => {
                                        setDepartmentId("");
                                        setStatus("");
                                        setGender("");
                                        setMinSalary("");
                                        setMaxSalary("");
                                        setStartDate("");
                                        setEndDate("");
                                        getAllEmployees();
                                    }}
                                >
                                    Reset Filters
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Right Side: Data Panel */}
                <div className="col-lg-8 col-md-7">
                    <div className="card shadow-sm border-0">
                        <div className="card-body p-4">
                            <div className="d-flex justify-content-between align-items-center mb-4">
                                <h3 className="mb-0 fw-bold" style={{ color: "var(--text-dark)", letterSpacing: "-0.5px" }}>
                                    Employees List
                                </h3>
                                <Link
                                    to="/add-employee"
                                    className="btn btn-success d-flex align-items-center gap-2"
                                >
                                    Add Employee
                                </Link>
                            </div>

                            <div className="table-responsive">
                                <table className="table table-hover table-striped align-middle">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Department</th>
                                            <th>Designation</th>
                                            <th>Salary</th>
                                            <th className="text-end">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {employees.length > 0 ? (
                                            employees.map((employee) => (
                                                <tr key={employee.employeeId}>
                                                    <td >{employee.employeeId}</td>
                                                    <td>
                                                        <div className="fw-semibold text-dark">
                                                            {employee.firstName} {employee.lastName}
                                                        </div>
                                                    </td>
                                                    <td>{employee.email}</td>
                                                    <td>
                                                        
                                                            {employee.departmentName || "Unassigned"}
                                                        
                                                    </td>
                                                    <td>{employee.designation}</td>
                                                    <td className="fw-bold" style={{ color: "var(--primary-color)" }}>
                                                        {employee.salary}
                                                    </td>
                                                    <td className="text-end">
                                                        <Link
                                                            className="btn btn-warning btn-sm me-2 text-white"
                                                            to={`/edit-employee/${employee.employeeId}`}
                                                        >
                                                            Edit
                                                        </Link>
                                                        <button
                                                            className="btn btn-danger btn-sm"
                                                            onClick={() => deleteEmployee(employee.employeeId)}
                                                        >
                                                            Delete
                                                        </button>
                                                    </td>
                                                </tr>
                                            ))
                                        ) : (
                                            <tr>
                                                <td colSpan="7" className="text-center py-5 text-muted">
                                                    <div className="fs-5 fw-semibold mb-1">No employees found</div>
                                                    <small>Try modifying your search or filter options</small>
                                                </td>
                                            </tr>
                                        )}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div className="d-flex justify-content-center align-items-center mt-3">

    <button
        className="btn btn-outline-primary me-2"
        onClick={previousPage}
        disabled={currentPage === 0}
    >
        Previous
    </button>

    {

        [...Array(totalPages).keys()].map((page) => (

            <button
                key={page}
                className={`btn me-2 ${
                    currentPage === page
                        ? "btn-primary"
                        : "btn-outline-primary"
                }`}
                onClick={() => changePage(page)}
            >
                {page + 1}
            </button>

        ))

    }

    <button
        className="btn btn-outline-primary"
        onClick={nextPage}
        disabled={currentPage === totalPages - 1}
    >
        Next
    </button>

</div>
                </div>
            </div>
        </div>
    );
}

export default Employees;