<template>
    <div id="main" class="main">
        <show-menu :displayMenu="displayMenu"></show-menu>
        <show-order :displayOrders="displayOrderHistory"></show-order>
    </div>
</template>

<script>
import Order from "./Order.vue";
import Menu from "./Menu.vue";
import { eventBus } from "../main";

export default {
  data() {
    return {
      displayMenu: true,
      displayOrderHistory: false
    };
  },
  components: {
    "show-order": Order,
    "show-menu": Menu
  },
  created() {
    eventBus.$on('leftMenuClick', selectedMenuOption => {
      if (selectedMenuOption.option_ref == '#menu') {
        this.displayMenu = true;
        this.displayOrderHistory = false;

        this.$http.get("http://localhost:8080/menu")
          .then(function(response){
            eventBus.$emit('menuReloaded',response.body);
          })
        
      }
      if (selectedMenuOption.option_ref == '#orderHistory') {
        this.displayMenu = false;
        this.displayOrderHistory = true;
           
        this.$http.get("http://localhost:8080/orders/getAllOrders"
        /*,
                                              {
                                                headers: {
                                                  'Access-Control-Allow-Origin': 'http://localhost:3000'
                                              }
                                              }
          */                                    
         ).then(function(response){
           eventBus.$emit('orderHistoryReloaded',response.body);
         })
        
    
      }
    });
  }
};
</script>
<style>
.main {
  grid-area: main;
}

.main > p {
  font-size: 20px;
}
</style>