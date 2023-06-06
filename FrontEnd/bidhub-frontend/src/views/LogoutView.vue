<template>
  <div class="logout-view">
    <h1>À bientôt !</h1>
    <p>Vous êtes maintenant déconnecté.</p>
  </div>
</template>

<style scoped>
.logout-view {
  text-align: center;
  padding: 20px;
  margin-top: 300px;
}

.logout-view h1 {
  font-size: 40px; /* Augmenté la taille de la police */
  margin-bottom: 10px;
}

.logout-view p {
  font-size: 20px; /* Augmenté la taille de la police */
  margin-top: 5px;
}
h1{
  color : #ED7D2F;
}
</style>

<script>
export default {
  name: 'LogoutView',
  components: {}
}
</script>
