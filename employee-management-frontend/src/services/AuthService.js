import axios from "axios";

const BASE_URL = "http://localhost:8080/auth";

class AuthService {

    login(credentials) {
        return axios.post(`${BASE_URL}/login`, credentials);
    }
}

export default new AuthService();