import api from "../api";

class AttendanceService {

    checkIn(attendance) {
        return api.post("/attendance/check-in", attendance);
    }

    checkOut(id) {
        return api.put(`/attendance/check-out/${id}`);
    }

    getAllAttendance() {
        return api.get("/attendance");
    }

    getAttendanceById(id) {
        return api.get(`/attendance/${id}`);
    }

    getAttendanceByEmployee(employeeId) {
        return api.get(`/attendance/employee/${employeeId}`);
    }

    getAttendanceByDate(date) {
        return api.get(`/attendance/date/${date}`);
    }

    deleteAttendance(id) {
        return api.delete(`/attendance/${id}`);
    }
}

export default new AttendanceService();