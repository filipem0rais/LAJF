import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/auth/'
// axios.defaults.headers.common['Authorization'] = `Bearer ${TOKEN}`;

export function registerUser (userData) {
  return axios.post(BASE_URL + 'register', userData)
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
export function loginUser (loginData) {
  return axios.post(BASE_URL + 'login', loginData)
    .then(response => {
      // eslint-disable-next-line no-unused-vars
      const TOKEN = response.data.token
      return response.data
    })
    .catch(error => {
      throw error
    })
}
