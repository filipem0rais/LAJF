import axios from 'axios'

const BASE_URL = 'http://localhost:9090/api/categories'

export default {
  getCategories () {
    return this.fetchCategories('root')
  },

  getItemsByCategory (idCategory) {
    return axios
      .get(`http://localhost:9090/api/items/categories/${idCategory}`)
      .then(response => response.data)
      .catch(error => {
        throw error
      })
  },

  fetchCategories (categoryId) {
    return axios.get(`${BASE_URL}/${categoryId}`)
      .then(response => {
        const categories = response.data
        const childrenPromises = categories.map(category => {
          if (category.hasChildren) {
            return this.fetchCategories(category.id)
              .then(children => {
                category.children = children
                return category
              })
          } else {
            return category
          }
        })
        return Promise.all(childrenPromises)
      })
      .catch(error => {
        console.error(error)
        throw error
      })
  }
}
