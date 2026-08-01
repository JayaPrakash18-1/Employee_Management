import axios from "axios";

const BASE_URL = "http://localhost:8080/departments";

class DepartmentService {

    getAllDepartments() {
        return axios.get(BASE_URL);
    }

    createDepartment(department) {
        return axios.post(BASE_URL, department);
    }

    getDepartmentById(id) {
        return axios.get(`${BASE_URL}/${id}`);
    }

    updateDepartment(id, department) {
        return axios.put(`${BASE_URL}/${id}`, department);
    }

    deleteDepartment(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }

}

export default new DepartmentService();