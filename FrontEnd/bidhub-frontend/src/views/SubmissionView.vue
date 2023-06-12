<template>
  <div class="container">
    <bread-crum page-title="Vente"></bread-crum>
    <h1 class="card-title text-center">Soumission d'un lot</h1>
    <div class="card mx-auto mt-5" style="width: 50%;">
      <div class="card-body">
        <lot-form :categories="categories" @category-changed="onCategoryChanged"></lot-form>
      </div>
    </div>
  </div>
</template>

<script>
import BreadCrum from '@/components/Header/BreadCrumHeader.vue'
import CategorieService from '@/services/CategoryService.js'
import LotForm from '@/components/Form/LotSubmissionForm.vue'

export default {
  components: {
    BreadCrum,
    LotForm
  },
  data () {
    return {
      categories: []
    }
  },
  methods: {
    onCategoryChanged (category) {
      console.log('Selected category:', category)
    }
  },
  created () {
    CategorieService.fetchCategories('root')
      .then(categories => {
        this.categories = categories
      })
  }
}
</script>

<style scoped>
</style>
