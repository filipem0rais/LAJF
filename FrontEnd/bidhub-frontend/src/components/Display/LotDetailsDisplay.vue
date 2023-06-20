<template>
  <div class="lot-details">
    <h2 class="text-center">{{ lot.iteName }}</h2>
    <p>{{ lot.iteDescription }}</p>
    <p><strong>Etat :</strong> {{ lot.iteState }}</p>
  </div>
</template>

<script>
export default {
  name: 'LotDetailsInfo',
  props: ['lot']
}
</script>

<style scoped>
.lot-details {
  margin-top: 20px;
}

.lot-details h2 {
  font-weight: bold;
}

.lot-details p strong {
  font-weight: bold;
}
</style>
