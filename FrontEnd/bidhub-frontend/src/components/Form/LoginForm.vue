<template>
  <div class="card">
    <div class="card-body">
      <h2 class="card-title">Se connecter</h2>
      <form @submit="submitLoginForm" @click="resetMessage">
        <div class="mb-3">
          <label for="email" class="form-label">Adresse e-mail</label>
          <input type="email" class="form-control" id="email" v-model="loginData.useEmail" required>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Mot de passe</label>
          <input type="password" class="form-control" id="password" v-model="loginData.usePassword" required>
        </div>
        <button type="submit" class="btn btn-primary">Se connecter</button>
      </form>
      <div v-if="showErrorLoginMessage" class="alert alert-danger mt-3">
        Adresse e-mail ou mot de passe incorrect.
      </div>
    </div>
  </div>
</template>
<style scoped>
.btn{
  background-color: #ED7D2F;
  border-color: #ED7D2F;
}
</style>

<script>
export default {
  props: ['showErrorLoginMessage'],
  data () {
    return {
      loginData: {
        usePassword: '',
        useEmail: ''
      }
    }
  },
  methods: {
    submitLoginForm (event) {
      event.preventDefault()
      this.$emit('submit', this.loginData)
    },
    resetMessage () {
      this.$emit('resetMessage')
    }
  }
}
</script>
