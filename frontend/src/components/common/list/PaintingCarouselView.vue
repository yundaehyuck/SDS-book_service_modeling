<template>
  <div>
    <!-- 캐러셀 창 설정 -->
    <b-carousel
      ref="myCarousel"
      id="carousel-1"
      v-model="slide"
      :interval="2500"
      indicators
      img-width="1024"
      img-height="450"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
    <!-- 여기에 보여줄 리스트 넣으면 됨 -->
    <!-- 캐러셀 한 페이지에 N개의 요소를 보여주려고 chunk,,, chunkeItems()에서 chunkSize 변경 가능 -->
      <template v-for="(itemsChunk, index) in chunkedItems">
        <!-- 캐러셀 배경 필요 ... 현재는 img-blank -->
        <b-carousel-slide :key="index" img-blank img-alt="Blank image">
          <div class="list-item-flex">
            <!-- key를 요소에 맞춰 설정 -->
            <div v-for="item in itemsChunk" :key="item.id" class="mychunk">
              <!-- ListItem -->
                <list-item :item="item"></list-item>
              <!-- ListItem -->
            </div>
          </div>
        </b-carousel-slide>
      </template>

    </b-carousel>
  </div>
</template>

<script>
import ListItem from './ListItem.vue'

export default {
  name: 'Carousel',
  components: {
    ListItem
  },
  props: {
    items: {
      type: Array,
      required: true
    },
    chunkSize: Number
  },
  data () {
    return {
      slide: 0,
      sliding: null
    }
  },
  methods: {
    onSlideStart (slide) {
      this.sliding = true
    },
    onSlideEnd (slide) {
      this.sliding = false
    },
    prev () {
      this.$refs.myCarousel.prev()
    },
    next () {
      this.$refs.myCarousel.next()
    },
    moveTo (id) {
      // 어떤 하나의 아이템을 클릭했을 때 수행할 함수
      this.$emit('moveTo', id)
    }
  },
  computed: {
    chunkedItems () {
      return this.items.reduce((resultArray, item, index) => {
        const chunkIndex = Math.floor(index / this.chunkSize)
        if (!resultArray[chunkIndex]) {
          resultArray[chunkIndex] = [] // start a new chunk
        }
        resultArray[chunkIndex].push(item)
        return resultArray
      }, [])
    }
  }
}
</script>
<style scoped>
.list-item-flex{
  display: flex;
  justify-content: center;
}
.mychunk {
  width: 210px!important;
  height: 350px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-right: 10px;
  margin-left: 10px;
}

.my-controls {
  display: flex;
  justify-content: right;
}

.carousel-btn{
  width: 40px;
  height: 40px;
  border-radius: 100%;
  color: var(--ae-navy);
  border: 1px solid #E5E3DA;
  background-color: white;
  margin-right: 5px;
}

.right{
  border: 1px solid var(--ae-red);
  background-color: var(--ae-red);
  color: white;
}

.carousel-inner{
  width: 80%!important;
}

.carousel-inner{
  height: 100%!important;
}
</style>
