import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

const login = (username, password) => {
  return axios
    .post(API_URL + "login",
      { username, password, })
    .then((response) => {
      if (response.data.username) {
        localStorage.setItem("user",
          JSON.stringify(response.data));
        return response.data;
      }
    }).catch((error) => {
      console.log(error);
      throw error;
    })
};

/* By doing throw error inside .catch(),
you're allowing the caller to still catch
the error. Otherwise, the promise will
resolve with an error object, which is
unexpected behavior for most consumers.
*/

const signup = (username, email, password, roles = ["user"]) => {
  return axios
    .post(API_URL + "signup", {
      username,
      email,
      password,
      roles,
    })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error);
      throw error;
    });
};



const logout = () => {
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const getJwtHeader = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  return user?.token
    ? { headers: { Authorization: `Bearer ${user.token}` } }
    : { headers: {} };
};

const AuthService = {
  login,
  signup,
  logout,
  getJwtHeader,
  getCurrentUser,
}

export default AuthService;