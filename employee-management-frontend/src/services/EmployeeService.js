import axios from "axios";

const BASE_URL = "http://localhost:8080/employees";

class EmployeeService {

    getAllEmployees() {
        return axios.get(BASE_URL);
    }

    createEmployee(employee) {
        return axios.post(BASE_URL, employee);
    }

    getEmployeeById(id) {
        return axios.get(`${BASE_URL}/${id}`);
    }

    updateEmployee(id, employee) {
        return axios.put(`${BASE_URL}/${id}`, employee);
    }

    deleteEmployee(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }

}

export default new EmployeeService();