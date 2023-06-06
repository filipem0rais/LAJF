<template>
  <div class="row">
    <div class="col">
      <div class="card" style="width: 18rem;">
        <img class="card-img-top card-image" :src="item.itePicture" alt="Image Lot">
        <div class="card-body">
          <h5 class="card-title">{{ item.iteName }}</h5>
          <p class="card-text">{{ item.iteInitialValue }} CHF</p>
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
.card {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}
.card-title {
  font-weight: bold;
}
.card-image {
  min-width: 265px;
  min-height: 265px;
  max-width: 265px;
  max-height: 265px;
}
</style>
