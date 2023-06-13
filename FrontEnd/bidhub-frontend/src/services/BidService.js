
import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/bids'

export function postBid (bid) {
  const token = localStorage.getItem('token')
  return axios.post(BASE_URL, bid, { headers: { Authorization: token } })
    .then(response => {
      return response.data
    })
    .catch(error => {
      let errorMessage = 'Une erreur s\'est produite lors de la soumission de l\'enchère.'
      if (error.response) {
        const { status } = error.response
        if (status === 409) {
          errorMessage = "La valeur doit être supérieure à l'enchère actuelle."
        } else if (status === 400) {
          errorMessage = 'Problème de format.'
        } else if (status === 401) {
          errorMessage = 'Utilisateur non connecté.'
        } else if (status === 412) {
          errorMessage = 'Crédit insuffisant.'
        }
      }
      throw new Error(errorMessage)
    })
}
