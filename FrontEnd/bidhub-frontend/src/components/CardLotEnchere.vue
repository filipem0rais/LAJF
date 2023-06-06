<template>
    <div class="row">
      <div class="col-auto">
        <img :src="item.itePicture" alt="Item Image" class="card-image"/>
      </div>
      <div class="col">
        <div class="card-block px-2">
          <h3>Numéro {{ item.idItem}} : {{ item.iteName }}</h3>
          <p>{{ item.iteDescription }}</p>
          <div class="bottom">
            <p class="prix">Valeur initale : {{ item.iteInitialValue }} CHF</p>
            <br>
            <p v-if="item.iteHighestBidAmount" class="prix">Valeur courante : {{ item.iteHighestBidAmount }} CHF</p>
            <p v-else class="prix notPrice">Aucune enchère en cours</p>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
.card-image {
  max-width: 300px;
  max-height: 300px;
  object-fit: contain;
}

.bottom {
  position: absolute;
  display: flex;
  bottom: 0;
}

.prix {
  font-weight: bold;
  margin-right: 10px;
}

.notPrice {
  color: #ED7D2F;
  font-style: italic;
}
</style>
