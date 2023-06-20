<template>
  <div v-if="message" :class="messageClass">
    {{ message }}
  </div>
</template>

<script>
export default {
  props: ['messageType', 'message'],
  computed: {
    messageClass () {
      switch (this.messageType) {
        case 'success':
          return 'alert alert-success mt-3'
        case 'danger':
          return 'alert alert-danger mt-3'
        case 'warning':
          return 'alert alert-warning mt-3'
        default:
          return ''
      }
    }
  }
}
</script>
