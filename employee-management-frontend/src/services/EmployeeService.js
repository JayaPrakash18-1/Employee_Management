import axios from "axios";

const BASE_URL = "http://localhost:8080/employees";

class EmployeeService {

    getAllEmployees(page, size) {
    return axios.get(`${BASE_URL}?page=${page}&size=${size}`);
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
    searchEmployeeByName(name) {
    return axios.get(`${BASE_URL}/search/name?name=${name}`);
}

searchEmployeeByEmail(email) {
    return axios.get(`${BASE_URL}/search/email?email=${email}`);
}
sortEmployees(field, direction) {
    return axios.get(`${BASE_URL}/sort?field=${field}&direction=${direction}`);
}
filterEmployees(
    departmentId,
    status,
    gender,
    minSalary,
    maxSalary,
    startDate,
    endDate
) {
    return axios.get(`${BASE_URL}/filter`, {
        params: {
            departmentId,
            status,
            gender,
            minSalary,
            maxSalary,
            startDate,
            endDate
        }
    });
}
}

export default new EmployeeService();