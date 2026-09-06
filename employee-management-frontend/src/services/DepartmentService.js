import api from "../api";

const BASE_URL = "/departments";

class DepartmentService {

    getAllDepartments() {
        return api.get(BASE_URL);
    }

    createDepartment(department) {
        return api.post(BASE_URL, department);
    }

    getDepartmentById(id) {
        return api.get(`${BASE_URL}/${id}`);
    }

    updateDepartment(id, department) {
        return api.put(`${BASE_URL}/${id}`, department);
    }

    deleteDepartment(id) {
        return api.delete(`${BASE_URL}/${id}`);
    }
}

export default new DepartmentService();