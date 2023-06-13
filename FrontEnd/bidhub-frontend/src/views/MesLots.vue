<template>
  <br>
  <div class="container">
    <BreadCrumHeader :page-title="`Mes lots`"></BreadCrumHeader>
    <h4>Vente en attente (Vendeur)</h4>
    <hr class="filter-hr-short">
    <h4>Vente en attente (Preneur)</h4>
    <hr class="filter-hr-short">
    <h4>Lots suivis</h4>
    <hr class="filter-hr-short">
    <h4>Mes lots en vente</h4>
    <hr class="filter-hr-short">
    <h4>Mes anciens lots</h4>
    <hr class="filter-hr-short">
    <LotCard v-for="lot in lots" :key="lot.idItem" :lot="lot"/>
  </div>
</template>

<script>
import BreadCrumHeader from '@/components/Header/BreadCrumHeader.vue'
import LotCard from '@/components/Card/LotUserCard.vue'
import { getUnsoldItems } from '@/services/LotUserService'

export default {
  components: {
    BreadCrumHeader,
    LotCard
  },
  data () {
    return {
      oldLots: [],
      onSaleLots: [],
      biddedLots: [],
      takerLots: [],
      sellerLots: []
    }
  },
  mounted () {
    this.getLots()
  },
  methods: {
    getLots () {
      getUnsoldItems()
        .then(lots => {
          this.lots = lots
        })
        .catch(error => {
          console.error(error)
        })
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
</style>
