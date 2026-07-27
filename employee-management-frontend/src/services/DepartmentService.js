import axios from "axios";

const BASE_URL = "http://localhost:8080/departments";

class DepartmentService {

    getAllDepartments() {
        return axios.get(BASE_URL);
    }

}

export default new DepartmentService();