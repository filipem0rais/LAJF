<template>
  <menu-bar/>
  <footer-bar/>
  <router-view/>
</template>

<style>
#app {
font-family: Arial, sans-serif;
  margin-bottom: 60px;
}

</style>
<script setup>
import MenuBar from '@/components/MenuBar.vue'
import FooterBar from '@/components/FooterBar.vue'
</script>
