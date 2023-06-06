<template>
  <div>
    <bread-crum page-title="Identification" />
    <div class="main-container">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <LoginForm
              @submit="submitLoginForm"
              @resetMessage="resetMessage"
              :showErrorLoginMessage="showErrorLoginMessage"
            />
          </div>
          <div class="col-md-6">
            <RegistrationForm
              @submit="submitForm"
              @resetMessage="resetMessage"
              :showSuccessMessage="showSuccessMessage"
              :showErrorMailMessage="showErrorMailMessage"
              :showErrorPasswordMessage="showErrorPasswordMessage"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { registerUser, loginUser } from '@/services/UserService'
import BreadCrum from '@/components/Header/BreadCrumHeader.vue'
import LoginForm from '@/components/Form/LoginForm.vue'
import RegistrationForm from '@/components/Form/RegistrationForm.vue'
import router from '@/router'

export default {
  components: {
    BreadCrum,
    LoginForm,
    RegistrationForm
  },
  data () {
    return {
      showSuccessMessage: false,
      showErrorLoginMessage: false,
      showErrorMailMessage: false,
      showErrorPasswordMessage: false
    }
  },
  methods: {
    submitForm (userData) {
      registerUser(userData)
        .then(() => {
          this.showSuccessMessage = true
        })
        .catch(() => {
          this.showErrorMailMessage = true
        })
    },
    submitLoginForm (loginData) {
      loginUser(loginData)
        .then(() => {
          router.push('/connected')
        })
        .catch(() => {
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

<style scoped>
.main-container {
  margin-bottom: 60px;
}
</style>
