<template>
  <br>
  <div class="container">
    <BreadCrumHeader :page-title="`Mes lots`"></BreadCrumHeader>
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

export default {
  components: {
    BreadCrumHeader,
    LotCard
  },
  data () {
    return {
      takerLots: [],
      sellerLots: [],
      biddedLots: [],
      onSaleLots: [],
      oldLots: []
    }
  },
  mounted () {
    this.fetchLots()
  },
  methods: {
    fetchLots () {
      LotUserService.getWonItemsNotPickedUp()
        .then(data => {
          this.takerLots = data
        })
        .catch(error => console.error(error))
      LotUserService.getSoldItemsNotPickedUp()
        .then(data => {
          this.sellerLots = data
        })
        .catch(error => console.error(error))
      LotUserService.getBiddedItems()
        .then(data => {
          this.biddedLots = data
        })
        .catch(error => console.error(error))
      LotUserService.getItems()
        .then(data => {
          this.onSaleLots = data
        })
        .catch(error => console.error(error))
      LotUserService.getSoldItemsPickedUp()
        .then(data => {
          this.oldLots = data
        })
        .catch(error => console.error(error))
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
