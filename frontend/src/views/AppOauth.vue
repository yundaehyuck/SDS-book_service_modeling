<template>
  <div>
    <header-navbar></header-navbar>
    <div>outh</div>
    <oauth></oauth>
  </div>
</template>

<script>
import HeaderNavbar from '../components/HeaderNavbar.vue'
import Oauth from '../components/user/Oauth.vue'

export default {
  name: 'AppOauth',
  components: {
    HeaderNavbar,
    Oauth
  }
}
</script>

<style>

</style>
