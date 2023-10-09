<template>
  <div>
    <header-navbar></header-navbar>
    <login></login>
    <oauth></oauth>
  </div>
</template>

<script>
import HeaderNavbar from '../components/HeaderNavbar.vue'
import Login from '../components/user/Login.vue'
import Oauth from '../components/user/Oauth.vue'

export default {
  name: 'AppUser',
  components: {
    HeaderNavbar,
    Login,
    Oauth
  }
}
</script>

<style>

</style>
