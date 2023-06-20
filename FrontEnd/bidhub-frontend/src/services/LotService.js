import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/items'

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
export function unSoldItems () {
  const token = localStorage.getItem('token')

  return axios.get('http://localhost:9090/api/items/unsoldItems', {
    headers: {
      Authorization: token
    }
  })
    .then(response => {
      return response.data
    })
    .catch(error => {
      console.error(error)
    })
}
