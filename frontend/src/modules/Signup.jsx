import { useNavigate } from 'react-router-dom';
import AuthService from '../services/auth.service';
import { useState } from 'react';
import '../styles/Signup.css'

export default function Signup() {
  const redirect = useNavigate();
  const [entries, setEntries] = useState({
    username: "",
    email: "",
    password: "",
    passwordConfirm: "",
    roles: [],
  });


  const store = (e) => {
    const { name, value, type, checked } = e.target;
    
    if (name === "roles" && type === "checkbox") {
      setEntries((prev) => {
        const roles = checked
          ? [...prev.roles, value]
          : prev.roles.filter((role) => role !== value);
        return { ...prev, roles };
      });
    } else {
      setEntries({ ...entries, [name]: value });
    }
  };


  const handleSubmit = (event) => {
    event.preventDefault();

    if (entries.password !== entries.passwordConfirm) {
      alert("Passwords do not match.");
      return;
    }

    if (entries.roles.length === 0) {
      alert("Please select at least one role.");
      return;
    }

    try {
      AuthService.signup(entries.username.trim(), entries.email.trim(), entries.password.trim(), entries.roles)
        .then((res) => {
          alert("User registered successfully!");
          redirect("/login");
        })
        .catch((err) => {
          console.error(err);
          alert(err.response?.data?.message || "Signup failed.");
        });
    } catch (err) { console.log(err); }
  };

  return (
    <div className="signup-container">
      <form onSubmit={handleSubmit}>
        <h2>Signup</h2>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input type="text" id="username"
            name="username" value={entries.username}
            onChange={store} required
          />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input type="text" id="email"
            name="email" value={entries.email}
            onChange={store} required
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input type="password" id="password"
            name="password" value={entries.password}
            onChange={store} required
          />
        </div>

        <div className="form-group">
          <label htmlFor="passwordConfirm">Confirm <br /> Password:</label>
          <input type="password" id="passwordConfirm"
            name="passwordConfirm" value={entries.passwordConfirm}
            onChange={store} required
          />
        </div>

        <div className="form-group">
          <label>Roles:</label>
          <div>
            <label>
              <input type="checkbox"
                name="roles" value="user" checked={entries.roles.includes("user")}
                onChange={store}
              />
              User
            </label>
            <label>
              <input type="checkbox"
                name="roles" value="admin" checked={entries.roles.includes("admin")}
                onChange={store}
              />
              Admin
            </label>
          </div>
        </div>

        <button type="submit">Register</button>
      </form>
    </div>
  );
}
