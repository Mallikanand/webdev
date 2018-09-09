<template>
    <div id="left"  class="left">
        <ul class="menu">
            <li v-on:click="toggleIfRequired(menu)" v-for="menu in menu_options" :key="menu.option">
                <a :href="menu.option_ref">{{ menu.option  }}</a></li>
        </ul>
    </div>
</template>

<script>
  import Main from "./Main.vue";

export default {
  components: {
      "app-main": Main
  },
  data() {
    return {
      menu_options: [
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
      toggleIfRequired: function(currentOption){ 
         if(currentOption.option_ref == '#menu'){
             //clicked on menu - so donot show Orders 
             Main.displayOrderHistory = false;
         }
         if(currentOption.option_ref == '#orderHistory'){
             //clicked on Orders - so donot show menu 
             Main.displayMenu = false;
         }
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
