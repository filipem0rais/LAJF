import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/auth'

export function registerUser (userData) {
  return axios.post(BASE_URL + '/register', userData)
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
export function loginUser (loginData) {
  return axios.post(BASE_URL + '/login', loginData)
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
