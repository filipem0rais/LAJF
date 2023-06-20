<template>
  <br>
  <div class="container">
    <BreadCrumHeader :page-title="`Mes lots`"></BreadCrumHeader>
    <StatusMessage :message-type="messageType" :message="message"></StatusMessage>
    <h4>Vente en attente (Preneur)</h4>
    <hr class="filter-hr-short">
    <div class="grid-container">
      <div class="lot-card card-container" v-for="lot in takerLots" :key="lot.idItem">
        <LotCard :lot="lot" type=""/>
      </div>
    </div>
    <h4>Vente en attente (Vendeur)</h4>
    <hr class="filter-hr-short">
    <div class="grid-container">
      <div class="lot-card card-container" v-for="lot in sellerLots" :key="lot.idItem">
        <LotCard :lot="lot" type="seller"/>
      </div>
    </div>
    <h4>Lots suivis</h4>
    <hr class="filter-hr-short">
    <div class="grid-container">
      <div class="lot-card card-container" v-for="lot in biddedLots" :key="lot.idItem">
        <LotCard :lot="lot" type="followed"/>
      </div>
    </div>
    <h4>Mes lots en vente</h4>
    <hr class="filter-hr-short">
    <div class="grid-container">
      <div class="lot-card card-container" v-for="lot in onSaleLots" :key="lot.idItem">
        <LotCard :lot="lot" type="myLots"/>
      </div>
    </div>
    <h4>Mes anciens lots</h4>
    <hr class="filter-hr-short">
    <div class="grid-container">
      <div class="lot-card card-container" v-for="lot in oldLots" :key="lot.idItem">
        <LotCard :lot="lot" type=""/>
      </div>
    </div>
  </div>
</template>

<script>
import BreadCrumHeader from '@/components/Header/BreadCrumHeader.vue'
import LotCard from '@/components/Card/LotUserCard.vue'
import LotUserService from '../services/LotUserService'
import StatusMessage from '@/components/Message/StatusMessage.vue'

export default {
  components: {
    BreadCrumHeader,
    LotCard,
    StatusMessage
  },
  data () {
    return {
      takerLots: [],
      sellerLots: [],
      biddedLots: [],
      onSaleLots: [],
      oldLots: [],
      messageType: '',
      message: ''
    }
  },
  mounted () {
    this.fetchLots()
  },
  methods: {
    fetchLots () {
      const lotPromises = [
        LotUserService.getWonItemsNotPickedUp(),
        LotUserService.getSoldItemsNotPickedUp(),
        LotUserService.getBiddedItems(),
        LotUserService.getItems(),
        LotUserService.getSoldItemsPickedUp()
      ]
      Promise.all(lotPromises)
        .then(responses => {
          responses.forEach((response, index) => {
            switch (response.status) {
              case 200:
                this.setLotsData(index, response.data)
                break
              case 400:
                this.setMessage('Une erreur est survenue', 'danger')
                break
              case 401:
                this.setMessage('Vous devez être connecté', 'danger')
                break
              case 404:
                this.setMessage('Aucunes données trouvées', 'danger')
                break
              case 500:
                this.setMessage('Une erreur est survenue', 'danger')
                break
              default:
                this.setMessage('Une erreur est survenue', 'danger')
                break
            }
          })
        })
        .catch(error => {
          this.setMessage('Une erreur est survenue : ' + error.message, 'danger')
        })
    },
    setLotsData (index, data) {
      switch (index) {
        case 0:
          this.takerLots = data
          break
        case 1:
          this.sellerLots = data
          break
        case 2:
          this.biddedLots = data
          break
        case 3:
          this.onSaleLots = data
          break
        case 4:
          this.oldLots = data
          break
      }
    },
    setMessage (message, messageType) {
      this.message = message
      this.messageType = messageType
    }
  }
}
</script>

<style scoped>
.filter-hr-short {
  border: #ED7D2F;
  height: 2px;
  background-color: #ED7D2F;
  width: 50%;
  margin-left: 0;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(10rem, 1fr));
  grid-gap: 20px;
}
</style>
