<template>
  <div class="home">
    <div class="container">
      <div class="row">
        <div class="col-md-12">

          <div class="logo-container text-center">
            <img alt="Vue logo" src="../assets/logo.png" class="logo img-fluid">
            <hr class="title-underline">
          </div>
          <div class="text-container">
            <h2 class="fw-bold">Bienvenue sur notre site de vente aux enchères en ligne !</h2>
            <p>Rejoignez dès maintenant notre communauté de passionnés et trouvez les produits rares et
              exclusifs que vous recherchez sur notre site de vente aux enchères en ligne ! </p>
          </div>
          <div class="last">
            <div class="row">
              <div class="col-md-3" v-for="lot in latestLots" :key="lot.idItem">
                <card-lot-accueil :item="lot"></card-lot-accueil>
              </div>
            </div>
          </div>

        </div>

      </div>
    </div>
  </div>
</template>

<script>
import CardLotAccueil from '@/components/Card/LotHomeCard.vue'
import LotService from '../services/LotService'

export default {
  name: 'HomeView',
  components: {
    CardLotAccueil
  },
  data () {
    return {
      latestLots: []
    }
  },
  mounted () {
    this.fetchLatestLots()
  },
  methods: {
    fetchLatestLots () {
      LotService.getLatestLots(4)
        .then(lots => {
          this.latestLots = lots
        })
        .catch(error => {
          console.error(error)
        })
    }
  }
}
</script>

<style scoped>
.logo-container {
  margin-top: 55px;
  margin-bottom: 0;
}

.title-underline {
  border: none;
  border-top: 2px solid black;
  width: 100%;
  margin: 20px auto;
}

.text-container {
  text-align: center;
}

img {
  width: 25%;
}

.last {
  text-align: center;
  margin-top: 50px;
}

</style>
