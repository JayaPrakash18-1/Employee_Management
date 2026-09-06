import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { getCurrentUserRole } from "../auth";

function Navbar() {
const navigate = useNavigate();
const role=getCurrentUserRole();
const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
};
    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">

            <div className="container">

                <Link className="navbar-brand" to="/">
                    Employee Management System
                </Link>

                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav">

                    <span className="navbar-toggler-icon"></span>

                </button>

                <div
                    className="collapse navbar-collapse"
                    id="navbarNav">

                    <ul className="navbar-nav ms-auto">

                        <li className="nav-item">

                            <Link className="nav-link" to="/">
                                Dashboard
                            </Link>

                        </li>

                      {role=="ROLE_ADMIN" && ( 
                        <>
                        
                        <li className="nav-item">

                            <Link className="nav-link" to="/employees">
                                Employees
                            </Link>

                        </li>

                        <li className="nav-item">

                            <Link className="nav-link" to="/departments">
                                Departments
                            </Link>

                        </li>
                        </>
                        )}
                        <li className="nav-item">

                            <Link className="nav-link" to="/leaves"> 
                                Leaves
                            
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/attendance">
                                Attendance
                            </Link>
                        </li> 

                    </ul>
                    <button
    className="btn btn-danger"
    onClick={handleLogout}
>
    Logout
</button>

                </div>

            </div>

        </nav>

    );

}

export default Navbar;