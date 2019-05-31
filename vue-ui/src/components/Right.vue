<template>
    <div id="right" class="right">
      <div v-if="!postOrderSubmission && !isAdminUser">
        <p>Your Current Order is as below</p>
          <table>
            <th>Remove Item</th>
            <th>Item Name</th>
            <th>Quantity</th>f
            <th>Cost of the Item</th>
            <tr v-for="item in basket.orderItems" v-bind:key="item.id">
              <td><input text-align="centre" type="button" v-on:click="removeItemFromBasket(item)" value="x"/> 
              <td>{{ item.itemName }}</td>
              <td>{{ item.quantity }}</td>
              <td>{{ item.price * item.quantity }}</td>
            </tr>
            <tr v-if="basket.orderItems.length > 0">
              <td>Total Cost of the Order:</td>
              <td><p>rs. {{costOfOrder}}</p></td>
              <td></td>
              <td></td>
            </tr>
          </table>

          <div v-if="basket.orderItems.length > 0 && !isAuthenticated">
            <p>Sign in to Submit your Order</p>
            <button disabled>Submit Your Order</button>
          </div>
          <div v-else>
            <button v-if="basket.orderItems.length > 0 && isAuthenticated && !isAdminUser" v-on:click="submitOrder">Submit Your Order</button>
          </div>
      </div>
      <div v-else-if="recentOrder != null && postOrderSubmission">
        <p>Thanks for Placing your order. Your Submitted Order id is {{recentOrder.id}} and its summary is below:</p>
        <table>
          <th>Item Name</th>
          <th>Quantity</th>
          <th>Item Price</th>
          <tr v-for="item in recentOrder.items" v-bind:key="item.id">
            <td>{{item.menuItem.itemName}}</td>
            <td>{{item.quantity}}</td>
            <td>{{item.price}}</td>
          </tr>
          <tr>
              <td>Total Cost of the Order:</td>
              <td><p>rs. {{recentOrder.value}}</p></td>
              <td></td>
            </tr>
        </table>
      </div>
      <div v-else-if="errorSubmittingOrder && postOrderSubmission">
        <p> Sorry, we are unable to take your order due to following error.</p>
        <p> {{this.errorDescription}}</p>

      </div>
    </div>
</template>

<script>
import { eventBus } from "../main";

export default {
  created() {
    eventBus.$on("orderItemsModified", item => {
      let _this = this;
      let existingItemIndex = -1;

      _this.postOrderSubmission = false;

      for (let i = 0; i < this.basket.orderItems.length; i++) {
        if (this.basket.orderItems[i].id == item.id) {
          existingItemIndex = i;
          break;
        }
      }
      if (existingItemIndex < 0) this.basket.orderItems.push(item);
      else {
        var existingItem = this.basket.orderItems[existingItemIndex];
        existingItem.quantity = item.quantity;
        if(existingItem.quantity == 0){
          this.basket.orderItems.splice(existingItemIndex, 1);
        }
      }

      _this.calculateCostOfOrder();
    });
  },
  data() {
    return {
      costOfOrder: 0,
      basket: {
        userId: null,
        orderItems: []
      },
      postOrderSubmission: false,
      errorSubmittingOrder: false,
      errorDescription: null,
      submittedOrder: {}
    };
  },
  computed: {
    recentOrder () {
      return this.$store.state.recentOrder
    },
    isAuthenticated () {
      return this.$store.state.authenticated
    },
    isAdminUser () {
      if(this.$store.state.user == null) return false; 
      return  this.$store.state.user.admin
    }
  },
  methods: {
    removeItemFromBasket: function(item) {
      let _this = this;
      let indexOfItemToRemove = -1;
      for (let i = 0; i < this.basket.orderItems.length; i++) {
        if (this.basket.orderItems[i].id == item.id) {
          indexOfItemToRemove = i;
          break;
        }
      }
      this.basket.orderItems.splice(indexOfItemToRemove, 1);
      item.quantity = 0;
      eventBus.$emit("itemRemovedFromBasket", item);
      _this.calculateCostOfOrder();
    },
    calculateCostOfOrder: function() {
      this.costOfOrder = 0;
      for (let i = 0; i < this.basket.orderItems.length; i++) {
        this.costOfOrder +=
          this.basket.orderItems[i].price * this.basket.orderItems[i].quantity;
      }
    },
    submitOrder: function() {
      var basketToSubmit = this.getBasketToSubmit();
      this.$http
        .post("http://localhost:8080/orders/saveOrder", basketToSubmit, {
          "Access-Control-Allow-Origin": "http://localhost:3000"
        })
        .then(response => {
          this.clearBasket();
          this.postOrderSubmission = true;
            if (response.status == 200){
              this.submittedOrder = response.data;
              this.$store.dispatch('recentOrder',response.body);
            }
          },
          response =>  {
            this.clearBasket();
            this.postOrderSubmission = true;
            if(response.status != 200){
              this.$store.dispatch('recentOrder', null);
              this.errorSubmittingOrder = true;
              this.errorDescription = response.data.message;
            }
        });
    },
    clearBasket: function() {
      let _this = this;
      
      //clone basket items so we dont do concurrent modification .. 
      var basketItems = [];
      this.basket.orderItems.forEach(item => basketItems.push(item));

      for(let i=0; i<basketItems.length; i++){
        _this.removeItemFromBasket(basketItems[i]);
      }

      this.basket.orderItems = []
    },
    getBasketToSubmit: function() {
      var basketToSubmit = {
        userId: null,
        items: []
      };

      basketToSubmit.userId = this.$store.state.user.userId
      this.basket.orderItems.forEach(orderItem => {
        basketToSubmit.items.push({
          id: orderItem.id,
          itemName: orderItem.itemName,
          quantity: orderItem.quantity,
          itemNotes: ""
        });
      });

      return basketToSubmit;
    }
  }
};
</script>
<style>
.right {
  grid-area: right;
}

.right > p {
  font-size: 20px;
}
</style>