import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/bids'
export default {
  getLots () {
    return axios.get(BASE_URL)
      .then(response => response.data)
      .catch(error => {
        console.error(error)
        throw error
      })
  },
  getLatestLots (count) {
    const url = `${BASE_URL}/last/${count}`
    return axios.get(url)
      .then(response => response.data)
      .catch(error => {
        console.error(error)
        throw error
      })
  },
  getLotById (id) {
    const url = `${BASE_URL}/${id}`
    return axios.get(url)
      .then(response => response.data)
      .catch(error => {
        console.error(error)
        throw error
      })
  }
}
