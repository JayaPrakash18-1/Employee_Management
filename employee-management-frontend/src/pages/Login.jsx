import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await AuthService.login({
                email: email,
                password: password
            });

            // Store JWT
            localStorage.setItem("token", response.data);

            // Go to home page
            navigate("/");

        } catch (error) {
            console.log(error);
            setError("Invalid email or password");
        }
    };

    return (
        <div className="container mt-5" style={{ maxWidth: "400px" }}>

            <h2 className="text-center mb-4">
                Employee Login
            </h2>

            <form onSubmit={handleLogin}>

                <div className="mb-3">
                    <label>Email</label>

                    <input
                        type="email"
                        className="form-control"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label>Password</label>

                    <input
                        type="password"
                        className="form-control"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                {error && (
                    <div className="alert alert-danger">
                        {error}
                    </div>
                )}

                <button
                    type="submit"
                    className="btn btn-primary w-100"
                >
                    Login
                </button>

            </form>

        </div>
    );
}

export default Login;