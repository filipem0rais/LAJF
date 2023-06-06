<template>
  <ul class="sub-category-list">
    <li v-for="subCategory in categories" :key="subCategory.idCategory">
      <a :class="['sub-category-link', { 'bold': subCategory.clicked, 'has-subcategories': subCategory.subCategories && subCategory.subCategories.length > 0 }]" @click="toggleSubCategories(subCategory)">
        {{ subCategory.catName }}
      </a>
      <div v-if="subCategory.showSubCategories">
        <sub-category :categories="subCategory.subCategories" @updateItems="$emit('updateItems', $event)"></sub-category>
      </div>
    </li>
  </ul>
</template>

<script>
import CategorieService from '../../services/CategoryService'

export default {
  name: 'SubCategory',
  props: ['categories'],
  methods: {
    toggleSubCategories (subCategory) {
      this.resetClickedCategories(this.categories)
      subCategory.clicked = true
      subCategory.showSubCategories = !subCategory.showSubCategories

      CategorieService.getItemsByCategory(subCategory.idCategory)
        .then(items => {
          this.$emit('updateItems', items)
        })
        .catch(error => {
          console.error(error)
        })
    },
    resetClickedCategories (categories) {
      categories.forEach((category) => {
        category.clicked = false
        if (category.subCategories && category.subCategories.length > 0) {
          this.resetClickedCategories(category.subCategories)
        }
      })
    }
  }
}
</script>

<style scoped>
.sub-category-list {
  padding-left: 30px;
}

.sub-category-list li {
  list-style-type: none;
}

.sub-category-link {
  color: inherit;
  text-decoration: none;
  cursor: pointer;
}

.sub-category-link:hover {
  text-decoration: none;
  color: orange;
}

.bold {
  font-weight: bold;
}
</style>
