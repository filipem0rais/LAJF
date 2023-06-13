<template>
  <div class="card" style="width: 18rem;">
    <img :src="lot.itePicture" class="card-img-top" :alt="lot.iteName">
    <div class="card-body">
      <h5 class="card-title">{{ lot.iteName }}</h5>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    lot: {
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
