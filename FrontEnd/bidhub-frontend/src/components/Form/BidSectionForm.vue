<template>
  <div class="offer-section">
    <h2 class="text-center"><strong>Placer une offre</strong></h2>
    <p v-if="lot.iteHighestBidAmount">Enchère courante: <strong>{{ lot.iteHighestBidAmount }} CHF </strong></p>
    <p v-else>Aucune enchère en cours</p>
    <p>Valeur initiale: <strong>{{ lot.iteInitialValue }} CHF</strong></p>
    <p>Nombre d'enchère(s): </p>
    <div class="input-group mb-3">
      <input type="text" class="form-control" v-model="bidAmount" placeholder="Nouvelle enchère">
      <div class="input-group-append">
        <button class="btn btn-primary" type="button" @click="placeBid">Placer une enchère</button>
      </div>
    </div>
  </div>
</template>

<script>
import { postBid } from '@/services/BidService'

export default {
  name: 'OfferSection',
  props: ['lot'],
  data () {
    return {
      itemId: '',
      bidAmount: ''
    }
  },
  methods: {
    placeBid () {
      const bid = {
        itemId: this.lot.idItem,
        bidAmount: this.bidAmount
      }
      postBid(bid)
        .then(() => {
          this.$emit('bid-placed')
        })
        .catch(error => {
          console.error(error)
        })
    }
  }
}
</script>

<style scoped>
.offer-section {
  margin-top: 30px;
}

.btn-primary {
  background-color: #ED7D2F !important;
  border-color: #ED7D2F !important;
}
</style>
