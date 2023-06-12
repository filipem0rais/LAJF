<template>
  <bread-crum page-title="Enchères"></bread-crum>
  <div class="container">
    <div class="row">
      <div class="col-md-3">
        <filter-container :categories="categories" @updateItems="updateItems"></filter-container>
      </div>
      <div class="col-md-9">
        <div class="d-flex justify-content-between align-items-center mb-3">
          <p>Affichage de {{ paginateLots().length }} sur {{ this.lots.length }}</p>
          <!-- Prochaine itération
          <select class="form-select custom-select">
            <option value="default" selected>Trier par défaut</option>
            <option value="priceAsc">Prix croissant</option>
            <option value="priceDesc">Prix décroissant</option>
          </select>
          -->
        </div>
        <div class="card-container">
          <div class="card" v-for="lot in paginateLots()" :key="lot.idItem">
            <router-link :to="'/lot/' + lot.idItem" class="card-link">
              <card-lot-enchere :item="lot"></card-lot-enchere>
            </router-link>
          </div>
        </div>
        <pagination-lot
          :current-page="currentPage"
          :total-pages="totalPages"
          @update-page="updatePage"
        ></pagination-lot>
      </div>
    </div>
  </div>
</template>

<script>
import BreadCrum from '@/components/BreadCrum.vue'
import CardLotEnchere from '@/components/CardLotEnchere.vue'
import LotService from '../services/LotService'
import CategorieService from '../services/CategorieService'
import FilterContainer from '@/components/FilterContainer.vue'
import PaginationLot from '@/components/PaginationLot.vue'

export default {
  components: {
    BreadCrum,
    CardLotEnchere,
    FilterContainer,
    PaginationLot
  },
  data () {
    return {
      lots: [],
      categories: [],
      sliderValues: [0, 100],
      minValue: 0,
      maxValue: 100,
      currentPage: 1,
      ITEMSPERPAGE: 10
    }
  },
  mounted () {
    this.getCategories()
    this.getLots()
  },
  computed: {
    totalPages () {
      return Math.ceil(this.lots.length / this.ITEMSPERPAGE)
    }
  },
  methods: {
    updateItems (newItems) {
      this.lots = newItems
    },
    getLots () {
      LotService.getLots()
        .then(lots => {
          this.lots = lots
        })
        .catch(error => {
          console.error(error)
        })
    },
    getCategories () {
      CategorieService.getCategories()
        .then(categories => {
          this.categories = categories
          console.log(this.categories)
        })
        .catch(error => {
          console.error(error)
        })
    },
    paginateLots () {
      const startIndex = (this.currentPage - 1) * this.ITEMSPERPAGE
      const endIndex = Math.min(startIndex + this.ITEMSPERPAGE, this.lots.length)
      return this.lots.slice(startIndex, endIndex)
    },
    updatePage (newPage) {
      this.currentPage = newPage
    }
  }
}
</script>

<style scoped>
.container {
  text-align: left;
}

.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 15px;
}

.card {
  width: 100%;
}

.custom-select {
  width: 200px !important;
}

.card-link {
  text-decoration: none;
  color: black;
}

.card-link:hover {
  text-decoration: none;
  color: black;
}

</style>
