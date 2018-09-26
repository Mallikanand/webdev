<template>
    <div id="right" class="right">
      <p>Your Current Order is as below</p>
        <table>
          <th>Remove Item</th>
          <th>Item Name</th>
          <th>Quantity</th>
          <th>Cost of the Item</th>
          <tr v-for="item in orderItems" v-bind:key="item.id">
            <td><input text-align="centre" type="button" v-on:click="removeItemFromBasket(item)" value="x"/> 
            <td>{{ item.itemName }}</td>
            <td>{{ item.quantity }}</td>
            <td>{{ item.price * item.quantity }}</td>
          </tr>
          <tr v-if="orderItems.length > 0">
            <td>Total Cost of the Order:</td>
            <td><p>rs. {{costOfOrder}}</p></td>
            <td></td>
            <td></td>
          </tr>
        </table>
    </div>
</template>

<script>
import { eventBus } from '../main';

export default {

  created() {
    eventBus.$on('orderItemsModified', item => {
      let _this = this;
      let existingItemIndex = -1;
      for(let i=0; i<this.orderItems.length;i++){
        if(this.orderItems[i].id == item.id){
          existingItemIndex = i; 
          break;
        }
      }
      if(existingItemIndex<0)
        this.orderItems.push(item);
      else{
        var existingItem = this.orderItems[existingItemIndex];
          existingItem.quantity = item.quantity;
        }

        _this.calculateCostOfOrder();
    });
  },
  data() {
    
    return {
      costOfOrder:0,
      orderItems:[]
    };
  },
  methods:{
    removeItemFromBasket: function(item){
      let _this = this;
      let indexOfItemToRemove = -1;
      for(let i=0; i<this.orderItems.length;i++){
        if(this.orderItems[i].id == item.id){
          indexOfItemToRemove = i;
          break;
        }
      }
      this.orderItems.splice(indexOfItemToRemove,1);
      item.quantity=0;
      eventBus.$emit('itemRemovedFromBasket',item);
      _this.calculateCostOfOrder();
    },
    calculateCostOfOrder: function(){
      
      this.costOfOrder = 0;
      for(let i=0; i<this.orderItems.length;i++){
        this.costOfOrder += (this.orderItems[i].price * this.orderItems[i].quantity);
      }
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