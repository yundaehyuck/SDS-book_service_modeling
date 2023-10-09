<template>
  <div class="modal-overlay">
    <b-modal v-model="visible" @hidden="onHidden" hide-footer title="리뷰 등록">
      <slot></slot>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: 'ReviewCreateModalView',
  props: {
    modalShow: {
      type: Boolean,
      required: true
    }
  },
  data () {
    return {

    }
  },
  methods: {
    onHidden () {
      this.$emit('close-modal')
    }
  },
  watch: {
    modalShow (newValue) {
      this.visible = newValue
    }
  },
  computed: {
    visible: {
      get () {
        return this.modalShow
      },
      set (newValue) {
        this.$emit('update:modalShow', newValue)
      }
    }
  }
}
</script>

<style scoped>
</style>
