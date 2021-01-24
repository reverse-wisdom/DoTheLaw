<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <img :src="logo" alt="logo" />
              <!-- <v-text-field label="검색" placeholder="검색어입력" background-color="blue" filled rounded solo solo-inverted></v-text-field>

              <v-btn color="primary" depressed elevation="23" raised>검색</v-btn> -->
              <div>
                <section class="webdesigntuts-workshop">
                  <form action="" method="">
                    <input type="search" placeholder="검색어입력" />
                    <button>검색</button>
                  </form>
                </section>
              </div>
              <!-- 리스트 -->

              <v-list>
                <v-list-item-group v-model="model">
                  <v-list-item v-for="(item, i) in items" :key="i">
                    <v-list-item-icon>
                      <v-icon v-text="item.icon"></v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                      <v-list-item-title v-text="item.text"></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </div>
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section section-basic">
        <!-- 테스트 영역 start-->

        <div class="container">
          <v-icon>mdi-home</v-icon>
          <v-data-table
            :headers="headers"
            :items="desserts"
            :items-per-page="5"
            item-key="name"
            class="elevation-1"
            :footer-props="{
              showFirstLastPage: true,
              firstIcon: 'mdi-arrow-collapse-left',
              lastIcon: 'mdi-arrow-collapse-right',
              prevIcon: 'mdi-minus',
              nextIcon: 'mdi-plus',
            }"
          ></v-data-table>
          <v-icon large color="green darken-2">
            mdi-domain
          </v-icon>
        </div>

        <!-- 테스트 영역 end -->

        <div class="container">
          <div class="title">
            <h2>Basic Elements</h2>
          </div>
          <basic-elements></basic-elements>
        </div>
      </div>
      <div class="section section-navbars">
        <div class="container">
          <small-navigation></small-navigation>
        </div>
        <navigation></navigation>
      </div>
      <div class="section section-tabs">
        <div class="container">
          <tabs></tabs>
        </div>
      </div>
      <div class="section section-white">
        <div class="container">
          <nav-pills></nav-pills>
        </div>
      </div>
      <div class="section section-notifications">
        <div class="container">
          <div class="title">
            <h3>Notifications</h3>
          </div>
        </div>
        <notifications></notifications>
      </div>
      <div class="section">
        <div class="container">
          <typography-images></typography-images>
        </div>
      </div>
      <div class="section section-javascript">
        <div class="container">
          <javascript-components></javascript-components>
        </div>
      </div>
      <div class="section">
        <div class="container text-center">
          <div class="md-layout">
            <div class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center">
              <h2>Completed with examples</h2>
              <h4>
                The kit comes with three pre-built pages to help you get started faster. You can change the text and images and you're good to go. More importantly, looking at them will give you a
                picture of what you can built with this powerful kit.
              </h4>
            </div>
          </div>
        </div>
      </div>
      <div class="section section-signup page-header" :style="signupImage">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-33 md-medium-size-40 md-small-size-50 md-xsmall-size-70 mx-auto text-center">
              <login-card header-color="green">
                <h4 slot="title" class="card-title">Login</h4>
                <md-button slot="buttons" href="javascript:void(0)" class="md-just-icon md-simple md-white">
                  <i class="fab fa-facebook-square"></i>
                </md-button>
                <md-button slot="buttons" href="javascript:void(0)" class="md-just-icon md-simple md-white">
                  <i class="fab fa-twitter"></i>
                </md-button>
                <md-button slot="buttons" href="javascript:void(0)" class="md-just-icon md-simple md-white">
                  <i class="fab fa-google-plus-g"></i>
                </md-button>
                <p slot="description" class="description">Or Be Classical</p>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>face</md-icon>
                  <label>First Name...</label>
                  <md-input v-model="firstname"></md-input>
                </md-field>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>email</md-icon>
                  <label>Email...</label>
                  <md-input v-model="email" type="email"></md-input>
                </md-field>
                <md-field class="md-form-group" slot="inputs">
                  <md-icon>lock_outline</md-icon>
                  <label>Password...</label>
                  <md-input v-model="password"></md-input>
                </md-field>
                <md-button slot="footer" class="md-simple md-success md-lg">
                  Get Started
                </md-button>
              </login-card>
            </div>
          </div>
        </div>
      </div>
      <div class="md-layout">
        <div class="md-layout-item text-center">
          <md-button href="#/login" class="md-simple md-success md-lg">View Login Page</md-button>
        </div>
      </div>
    </div>
    <div id="fp-nav" class="right" style="margin-top: -53.5px;">
      <ul>
        <li>
          <a href="#allsearch" class="active"><span></span></a>
        </li>
        <li>
          <a href="#network" class=""><span></span></a>
        </li>
        <li>
          <a href="#statistics"><span></span></a>
        </li>
        <li>
          <a href="#infomation"><span></span></a>
        </li>
        <li>
          <a href="#footer"><span></span></a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import BasicElements from './components/BasicElementsSection';
import Navigation from './components/NavigationSection';
import SmallNavigation from './components/SmallNavigationSection';
import Tabs from './components/TabsSection';
import NavPills from './components/NavPillsSection';
import Notifications from './components/NotificationsSection';
import TypographyImages from './components/TypographyImagesSection';
import JavascriptComponents from './components/JavascriptComponentsSection';
import { LoginCard } from '@/components';

export default {
  components: {
    BasicElements,
    Navigation,
    SmallNavigation,
    Tabs,
    NavPills,
    Notifications,
    TypographyImages,
    JavascriptComponents,
    LoginCard,
  },
  name: 'index',
  bodyClass: 'index-page',
  props: {
    image: {
      type: String,
      default: require('@/assets/img/bigstock.jpg'),
    },
    logo: {
      type: String,
      default: require('@/assets/img/logo.png'),
    },
    signup: {
      type: String,
      default: require('@/assets/img/city.jpg'),
    },
    landing: {
      type: String,
      default: require('@/assets/img/landing.jpg'),
    },
    profile: {
      type: String,
      default: require('@/assets/img/profile.jpg'),
    },
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
      singleSelect: false,
      selected: [],
      headers: [
        {
          text: 'Dessert (100g serving)',
          align: 'start',
          value: 'name',
        },
        { text: 'Category', value: 'category' },
      ],
      desserts: [
        {
          name: 'Frozen Yogurt',
          category: 'Ice cream',
        },
        {
          name: 'Ice cream sandwich',
          category: 'Ice cream',
        },
        {
          name: 'Eclair',
          category: 'Cookie',
        },
        {
          name: 'Cupcake',
          category: 'Pastry',
        },
        {
          name: 'Gingerbread',
          category: 'Cookie',
        },
        {
          name: 'Jelly bean',
          category: 'Candy',
        },
        {
          name: 'Lollipop',
          category: 'Candy',
        },
        {
          name: 'Honeycomb',
          category: 'Toffee',
        },
        {
          name: 'Donut',
          category: 'Pastry',
        },
        {
          name: 'KitKat',
          category: 'Candy',
        },
      ],

      items: [
        {
          text: '1. 명예훼손',
        },
        {
          text: '2. 도로교통법',
        },
        {
          text: '3. 모욕죄',
        },
      ],
      model: 1,
    };
  },
  methods: {},
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`,
      };
    },
  },
};
</script>
<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
@import url(https://fonts.googleapis.com/css?family=Cabin:400);

// .webdesigntuts-workshop {
//   background: #151515;
//   height: 100%;
//   position: absolute;
//   text-align: center;
//   width: 100%;
// }

.webdesigntuts-workshop:before,
.webdesigntuts-workshop:after {
  content: '';
  display: block;
  height: 1px;
  left: 50%;
  margin: 0 0 0 -400px;
  position: absolute;
  width: 800px;
}

.webdesigntuts-workshop:before {
  background: #444;
  background: linear-gradient(left, #151515, #444, #151515);
  top: 192px;
}

.webdesigntuts-workshop:after {
  background: #000;
  background: linear-gradient(left, #151515, #000, #151515);
  top: 191px;
}

.webdesigntuts-workshop form {
  background: #111;
  background: linear-gradient(#1b1b1b, #111);
  border: 1px solid #000;
  border-radius: 5px;
  box-shadow: inset 0 0 0 1px #272727;
  display: inline-block;
  font-size: 0px;
  margin: 150px auto 0;
  padding: 20px;
  position: relative;
  z-index: 1;
}

.webdesigntuts-workshop input {
  background: #222;
  background: linear-gradient(#333, #222);
  border: 1px solid #444;
  border-radius: 5px 0 0 5px;
  box-shadow: 0 2px 0 #000;
  color: #888;
  display: block;
  float: left;
  font-family: 'Cabin', helvetica, arial, sans-serif;
  font-size: 13px;
  font-weight: 400;
  height: 40px;
  margin: 0;
  padding: 0 10px;
  text-shadow: 0 -1px 0 #000;
  width: 200px;
}

.ie .webdesigntuts-workshop input {
  line-height: 40px;
}

.webdesigntuts-workshop input::-webkit-input-placeholder {
  color: #888;
}

.webdesigntuts-workshop input:-moz-placeholder {
  color: #888;
}

.webdesigntuts-workshop input:focus {
  animation: glow 800ms ease-out infinite alternate;
  background: #222922;
  background: linear-gradient(#333933, #222922);
  border-color: #393;
  box-shadow: 0 0 5px rgba(0, 255, 0, 0.2), inset 0 0 5px rgba(0, 255, 0, 0.1), 0 2px 0 #000;
  color: #efe;
  outline: none;
}

.webdesigntuts-workshop input:focus::-webkit-input-placeholder {
  color: #efe;
}

.webdesigntuts-workshop input:focus:-moz-placeholder {
  color: #efe;
}

.webdesigntuts-workshop button {
  background: #222;
  background: linear-gradient(#333, #222);
  box-sizing: border-box;
  border: 1px solid #444;
  border-left-color: #000;
  border-radius: 0 5px 5px 0;
  box-shadow: 0 2px 0 #000;
  color: #fff;
  display: block;
  float: left;
  font-family: 'Cabin', helvetica, arial, sans-serif;
  font-size: 13px;
  font-weight: 400;
  height: 40px;
  line-height: 40px;
  margin: 0;
  padding: 0;
  position: relative;
  text-shadow: 0 -1px 0 #000;
  width: 80px;
}

.webdesigntuts-workshop button:hover,
.webdesigntuts-workshop button:focus {
  background: #292929;
  background: linear-gradient(#393939, #292929);
  color: #5f5;
  outline: none;
}

.webdesigntuts-workshop button:active {
  background: #292929;
  background: linear-gradient(#393939, #292929);
  box-shadow: 0 1px 0 #000, inset 1px 0 1px #222;
  top: 1px;
}

@keyframes glow {
  0% {
    border-color: #393;
    box-shadow: 0 0 5px rgba(0, 255, 0, 0.2), inset 0 0 5px rgba(0, 255, 0, 0.1), 0 2px 0 #000;
  }
  100% {
    border-color: #6f6;
    box-shadow: 0 0 20px rgba(0, 255, 0, 0.6), inset 0 0 10px rgba(0, 255, 0, 0.4), 0 2px 0 #000;
  }
}
</style>
