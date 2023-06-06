<template>
  <body>
  <bread-crum page-title="Identification"/>
  <div class="main-container">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
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
        </div>
        <div class="col-md-6">
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
        </div>
      </div>
    </div>
  </div>
  </body>
</template>
<style scoped>
.main-container {
  margin-bottom: 60px;
}

.btn {
  background-color: #ED7D2F;
  border-color: #ED7D2F;
}

.btn:hover {
  background-color: #E85C0C;
  border-color: #E85C0C;
}

.card-title {
  padding-bottom: 10px;
}
</style>
<script>
import { registerUser, loginUser } from '@/services/UserService'
import BreadCrum from '@/components/Header/BreadCrumHeader.vue'
import router from '@/router'

export default {
  components: {
    BreadCrum
  },
  data () {
    return {
      userData: {
        usePassword: '',
        useName: '',
        useLastName: '',
        useEmail: ''
      },
      loginData: {
        usePassword: '',
        useEmail: ''
      },
      confirmPassword: '',
      passwordMismatch: false,
      showSuccessMessage: false,
      showErrorLoginMessage: false,
      showErrorMailMessage: false,
      showErrorPasswordMessage: false
    }
  },
  methods: {
    submitForm (event) {
      event.preventDefault()

      if (this.userData.usePassword !== this.confirmPassword) {
        this.confirmPassword = ''
        this.userData.usePassword = ''
        this.passwordMismatch = true
        this.showErrorPasswordMessage = true
        return
      }

      registerUser(this.userData)
        .then(() => {
          this.userData = {
            usePassword: '',
            useName: '',
            useLastName: '',
            useEmail: ''
          }
          this.confirmPassword = ''
          this.passwordMismatch = false
          this.showSuccessMessage = true
        })
        .catch(() => {
          console.log('error')
          this.showErrorMailMessage = true
          this.userData.useEmail = ''
        })
    },
    submitLoginForm (event) {
      event.preventDefault()
      loginUser(this.loginData)
        .then(() => {
          router.push('/connected')
        })
        .catch(() => {
          this.loginData = {
            usePassword: '',
            useEmail: ''
          }
          this.showErrorLoginMessage = true
        })
    },
    resetMessage () {
      this.showSuccessMessage = false
      this.showErrorLoginMessage = false
      this.showErrorMailMessage = false
      this.showErrorPasswordMessage = false
    }
  }
}
</script>
