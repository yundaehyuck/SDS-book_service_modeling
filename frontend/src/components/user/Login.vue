<template>
  <div>
      <div id="loginForm">
        <div style="margin-right:100px;">
          <img src="../../assets/images/login_logo.png" style="width:230px;">
        </div>
        <div>
          <span id="font">간편하게<br> 아이북을<br> 이용해보세요.<br></span>
        </div>
      </div>
    <p>
      <a :href="kakaoLoginLink">
        <img src="../../assets/images/kakao_login_large_wide.png" style="width:400px; margin-bottom:30px;">
      </a>
    </p>
  </div>
</template>

<script>
// import axios from 'axios'
export default {
  name: 'login',
  data () {
    return {
      kakaoCode: '',
      kakaoLoginLink: `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.VUE_APP_REST_API_KEY}&redirect_uri=${process.env.VUE_APP_REDIRECT_URI}&response_type=code`
    }
  },
  created () {
    this.kakaoCode = this.$route.query
  },
  methods: {
  }

}
</script>

<style scoped>

#font{
  font-weight: bold;
  font-size:45px;
  color:white;
  text-shadow: 0px 2px 4px rgba(0, 0, 0, 0.4);
}

#loginForm{
  margin: 100px;
  margin-bottom: 40px;
  padding:0px;
  text-align: center;
  display: flex;
  justify-content: center;
}
</style>
