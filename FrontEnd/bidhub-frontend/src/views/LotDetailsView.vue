<template>
  <bread-crum :page-title="`Lot Numéro : ${lot.idItem}`"></bread-crum>
  <div class="container">
    <div class="row">
      <div class="col">
        <div class="d-flex justify-content-center align-items-center">
          <img :src="lot.itePicture" alt="lot image" class="img-fluid">
        </div>
        <LotDetailsInfo :lot="lot"></LotDetailsInfo>
      </div>
      <div class="col">
        <OfferSection :lot="lot"></OfferSection>
      </div>
    </div>
  </div>
</template>

<script>
import LotService from '../services/LotService'
import BreadCrum from '@/components/Header/BreadCrumHeader.vue'
import LotDetailsInfo from '@/components/Display/LotDetailsDisplay.vue'
import OfferSection from '@/components/Form/BidSectionForm.vue'

export default {
  name: 'LotDetails',
  components: {
    BreadCrum,
    LotDetailsInfo,
    OfferSection
  },
  data () {
    return {
      lot: {}
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
img {
  max-width: 500px;
  max-height: 500px;
}

</style>
