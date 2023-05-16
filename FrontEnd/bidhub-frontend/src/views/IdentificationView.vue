<template>
  <body>
  <bread-crum/>
  <div class="main-container">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <div class="card">
            <div class="card-body">
              <h2 class="card-title">Se connecter</h2>
              <form>
                <div class="mb-3">
                  <label for="email" class="form-label">Adresse e-mail</label>
                  <input type="email" class="form-control" id="email" required>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Mot de passe</label>
                  <input type="password" class="form-control" id="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Se connecter</button>
              </form>
            </div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="card">
            <div class="card-body">
              <h2 class="card-title">S'inscrire</h2>
              <form>
                <div class="mb-3">
                  <label for="firstname" class="form-label">Prénom</label>
                  <input type="text" class="form-control" id="firstname" required>
                </div>
                <div class="mb-3">
                  <label for="lastname" class="form-label">Nom</label>
                  <input type="text" class="form-control" id="lastname" required>
                </div>
                <div class="mb-3">
                  <label for="email2" class="form-label">Adresse e-mail</label>
                  <input type="email" class="form-control" id="email2" required>
                </div>
                <div class="mb-3">
                  <label for="password2" class="form-label">Mot de passe</label>
                  <input type="password" class="form-control" id="password2" required>
                </div>
                <div class="mb-3">
                  <label for="confirm-password" class="form-label">Confirmez le mot de passe</label>
                  <input type="password" class="form-control" id="confirm-password" required>
                </div>
                <button type="submit" class="btn btn-primary">S'inscrire</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </body>
</template>

<style scoped>
.btn {
  background-color: #ED7D2F;
  border-color: #ED7D2F;
}

.card-title {
  padding-bottom: 10px;
}
</style>

<script>
import BreadCrum from '@/components/BreadCrum.vue'

export default {
  name: 'IdentificationView',
  components: {
    BreadCrum
  }
}
</script>
