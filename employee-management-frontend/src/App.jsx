import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Employees from "./pages/Employees";
import AddEmployee from "./pages/AddEmployee";
import EditEmployee from "./pages/EditEmployee";

import Departments from "./pages/Departments";
import AddDepartment from "./pages/AddDepartment";
import EditDepartment from "./pages/EditDepartment";

import Navbar from "./components/Navbar";
import Leaves from "./pages/Leaves";
import AddLeave from "./pages/AddLeave";
import Attendance from "./pages/Attendance";
import Login from "./pages/login";
import ProtectedRoute from "./components/ProtectedRoute";
function App() {
    return (
        <BrowserRouter>

            <Navbar />

            <Routes>

    {/* Public route */}
    <Route path="/login" element={<Login />} />

    {/* Protected routes */}
    <Route
        path="/"
        element={
            <ProtectedRoute>
                <Dashboard />
            </ProtectedRoute>
        }
    />

    <Route
        path="/employees"
        element={
            <ProtectedRoute>
                <Employees />
            </ProtectedRoute>
        }
    />

    <Route
        path="/add-employee"
        element={
            <ProtectedRoute>
                <AddEmployee />
            </ProtectedRoute>
        }
    />

    <Route
        path="/edit-employee/:id"
        element={
            <ProtectedRoute>
                <EditEmployee />
            </ProtectedRoute>
        }
    />

    <Route
        path="/departments"
        element={
            <ProtectedRoute>
                <Departments />
            </ProtectedRoute>
        }
    />

    <Route
        path="/add-department"
        element={
            <ProtectedRoute>
                <AddDepartment />
            </ProtectedRoute>
        }
    />

    <Route
        path="/edit-department/:id"
        element={
            <ProtectedRoute>
                <EditDepartment />
            </ProtectedRoute>
        }
    />

    <Route
        path="/leaves"
        element={
            <ProtectedRoute>
                <Leaves />
            </ProtectedRoute>
        }
    />

    <Route
        path="/add-leave"
        element={
            <ProtectedRoute>
                <AddLeave />
            </ProtectedRoute>
        }
    />

    <Route
        path="/attendance"
        element={
            <ProtectedRoute>
                <Attendance />
            </ProtectedRoute>
        }
    />

</Routes>

        </BrowserRouter>
    );
}

export default App;