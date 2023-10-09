<template>
  <div>
  </div>

</template>

<script>
import { mapState, mapActions } from 'vuex'
const userStore = 'userStore'

export default {
  name: 'oauth',
  data () {
    return {
      // isLoginError: false,
      kakaoCode: ''
    }
  },

  created () {
    // console.log(this.$route.query) // 경로 확인
    this.kakaoCode = this.$route.query

    if (this.kakaoCode.code === undefined) {
      console.log('undifined')
    } else {
      // console.log(this.kakaoCode.code)
      this.login(this.kakaoCode.code)
    }
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'isLoginError', 'user'])
  },
  methods: {
    ...mapActions(userStore, ['userLogin']),
    async login () {
      await this.userLogin(this.kakaoCode.code)
      this.$router.push({ name: 'Main' })
    }
  }

}
</script>

<style>

</style>
