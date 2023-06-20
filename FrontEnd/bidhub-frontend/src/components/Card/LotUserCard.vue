<template>
  <StatusMessage :message="message" :messageType="messageType" />
  <div class="card-container">
    <div class="card">
      <div class="card-image">
        <img :src="lot.itePicture" :alt="lot.iteName">
      </div>
      <div class="card-body">
        <h2 class="card-title">{{ lot.iteName }}</h2>
        <p class="card-text">{{ lot.iteHighestBidAmount > 0 ? lot.iteHighestBidAmount + ' CHF' : 'Aucune enchère' }}</p>
        <router-link :to="'/lot/' + lot.idItem" class="card-link">
          <button v-if="type === 'followed'" class="btn btn-primary">Enchérir !</button>
        </router-link>
        <button v-if="type === 'seller'" class="btn btn-warning" @click="handleValidate">Valider</button>
        <button v-if="type === 'myLots'" class="btn btn-danger" @click="handleFinish">Terminer</button>
      </div>
    </div>
  </div>
</template>

<script>
import LotUserService from '@/services/LotUserService'
import StatusMessage from '@/components/Message/StatusMessage.vue'

export default {
  components: {
    StatusMessage
  },
  props: {
    lot: {
      type: Object,
      required: true
    },
    type: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      message: '',
      messageType: ''
    }
  },
  methods: {
    handleValidate () {
      LotUserService.validateTransaction(this.lot.idItem)
        .then(() => {
          this.message = 'La transaction a bien été validée'
          this.messageType = 'success'
        })
        .catch(error => {
          this.message = 'Une erreur est survenue lors de la validation : ' + error.message
          this.messageType = 'danger'
        })
    },
    handleFinish () {
      LotUserService.endAuction(this.lot.idItem)
        .then(() => {
          this.message = 'L\'enchère a bien été terminée'
          this.messageType = 'success'
        })
        .catch(error => {
          this.message = 'Une erreur est survenue lors de la fin de l\'enchère : ' + error.message
          this.messageType = 'danger'
        })
    }
  }
}
</script>

<style scoped>
.card {
  width: 100%;
}

.card-image {
  position: relative;
  width: 100%;
  height: 0;
  padding-top: 100%;
}

.card-image img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.card-body {
  padding: 10px;
}

.card-title {
  font-size: 20px;
  margin: 0;
  font-weight: bold;
}

button {
  width: 100%;
}
</style>
