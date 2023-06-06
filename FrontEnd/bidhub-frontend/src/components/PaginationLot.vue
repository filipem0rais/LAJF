<template>
  <div class="pagination-buttons d-flex justify-content-center">
    <button class="btn btn-primary" @click="previousPage" :disabled="currentPage === 1">Précédent</button>
    <div class="page-info">
      Page {{ currentPage }} sur {{ totalPages }}
    </div>
    <button class="btn btn-primary" @click="nextPage" :disabled="currentPage === totalPages">Suivant</button>
  </div>
</template>

<script>
export default {
  props: {
    currentPage: {
      type: Number,
      required: true
    },
    totalPages: {
      type: Number,
      required: true
    }
  },
  methods: {
    previousPage () {
      if (this.currentPage > 1) {
        this.$emit('update-page', this.currentPage - 1)
      }
    },
    nextPage () {
      if (this.currentPage < this.totalPages) {
        this.$emit('update-page', this.currentPage + 1)
      }
    }
  }
}
</script>

<style scoped>
.pagination-buttons {
  margin-top: 20px;
  margin-bottom: 20px;
}

.pagination-buttons .btn-primary {
  background-color: #ED7D2F !important;
  border-color: #ED7D2F !important;
}

.pagination-buttons .page-info {
  margin: 0 10px;
}
</style>
