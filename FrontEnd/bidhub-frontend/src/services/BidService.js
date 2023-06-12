
import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/bids'

export function postBid (bid) {
  const token = localStorage.getItem('token')
  return axios.post(BASE_URL, bid, {
    headers: {
      Authorization: token
    }
  })
    .then(() => {
      console.log('cest placé')
    })
    .catch(error => {
      console.error(error)
    })
}
