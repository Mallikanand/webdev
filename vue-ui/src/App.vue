<template>
  <div id="app" class="grid-container">
    <app-header></app-header>
    <app-left></app-left>
    <app-main></app-main>
    <app-right></app-right>
    <app-footer></app-footer>
    <router-view/>
  </div>
</template>
<script>
import Header from "./components/Header.vue";
import Left from "./components/Left.vue";
import Main from "./components/Main.vue";
import Right from "./components/Right.vue";
import Footer from "./components/Footer.vue";

export default {
  name: "app",
  data() {
    return {
      order: {
        id: ""
      }
    }
  },
  components: {
    "app-header": Header,
    "app-left": Left,
    "app-main": Main,
    "app-right": Right,
    "app-footer": Footer
  },

  methods: {}
};
</script>

<style>
.grid-container {
  display: grid;
  grid-template-areas:
    "header header header header header header"
    "left main main main right right"
    "footer footer footer footer footer footer";
  grid-gap: 1px;
  background-color: #88e0d9;
  padding: 1px;
}

.grid-container > div {
  background-color: rgba(255, 255, 255, 0.863);
  text-align: center;
  padding: 20px 20px;
}

@media (max-width: 600px) {
  .grid-container {
    grid-template-areas:
      "header header header header header header"
      "left left left left left left"
      "main main main main main main"
      "right right right right right right"
      "footer footer footer footer footer footer";
    grid-gap: 1px;
    background-color: #11f1f1;
    padding: 1px;
  }
}
</style>