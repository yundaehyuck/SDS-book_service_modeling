<template>
  <div>
    <h4>알림 신청</h4><br>
    <div>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-0"
        label="알림 신청 가격"
        label-for="input-0"
      >
        <b-form-input
          id="input-0"
          v-model="form.price"
          type="text"
          placeholder="Enter title"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group
        id="input-group-2"
        label="Book ISBN"
        label-for="input-2"
      >
        <b-form-input
          id="input-2"
          v-model="form.isbn"
          type="text"
          maxlength=13
          placeholder="Enter ISBN"
          required
          readonly
        ></b-form-input>
      </b-form-group>
    </b-form>
  </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import notificationStore from '../../store/modules/notificationStore'

export default {
  name: 'NotificationCreateView',
  props: ['isbn'],
  data () {
    return {
      form: {
        price: '',
        isbn: this.isbn
      },
      show: true
    }
  },
  methods: {
    ...mapActions(notificationStore, ['saveNotification']),
    onSubmit (event) {
      event.preventDefault()
      const payload = {
        data: {
          isbn: this.isbn,
          price: this.form.price
        }
      }
      console.log(payload)
    },
    onReset (event) {
      event.preventDefault()
      this.form.price = ''
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }

}
</script>

<style>
.star {
  display: inline-block;
  font-size: 2em;
}
</style>
