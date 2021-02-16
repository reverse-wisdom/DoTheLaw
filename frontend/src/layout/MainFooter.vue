<template>
  <footer class="footer" :class="{ [`footer-${type}`]: type }" :data-background-color="backgroundColor">
    <div class="container">
      <div class="copyright">
        &copy; {{ year }}, made with
        <md-icon>favorite</md-icon>
        by Team Perfect for a better web.
      </div>
      <nav>
        <ul>
          <li>
            <a href="">
              개인정보 처리방침
            </a>
          </li>
          <li>
            <a href="">
              사이트 이용안내
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String,
  },
  data() {
    return {
      year: new Date().getFullYear(),
      items: ['Foo', 'Bar', 'Fizz', 'Buzz'],
    };
  },
};
</script>
<style></style>
