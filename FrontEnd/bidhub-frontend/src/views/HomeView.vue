<template>
  <div class="home">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="logo-container text-center">
            <img alt="Vue logo" src="../assets/logo.png" class="logo img-fluid">
            <hr class="title-underline">
          </div>
          <div class="text-container">
            <h2 class="fw-bold">Bienvenue sur notre site de vente aux enchères en ligne !</h2>
            <p>Notre plateforme vous propose une expérience unique pour acheter et vendre des objets et des produits de toutes catégories. Que vous soyez un collectionneur passionné, un amateur de mode ou un amateur d'objets rares, vous trouverez certainement votre bonheur parmi les ratiers de lots mis en vente chaque jour. </p>
            <p>Naviguez facilement sur notre site en utilisant nos filtres de recherche par catégorie pour trouver les lots qui vous intéressent le plus. Chaque lot dispose d'une fiche descriptive détalée avec des images haute résolution, pour vous permettre de visualiser tous les détails du produit. </p>
            <p>Vous souhaitez enchérir sur un lot vous suffit de créer un compte et de vous identifier. Vous pourrez alors placer des enchères sur vos lots préférés en temps réel et suivre les enchères des autres utilisateurs. </p>
            <p>Vous êtes plutôt vendeur Vous pouvez également proposer vos propres lots à la vente en quelques clics. Vous pouvez arrêter la mise aux enchères d'un lot que vous avez proposé à tout moment et le lot sera affecté à renchérisseur ayant fait la dernière offre ou considéré comme invendu.</p>
            <p class="fw-bold">Rejoignez dès maintenant notre communauté de passionnés et trouvez les produits rares et exclusifs que vous recherchez sur notre site de vente aux enchères en ligne ! </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
  components: {}
}
</script>

<style scoped>
.logo-container {
  margin-top: 65px;
  margin-bottom: 0px;
}

.title-underline {
  border: none;
  border-top: 2px solid black;
  width: 100%;
  margin: 30px auto;
}

.text-container {
  text-align: center;
}

img {
  width: 25%;
}
</style>
