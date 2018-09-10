<template>
    <div id="left"  class="left">
        <ul class="menu">
            <li v-on:click="toggleAsRequired(menu)" v-for="menu in menus" :key="menu.option">
                <a :href="menu.option_ref">{{ menu.option  }}</a></li>
        </ul>
    </div>
</template>

<script>
import Main from "./Main.vue";
import { eventBus } from "../main";

export default {
  components: {
      "app-main": Main
  },
  data() {
    return {
      menus: [
        { option: "Who We Are", option_ref: "#aboutUs", active: "true" },
        {
          option: "Login To Your Account",
          option_ref: "#login",
          active: "false"
        },
        { option: "Our Menu", option_ref: "#menu", active: "false" },
        { option: "Your Orders", option_ref: "#orderHistory", active: "false" },
        { option: "Our Promise", option_ref: "#promise", active: "false" },
        {
          option: "Our Kitchen Gallery",
          option_ref: "#gallery",
          active: "false"
        }
      ]
    };
  },
  methods: {
      toggleAsRequired: function(selectedMenuOption){ 
        eventBus.$emit('leftMenuClick',selectedMenuOption);
      }
  }
};
</script>
<style >
.menu {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 100%;
  text-align: left;
}

.menu > li a {
  display: block;
  color: #000;
  text-decoration: none;
  padding: 0px 0px;
  font-size: 20px;
}

.menu > li a:hover {
  color: white;
  background-color: #a4a4a4;
}

.active {
  background-color: green;
  color: white;
}

.left {
  grid-area: left;
}
</style>
