import api from "../api";

class LeaveService {

    applyLeave(leave) {
        return api.post("/leaves", leave);
    }

    getAllLeaves() {
        return api.get("/leaves");
    }

    getLeaveById(id) {
        return api.get(`/leaves/${id}`);
    }

    deleteLeave(id) {
        return api.delete(`/leaves/${id}`);
    }

    getLeavesByEmployee(employeeId) {
        return api.get(`/leaves/employee/${employeeId}`);
    }

    approveLeave(id) {
        return api.put(`/leaves/${id}/approve`);
    }

    rejectLeave(id) {
        return api.put(`/leaves/${id}/reject`);
    }
}

export default new LeaveService();