/* eslint-disable */ // eslint-disable-next-line
<template>
  <div id="material-kit">
    <v-app id="app">
      <div :class="{ 'nav-open': NavbarStore.showNavbar }">
        <router-view name="header" />
        <div>
          <router-view />
        </div>
        <router-view name="footer" />
      </div>
    </v-app>
  </div>
</template>
/* eslint-enable */
