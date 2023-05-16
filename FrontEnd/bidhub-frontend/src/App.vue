<template>
  <menu-bar/>
  <footer-bar/>
  <router-view/>
</template>

<style>
#app {
font-family: 'Avenir', Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-right: auto;
  margin-left: auto;
}

</style>
<script setup>
import MenuBar from '@/components/MenuBar.vue'
import FooterBar from '@/components/FooterBar.vue'
</script>
