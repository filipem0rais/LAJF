<template>
  <footer class="sticky-footer">
    <div class="container-fluid bg-light">
      <div class="row">
        <div class="col-md-6 text-left py-2">
          &copy; Bidhub
        </div>
        <div class="col-md-6 text-right py-2">
          Luca Bohlen & Filipe Dias Morais
        </div>
      </div>
    </div>
  </footer>
</template>

<style scoped>
footer.sticky-footer {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  background-color: #f8f9fa;
  color: #9b9da4;
  z-index: 1000;
}

.container-fluid {
  padding: 10px;
}

.row {
  display: flex;
  justify-content: space-between;
}

.col-md-6 {
  flex: 0 0 50%;
  max-width: 50%;
}

.text-left {
  text-align: left;
}

.text-right {
  text-align: right;
}

.py-2 {
  padding-top: 8px;
  padding-bottom: 8px;
}
</style>
