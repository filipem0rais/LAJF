
import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/items'

export function getUnsoldItems () {
  const token = localStorage.getItem('token')
  return axios.get(BASE_URL + '/unsoldItems', { headers: { Authorization: token } })
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}