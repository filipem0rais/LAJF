<template>
  <form @submit="submitForm" >
    <status-message :message-type="messageType" :message="successMessage || errorMessage"></status-message>
    <div class="form-group">
      <label for="nomLot">Nom du lot</label>
      <input type="text" class="form-control" id="nomLot" v-model="iteName" placeholder="Entrez le nom du lot" required minlength="2">
    </div>
    <div class="form-group">
      <label for="description">Description</label>
      <textarea class="form-control" id="description" v-model="iteDescription" rows="3" maxlength="255"></textarea>
    </div>
    <label for="etatLot">Ancien Lot</label>
    <select class="form-control" v-model="selectedOldLot" @change="fillFormWithOldLot" id="oldLots">
      <option v-for="lot in oldLots" :value="lot.idItem" :key="lot.idItem">Nom : {{ lot.iteName }} - Valeur initiale : {{ lot.iteInitialValue}}</option>
    </select>
    <div class="form-group">
      <label for="valeurInitiale">Valeur initiale</label>
      <input type="text" class="form-control" id="valeurInitiale" v-model="iteInitialValue" placeholder="Entrez la valeur initiale" required pattern="[0-9]+" title="La valeur initiale doit contenir uniquement des chiffres">
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
      <category-select-display :categories="categories" @category-changed="onCategoryChanged" required></category-select-display>
    </div>
    <div class="form-group">
      <label for="image">Image</label>
      <input type="text" class="form-control" id="imageLot" v-model="itePicture" placeholder="Entrez le lien d'une image">
    </div>
    <button type="submit" class="btn btn-primary btn-block btn-lg">Valider</button>
  </form>
</template>

<script>
import StatusMessage from '@/components/Message/StatusMessage.vue'
import CategorySelectDisplay from '@/components/Display/CategorySelectDisplay.vue'
import { postLot } from '@/services/SubmissionService'
import { unSoldItems } from '@/services/LotService'

export default {
  components: {
    CategorySelectDisplay,
    StatusMessage
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
      iteBidCount: '',
      successMessage: '',
      errorMessage: '',
      selectedOldLot: null,
      oldLots: [],
      status: null
    }
  },
  mounted () {
    unSoldItems()
      .then(lots => {
        this.oldLots = lots
      })
      .catch(error => {
        console.error(error)
      })
  },
  methods: {
    fillFormWithOldLot () {
      const lot = this.oldLots.find(lot => lot.idItem === this.selectedOldLot)

      if (lot) {
        this.iteDescription = lot.iteDescription
        this.iteInitialValue = lot.iteInitialValue
        this.iteOnSale = lot.iteOnSale
        this.iteDatePublication = lot.iteDatePublication
        this.idUser = lot.idUser
        this.idCategory = lot.idCategory
        this.iteName = lot.iteName
        this.iteState = lot.iteState
        this.itePicture = lot.itePicture
        this.iteHighestBidAmount = lot.iteHighestBidAmount
        this.itePickedUp = lot.itePickedUp
        this.iteBidCount = lot.iteBidCount
      }
    },
    onCategoryChanged (category) {
      this.$emit('category-changed', category)
      this.idCategory = category.idCategory
    },
    submitForm () {
      this.errorMessage = null
      this.successMessage = null
      if (this.itePicture === '') {
        this.itePicture = 'https://dummyimage.com/300x300/ccc/000.jpg'
      }
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
      postLot(data)
        .then(response => {
          if (response.success) {
            this.successMessage = 'Votre lot a été ajouté avec succès'
            this.messageType = 'success'
            this.iteName = ''
            this.iteDescription = ''
            this.iteInitialValue = ''
            this.iteState = ''
            this.itePicture = ''
            this.idCategory = ''
          } else {
            this.errorMessage = response.message
            this.messageType = 'danger'
          }
          this.status = response.status
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
