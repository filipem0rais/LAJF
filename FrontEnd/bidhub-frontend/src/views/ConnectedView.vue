<template>
  <div class="connected-view">
    <img class="check" alt="Check connexion" src="../assets/check.png">
    <h1>Bienvenue !</h1>
    <p class="subtext">Vous êtes connecté.</p>
    <router-link to="/encheres" class="btn btn-primary">Accéder aux enchères</router-link>
  </div>
</template>

<script>
export default {
  name: 'ConnectedView',
  components: {}
}
</script>

<style scoped>
.connected-view {
  text-align: center;
  padding: 20px;
  margin-top: 300px;
}

.connected-view h1 {
  font-size: 36px;
  margin-bottom: 10px;
}

.connected-view .subtext {
  font-size: 18px;
  margin-top: 5px;
}

.check {
  width: 100px;
  height: 100px;
  margin-bottom: 20px;
}

.btn {
  background-color: #ED7D2F;
  border-color: #ED7D2F;
}
.btn:hover {
  background-color: #E85C0C;
  border-color: #E85C0C;
}

</style>
