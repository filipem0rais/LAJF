<template>
  <form @submit="submitForm" >
    <div class="form-group">
      <label for="nomLot">Nom du lot</label>
      <input type="text" class="form-control" id="nomLot" v-model="iteName" placeholder="Entrez le nom du lot" required>
    </div>
    <div class="form-group">
      <label for="description">Description</label>
      <textarea class="form-control" id="description" v-model="iteDescription" rows="3" required></textarea>
    </div>
    <div class="form-group">
      <label for="valeurInitiale">Valeur initiale</label>
      <input type="text" class="form-control" id="valeurInitiale" v-model="iteInitialValue" placeholder="Entrez la valeur initiale" required>
    </div>
    <div class="form-group">
      <label for="etatLot">État du lot</label>
      <select class="form-control" v-model="iteState" id="etatLot" required>
        <option value="Neuf">Neuf</option>
        <option value="Occasion">Occasion</option>
      </select>
    </div>
    <div class="form-group">
      <label for="categorie">Catégorie</label>
      <category-select-display :categories="categories" @category-changed="onCategoryChanged"></category-select-display>
    </div>
    <div class="form-group">
      <label for="image">Image</label>
      <input type="text" class="form-control" id="imageLot" v-model="itePicture" placeholder="Entrez le lien d'une image">
    </div>
    <button type="submit" class="btn btn-primary btn-block btn-lg">Valider</button>
  </form>
</template>

<script>
import CategorySelectDisplay from '@/components/Display/CategorySelectDisplay.vue'
import { postLot } from '@/services/SubmissionService'

export default {
  components: {
    CategorySelectDisplay
  },
  props: ['categories'],
  data () {
    return {
      idItem: '',
      iteDescription: '',
      iteInitialValue: '',
      iteOnSale: '',
      iteDatePublication: '',
      idUser: '',
      idCategory: '',
      iteName: '',
      iteState: '',
      itePicture: '',
      iteHighestBidAmount: '',
      itePickedUp: '',
      iteBidCount: ''
    }
  },
  methods: {
    onCategoryChanged (category) {
      this.$emit('category-changed', category)
      this.idCategory = category.idCategory
    },
    submitForm () {
      const data = {
        idItem: 5000,
        iteDescription: this.iteDescription,
        iteInitialValue: this.iteInitialValue,
        iteOnSale: true,
        iteDatePublication: this.getDate(),
        idUser: localStorage.getItem('userId'),
        idCategory: this.idCategory,
        iteName: this.iteName,
        iteState: this.iteState,
        itePicture: this.itePicture,
        iteHighestBidAmount: 0,
        itePickedUp: false,
        iteBidCount: 0
      }
      console.log(data)
      postLot(data)
        .then(() => {
          console.log('ok')
        })
        .catch(error => {
          console.log(error)
        })
    },
    getDate () {
      const date = new Date()
      const year = date.getFullYear()
      const month = String(date.getMonth() + 1).padStart(2, '0')
      const day = String(date.getDate()).padStart(2, '0')
      const hours = String(date.getHours()).padStart(2, '0')
      const minutes = String(date.getMinutes()).padStart(2, '0')
      const seconds = String(date.getSeconds()).padStart(2, '0')
      return year + '-' + month + '-' + day + 'T' + hours + ':' + minutes + ':' + seconds + '.000Z'
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
