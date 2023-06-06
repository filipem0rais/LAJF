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
      console.log('connect')
      return response.data
    })
    .catch(error => {
      throw error
    })
}
export function getUserData () {
  const token = localStorage.getItem('token')

  return axios.get('http://localhost:9090/api/users', {
    headers: {
      Authorization: token
    }
  })
    .then(response => {
      console.log('ok')
      console.log(response.data)
    })
    .catch(error => {
      console.log('CPT2')
      console.error(error)
    })
}
