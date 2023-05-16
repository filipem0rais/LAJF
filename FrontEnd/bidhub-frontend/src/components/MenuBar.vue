<template>
  <header>
    <div class="navbar navbar-expand-lg bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">
          <img src="../assets/logo.png" alt="Logo" class="logo logo-white-bg">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <router-link class="nav-link text-white" to="/">Accueil</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link text-white" to="/about">Enchères</router-link>
            </li>
          </ul>
          <ul class="navbar-nav custom-nav">
            <li class="nav-item">
              <router-link class="nav-link text-white" to="/identification">Identification</router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <br>
  </header>
</template>

<style scoped>
.logo {
  height: 50px;
}

.navbar-nav a {
  color: white;
  text-decoration: none;
  font-weight: bold;
  font-size: 18px;
  text-transform: uppercase;
  padding: 10px;
  border-radius: 5px;
}

.navbar-nav a:hover {
  color: white;
  background-color: #ED7D2F;
  text-decoration: none;
}

.custom-nav {
  margin-left: auto;
}
</style>
