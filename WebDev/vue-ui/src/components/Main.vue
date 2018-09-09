<template>
    <div id="main" class="main">
        <show-menu :displayMenu="displayMenu"></show-menu>

        <show-order :displayOrders="displayOrderHistory"></show-order>
    </div>
</template>

<script>
import Order from './Order.vue'
import Menu from './Menu.vue'

export default {
  data() {
      return{
        displayMenu: true,
        displayOrderHistory: true
    }
  },
  components:{
      'show-order': Order,
      'show-menu': Menu
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