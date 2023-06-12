import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/items'

export function postLot (articleData) {
  const token = localStorage.getItem('token')
  return axios.post(BASE_URL, articleData, { headers: { Authorization: token } })
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
