
import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/items'

export function getWonItemsNotPickedUp () {
  const token = localStorage.getItem('token')
  return axios.get(BASE_URL + '/getWonItemsNotPickedUp', { headers: { Authorization: token } })
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
export function getSoldItemsPickedUp () {
  const token = localStorage.getItem('token')
  return axios.get(BASE_URL + '/getSoldItemsPickedUp', { headers: { Authorization: token } })
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
export function getSoldItemsNotPickedUp () {
  const token = localStorage.getItem('token')
  return axios.get(BASE_URL + '/getSoldItemsNotPickedUp', { headers: { Authorization: token } })
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
export function getBiddedItems () {
  const token = localStorage.getItem('token')
  return axios.get(BASE_URL + '/getBiddedItems', { headers: { Authorization: token } })
    .then(response => {
      return response.data
    })
    .catch(error => {
      throw error
    })
}
