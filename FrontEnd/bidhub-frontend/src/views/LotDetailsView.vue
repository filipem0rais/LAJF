<template>
  <bread-crum :page-title="`Lot Numéro : ${lot.idItem}`"></bread-crum>
  <div class="container">
    <div class="row">
      <div class="col">
        <div class="d-flex justify-content-center align-items-center">
          <img :src="lot.itePicture" alt="lot image" class="img-fluid">
        </div>
        <div class="lot-details">
          <h2 class="text-center">{{ lot.iteName }}</h2>
          <p>{{ lot.iteDescription }}</p>
          <p><strong>Etat :</strong> {{lot.iteState}}</p>
          <p><strong>Categories:</strong> <!-- Affiche les catégories --></p>
        </div>
      </div>
      <div class="col">
        <div class="offer-section">
          <h2 class="text-center"> <strong>Placer une offre</strong></h2>
          <p v-if="lot.iteHighestBidAmount">Enchère courante: <strong>{{ lot.iteHighestBidAmount }} CHF </strong></p>
          <p v-else>Aucune enchère en cours</p>
          <p>Valeur initiale: <strong>{{ lot.iteInitialValue }} CHF</strong></p>
          <p>Nombre d'enchère(s): </p>
          <div class="input-group mb-3">
            <input type="text" class="form-control" v-model="newBid" placeholder="Nouvelle enchère">
            <div class="input-group-append">
              <button class="btn btn-primary" type="button" @click="placeBid">Placer une enchère</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LotService from '../services/LotService'
import BreadCrum from '@/components/BreadCrum.vue'

export default {
  name: 'LotDetails',
  components: {
    BreadCrum
  },
  data () {
    return {
      lot: {},
      newBid: ''
    }
  },
  mounted () {
    const lotId = this.$route.params.id
    this.fetchLotDetails(lotId)
  },
  methods: {
    fetchLotDetails (lotId) {
      LotService.getLotById(lotId)
        .then(lot => {
          this.lot = lot
        })
        .catch(error => {
          console.error(error)
        })
    }
  }
}
</script>

<style scoped>
.lot-details {
  margin-top: 20px;
}

.offer-section {
  margin-top: 30px;
}

img{
  max-width: 500px;
  max-height: 500px;
}

.lot-details h2 {
  font-weight: bold;
}

.lot-details p strong {
  font-weight: bold;
}

.btn-primary {
  background-color: #ED7D2F !important;
  border-color: #ED7D2F !important;
}
</style>
