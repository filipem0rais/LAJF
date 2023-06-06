<template>
  <div class="card">
    <div class="card-body">
      <h2 class="card-title">S'inscrire</h2>
      <form @submit="submitForm" @click="resetMessage">
        <div class="mb-3">
          <label for="firstname" class="form-label">Prénom</label>
          <input type="text" class="form-control" id="firstname" v-model="userData.useName" required pattern="[A-Za-z]{2,25}" title="Au moins 2 caractères alphabétiques">
        </div>
        <div class="mb-3">
          <label for="lastname" class="form-label">Nom</label>
          <input type="text" class="form-control" id="lastname" v-model="userData.useLastName" required pattern="[A-Za-z]{2,25}"  title="Au moins 2 caractères alphabétiques">
        </div>
        <div class="mb-3">
          <label for="email2" class="form-label">Adresse e-mail</label>
          <input type="email" class="form-control" id="email2" v-model="userData.useEmail" required>
        </div>
        <div class="mb-3">
          <label for="password2" class="form-label">Mot de passe</label>
          <input type="password" class="form-control" id="password2" v-model="userData.usePassword" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{10,50}" title="Au moins 10 caractères, 1 chiffre, 1 majuscule/minuscule et 1 caractère spécial">
        </div>
        <div class="mb-3">
          <label for="confirm-password" class="form-label">Confirmez le mot de passe</label>
          <input type="password" class="form-control" id="confirm-password" v-model="confirmPassword">
        </div>
        <button type="submit" class="btn btn-primary">S'inscrire</button>
      </form>
      <div v-if="showSuccessMessage" class="alert alert-success mt-3">
        Vous êtes maintenant inscrit ! Connectez-vous pour accéder à votre compte.
      </div>
      <div v-if="showErrorMailMessage" class="alert alert-danger mt-3">
        Adresse e-mail déjà utilisée.
      </div>
      <div v-if="showErrorPasswordMessage" class="alert alert-danger mt-3">
        Les mots de passe ne correspondent pas.
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
  props: ['showSuccessMessage', 'showErrorMailMessage', 'showErrorPasswordMessage'],
  data () {
    return {
      userData: {
        usePassword: '',
        useName: '',
        useLastName: '',
        useEmail: ''
      },
      confirmPassword: ''
    }
  },
  methods: {
    submitForm (event) {
      event.preventDefault()

      if (this.userData.usePassword !== this.confirmPassword) {
        this.confirmPassword = ''
        this.userData.usePassword = ''
        this.$emit('resetMessage')
        this.$emit('showErrorPasswordMessage', true)
        return
      }

      this.$emit('submit', this.userData)
    },
    resetMessage () {
      this.$emit('resetMessage')
    }
  }
}
</script>
