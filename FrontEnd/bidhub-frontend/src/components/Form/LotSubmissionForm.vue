<template>
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
      <label for="categorie">Catégorie</label>
      <category-select :categories="categories" @category-changed="onCategoryChanged"></category-select>
    </div>
    <div class="form-group">
      <label for="image">Image</label>
      <input type="text" class="form-control" id="imageLot" placeholder="Entrez le lien d'une image">
    </div>
    <button type="submit" class="btn btn-primary btn-block btn-lg">Valider</button>
  </form>
</template>

<script>
import CategorySelect from '@/components/Display/CategorySelectDisplay.vue'

export default {
  components: {
    CategorySelect
  },
  props: ['categories'],
  methods: {
    onCategoryChanged (category) {
      this.$emit('category-changed', category)
    }
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
