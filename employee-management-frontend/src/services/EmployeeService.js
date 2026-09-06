import api from "../api";

const BASE_URL = "/employees";

class EmployeeService {

    getAllEmployees(page, size) {
        return api.get(`${BASE_URL}?page=${page}&size=${size}`);
    }

    createEmployee(employee) {
        return api.post(BASE_URL, employee);
    }

    getEmployeeById(id) {
        return api.get(`${BASE_URL}/${id}`);
    }

    updateEmployee(id, employee) {
        return api.put(`${BASE_URL}/${id}`, employee);
    }

    deleteEmployee(id) {
        return api.delete(`${BASE_URL}/${id}`);
    }

    searchEmployeeByName(name) {
        return api.get(`${BASE_URL}/search/name?name=${name}`);
    }

    searchEmployeeByEmail(email) {
        return api.get(`${BASE_URL}/search/email?email=${email}`);
    }

    sortEmployees(field, direction) {
        return api.get(
            `${BASE_URL}/sort?field=${field}&direction=${direction}`
        );
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
        return api.get(`${BASE_URL}/filter`, {
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