import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/auth'

export function registerUser (userData) {
  return axios.post(BASE_URL + '/register', userData)
    .then(response => {
      return response
    })
    .catch(response => {
      return response.error
    })
}
export function loginUser (loginData) {
  return axios.post(BASE_URL + '/login', loginData)
    .then(response => {
      localStorage.setItem('token', response.data.token)
      return response
    })
    .catch(error => {
      return error.response
    })
}
export function getUserData () {
  if (!localStorage.getItem('token')) return Promise.resolve(null)
  const token = localStorage.getItem('token')

  return axios.get('http://localhost:9090/api/users', {
    headers: {
      Authorization: token
    }
  })
    .then(response => {
      localStorage.setItem('userId', response.data.idUser)
      return response.data
    })
    .catch(error => {
      return error.response
    })
}
export function logoutUser () {
  localStorage.removeItem('token')
  localStorage.removeItem('userId')
}
