import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/items'

export function postLot (lot) {
  const token = localStorage.getItem('token')
  return axios.post(BASE_URL, lot, { headers: { Authorization: token } })
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
