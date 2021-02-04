<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="row">
            <div class="col-3 row ml-5">
              <img id="profile" class="col-12 r-10" :src="image" alt="noimage" />
              <div class="r-2" id="button-sort">
                <button id="image-change-button">
                  이미지 변경
                </button>
              </div>
            </div>
            <div class="col-1"></div>
            <div class="col-8 row" id="content-sort">
              <h1 class="col-12 r-4">사용자 {{ $store.state.name }}</h1>
              <div class="col-11" id="text-solid-1">한줄소개</div>
            </div>
            <div class="row ml-10">
              <div class="col-5" id="text-solid-margin">
                관심분야
                <hr />
              </div>
              <div class="col-5" id="text-solid">
                전화번호
                <hr />
              </div>
              <div class="col-5" id="text-solid-margin">
                경력
                <hr />
              </div>
              <div class="col-5" id="text-solid">
                자기소개
                <hr />
              </div>
              <div class="col-11" id="text-solid-one">
                최근답변
                <hr />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <div></div>
              </div>
              <!-- <div class="col-5" id="text-solid">6</div> -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  bodyClass: 'profile-page',
  data() {
    return {
      image: this.$store.state.image,
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/jj02.gif'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  created() {},

  methods: {},
};
</script>

<style>
#profile {
  border-radius: 70%;
}

#image-change-button {
  border: 1px solid gray;
  margin-top: 3px;
}
#button-sort {
  justify-items: center;
}
#text-solid {
  border: 1px solid black;
  width: 200px;
  height: 120px;
  margin-top: 2rem;
}
#text-solid-1 {
  border: 1px solid black;
  width: 200px;
  height: 120px;
}
#text-solid-margin {
  border: 1px solid black;
  width: 200px;
  height: 120px;
  margin-top: 2rem;
  margin-right: 4.7rem;
}
#text-solid-one {
  border: 1px solid black;
  height: auto;
  margin-top: 2rem;
}
</style>
