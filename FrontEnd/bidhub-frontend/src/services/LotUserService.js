import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/items'
export default {
  TOKEN: localStorage.getItem('token'),
  getWonItemsNotPickedUp () {
    return axios.get(BASE_URL + '/getWonItemsNotPickedUp', { headers: { Authorization: this.TOKEN } })
      .then(response => {
        return response
      })
      .catch(error => {
        return error.response
      })
  },
  getSoldItemsPickedUp () {
    return axios.get(BASE_URL + '/getSoldItemsPickedUp', { headers: { Authorization: this.TOKEN } })
      .then(response => {
        return response
      })
      .catch(error => {
        return error.response
      })
  },
  getSoldItemsNotPickedUp () {
    return axios.get(BASE_URL + '/getSoldItemsNotPickedUp', { headers: { Authorization: this.TOKEN } })
      .then(response => {
        return response
      })
      .catch(error => {
        return error.response
      })
  },
  getBiddedItems () {
    return axios.get(BASE_URL + '/getBiddedItems', { headers: { Authorization: this.TOKEN } })
      .then(response => {
        return response
      })
      .catch(error => {
        return error.response
      })
  },
  getItems () {
    return axios.get(BASE_URL + '/user', { headers: { Authorization: this.TOKEN } })
      .then(response => {
        return response
      })
      .catch(error => {
        return error.response
      })
  },
  validateTransaction (itemId) {
    return axios.put(`${BASE_URL}/${itemId}/validate-transaction`, null, { headers: { Authorization: this.TOKEN } })
      .then(response => {
        return response
      })
      .catch(error => {
        return error.response
      })
  },

  endAuction (itemId) {
    return axios.put(`${BASE_URL}/${itemId}/end-auction`, null, { headers: { Authorization: this.TOKEN } })
      .then(response => {
        return response
      })
      .catch(error => {
        return error.response
      })
  }
}
