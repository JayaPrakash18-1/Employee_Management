import { BrowserRouter, Routes, Route } from "react-router-dom";

import Dashboard from "./pages/Dashboard";
import Employees from "./pages/Employees";
import AddEmployee from "./pages/AddEmployee";
import EditEmployee from "./pages/EditEmployee";

import Departments from "./pages/Departments";
import AddDepartment from "./pages/AddDepartment";
import EditDepartment from "./pages/EditDepartment";

import Navbar from "./components/Navbar";

function App() {
    return (
        <BrowserRouter>

            <Navbar />

            <Routes>

                <Route path="/" element={<Dashboard />} />

                <Route path="/employees" element={<Employees />} />
                <Route path="/add-employee" element={<AddEmployee />} />
                <Route path="/edit-employee/:id" element={<EditEmployee />} />

                <Route path="/departments" element={<Departments />} />
                <Route path="/add-department" element={<AddDepartment />} />
                <Route path="/edit-department/:id" element={<EditDepartment />} />

            </Routes>

        </BrowserRouter>
    );
}

export default App;