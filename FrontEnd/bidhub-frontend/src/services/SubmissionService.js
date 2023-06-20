import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/items'

export function postLot (lot) {
  const token = localStorage.getItem('token')
  return axios.post(BASE_URL, lot, { headers: { Authorization: token } })
    .then(response => {
      return {
        success: true,
        data: response.data,
        status: response.status
      }
    })
    .catch(error => {
      let errorMessage = ''
      if (error.response) {
        switch (error.response.status) {
          case 404:
            errorMessage = 'Le serveur ne peut pas trouver le lot demandé.'
            break
          case 500:
            errorMessage = 'Il y a un problème avec le serveur. Veuillez réessayer plus tard.'
            break
          default:
            errorMessage = 'Une erreur s\'est produite. Veuillez réessayer.'
            break
        }
      } else {
        errorMessage = 'Une erreur s\'est produite. Veuillez vérifier votre connexion internet et réessayer.'
      }
      return {
        success: false,
        message: errorMessage,
        status: error.response ? error.response.status : null
      }
    })
}
