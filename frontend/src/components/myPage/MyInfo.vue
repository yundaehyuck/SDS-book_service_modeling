<template>
  <div>
    <div>사용자 정보</div>
    <div id="img"><img :src=user.profileUrl></div>
    <div>{{ user.nickname }}</div>
    <!-- <b-button size="sm" @click="modifyUserInfo">사용자 정보 수정</b-button> -->
    <!-- TODO: 임시로 page를 생성하고 모달로 변경하도록 하겠습니다,,,-->
    <router-link :to="{ name: 'MyInfoModify' }" class="link"><b-button size="sm">사용자 정보 수정</b-button></router-link>&nbsp;
    <!-- <b-button size="sm" @click="deleteUserInfo">사용자 탈퇴</b-button> -->
    <b-button size="sm" v-b-modal.modal-delete-user>사용자 탈퇴</b-button>

    <b-modal
      id="modal-delete-user"
      ref="modal"
      title="회원 탈퇴"
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
      ok-title="확인"
      ok-variant="danger"
      cancel-title="취소"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
          label="회원 탈퇴를 원하신다면 아래의 문구를 입력해주세요."
          label-for="msg-input"
          invalid-feedback="문구를 올바르게 입력해주세요"
          :state="msgState"
        >
          <h6>&lt; 회원 탈퇴를 진행합니다 &gt;</h6><br>
          <b-form-input
            id="msg-input"
            v-model="msg"
            :state="msgState"
            required
          ></b-form-input>
        </b-form-group>
      </form>
    </b-modal>

  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
const userStore = 'userStore'

export default {
  name: 'MyInfo',
  components: {

  },
  data () {
    return {
      msg: '',
      msgState: null,
      submittedNames: []
    }
  },
  created () {
    this.isLoginUser = sessionStorage.getItem('isLoginUser')
    this.user = this.getUserInfo
    console.log(this.user)
  },
  computed: {
    ...mapState(userStore, ['isLogin', 'isLoginError', 'user']),
    ...mapGetters(userStore, ['getUserInfo']),
    user () {
      return JSON.parse(sessionStorage.getItem('userInfo'))
    }
  },

  methods: {
    ...mapActions(userStore, ['userDelete', 'userLogout']),
    deleteUserInfo () {
      console.log('사용자 탈퇴')
      if (confirm('정말로 탈퇴하시겠습니까?')) {
        this.userDelete()
          .then(() => {
            console.log('사용자 탈퇴 완료')
            return this.userLogout()
          })
          .then(() => {
            console.log('사용자 로그아웃 완료')
            alert('그동안 서비스를 이용해주셔서 감사합니다')
            this.$router.push({ name: 'Main' })
          })
          .catch((error) => {
            console.error(error)
          })
      }
    },
    checkFormValidity () {
      const valid = this.$refs.form.checkValidity()
      this.msgState = valid
      return valid
    },
    resetModal () {
      this.msg = ''
      this.msgState = null
    },
    handleOk (bvModalEvent) {
      bvModalEvent.preventDefault()
      this.handleSubmit()
    },
    handleSubmit () {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }

      if (this.msg === '회원 탈퇴를 진행합니다') {
        this.deleteUserInfo()
        this.$nextTick(() => {
          this.$bvModal.hide('modal-delete-user')
        })
      } else {
        this.msgState = false
        this.$refs.form.reportValidity()
      }
    }
  }

}
</script>

<style>
#img img {
  width: 200px;
  height: 200px;
}
.invalid-feedback {
  text-align: left;
}
</style>
