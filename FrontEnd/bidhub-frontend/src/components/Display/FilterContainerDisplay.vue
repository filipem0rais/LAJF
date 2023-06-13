<template>
  <div class="filter-container">
    <div class="categorie">
      <h4>Catégorie</h4>
      <hr class="filter-hr">
      <ul class="category-list">
        <li v-for="category in categories" :key="category.idCategory">
          <a :class="{ 'category-link': true, 'bold': category.clicked }" @click="toggleCategories(category)">
            {{ category.catName }}
          </a>
          <div v-if="category.showSubCategories">
            <sub-category :categories="category.subCategories" @updateItems="$emit('updateItems', $event)"></sub-category>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import SubCategory from '@/components/Display/SubCategoryDisplay.vue'
import CategorieService from '../../services/CategoryService'

export default {
  components: {
    SubCategory
  },
  props: {
    categories: {
      type: Array,
      default: () => []
    }
  },
  methods: {
    toggleCategories (category) {
      this.resetClickedCategories(this.categories)
      category.clicked = !category.clicked
      category.showSubCategories = !category.showSubCategories

      CategorieService.getItemsByCategory(category.idCategory)
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
.filter-container {
  height: 100%;
  padding-right: 15px;
}

.filter-hr {
  border: none;
  height: 2px;
  background-color: #ED7D2F;
}

.filter-hr:after {
  content: "";
  display: inline-block;
  position: relative;
  height: 2px;
  width: 100%;
}

.category-list {
  list-style-type: none;
  padding-left: 0;
}

.category-link {
  color: #000000;
  text-decoration: none;
}

.category-link:hover {
  color: #ED7D2F;
  cursor: pointer;
}

.bold {
  font-weight: bold;
}
</style>
