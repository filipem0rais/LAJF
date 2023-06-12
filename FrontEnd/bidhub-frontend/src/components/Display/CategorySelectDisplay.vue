<template>
  <div>
    <select class="form-control" @change="onChange($event)">
      <option value="">Sélectionnez une catégorie</option>
      <option v-for="cat in categories" :key="cat.idCategory" :value="cat.idCategory">
        {{ cat.catName }}
      </option>
    </select>
    <label v-if="selectedCategory && selectedCategory.subCategories.length > 0">Sous-catégorie</label>
    <category-select-display v-if="selectedCategory && selectedCategory.subCategories.length > 0"
                    :categories="selectedCategory.subCategories" @category-changed="onSubCategoryChanged"/>
  </div>
</template>

<script>
export default {
  props: ['categories'],
  data () {
    return {
      selectedCategory: null
    }
  },
  methods: {
    onChange (event) {
      const selectedId = Number(event.target.value)
      this.selectedCategory = this.categories.find(cat => cat.idCategory === selectedId)
      console.log(this.selectedCategory)
      this.$emit('category-changed', this.selectedCategory)
    },
    onSubCategoryChanged (subCategory) {
      this.$emit('category-changed', subCategory)
    }
  }
}
</script>

<style scoped>

</style>
