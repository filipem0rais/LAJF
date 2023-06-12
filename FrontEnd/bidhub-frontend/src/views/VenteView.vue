<template>
  <div class="container">
    <bread-crum page-title="Vente"></bread-crum>
    <h1 class="card-title text-center">Soumission d'un lot</h1>
    <div class="card mx-auto mt-5" style="width: 50%;">
      <div class="card-body">
        <form>
          <div class="form-group">
            <label for="nomLot">Nom du lot</label>
            <input type="text" class="form-control" id="nomLot" placeholder="Entrez le nom du lot">
          </div>
          <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" rows="3"></textarea>
          </div>
          <div class="form-group">
            <label for="valeurInitiale">Valeur initiale</label>
            <input type="text" class="form-control" id="valeurInitiale" placeholder="Entrez la valeur initiale">
          </div>
          <div class="form-group">
            <label for="etatLot">État du lot</label>
            <select class="form-control" id="etatLot">
              <option value="Neuf">Neuf</option>
              <option value="Occasion">Occasion</option>
            </select>
          </div>
          <div class="form-group">
            <label for="categorie">Categorie</label>
            <CategorySelect :categories="categories" @category-changed="onCategoryChanged"/>
          </div>
          <div class="form-group">
            <label for="image">Image</label>
            <input type="text" class="form-control" id="imageLot" placeholder="Entrez le lien d'une image">
          </div>
          <button type="submit" class="btn btn-primary btn-block btn-lg">Valider</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import BreadCrum from '@/components/BreadCrum.vue'
import CategorieService from '@/services/CategorieService.js'
import CategorySelect from '@/components/CategorySelect.vue'

export default {
  components: {
    BreadCrum,
    CategorySelect
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
.form-group {
  padding-bottom: 20px;
}
.btn{
  background-color: #ED7D2F;
  border-color: #ED7D2F;
  width: 100%;
}
</style>
