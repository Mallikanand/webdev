<template>
<div v-if="displayOrders">
    <div id="ordersDiv" v-if="showOrders">

      <div v-if="!Orders">
        <p>No orders to display</p>
      </div>
      <div v-else>
        <p>All Your Previous Orders</p>
        <table id="orders">
            <th>Order Id</th>
            <th>Order Value</th>
            <th>Placement Date</th>
            <th>Delivery Date</th>
            <th>Order Status</th>
            <tr v-bind:key="order.id" v-for="order in Orders" v-on:click="displayOrderDetails(order)">
                <td>{{ order.id }}</td>
                <td>{{ order.value }} </td>
                <td>{{ order.placementDate }} </td>
                <td>{{ order.deliveryDate }} </td>
                <td>{{ order.status }} </td>
            </tr>
        </table>
    </div>
    </div>
    <div id="orderDetailsDiv" v-if="!showOrders">
        <p><b> <u>This Order Details</u> </b></p>
        <table id="orders2">
            <tr><td>Order Id </td><td>{{ currentOrder.id }} </td></tr>
            <tr><td>Order Value </td><td> {{ currentOrder.value }} </td></tr>
            <tr><td>Order Placement Date </td><td> {{ currentOrder.placementDate }} </td></tr>
            <tr><td>Order Delivery Date </td><td> {{ currentOrder.deliveryDate }} </td></tr>
            <tr><td>Order Status </td><td>  {{ currentOrder.status }} </td></tr>
        </table>
        <p><b> <u>Order Contents</u> </b></p>
        <table id="orders2">
            <th>Item Id</th>
            <th>Item Name</th>
            <th>Item Price</th>
            <th>Item Quantity</th>
            <tr :key="item.id" v-for="item in currentOrder.items">
                <td>{{item.id}}</td>
                <td>{{item.itemName}}</td>
                <td>{{item.price}}</td>
                <td>{{item.quantity}}</td>
            </tr>
        </table>
        <input type="submit" value="Go Back" v-on:click="displayOrderHistory"/>
    </div>
</div>
</template>

<script>
import { eventBus } from "../main";

export default {
  props: ['displayOrders'],
  name: "Order",
  currentOrder: '',
  methods: {
    displayOrderDetails: function(order) {
      this.showOrders = false;
      this.currentOrder = order;

    },
    displayOrderHistory: function() {
      this.showOrders = true;
    }
  },
  computed: {
    Orders () {
      return this.$store.state.orders
    }
  },
  // created(){
  //   eventBus.$on('orderHistoryReloaded', ordersHistory => {
  //     this.Orders = ordersHistory;
  //   })
  // },
  data() {
    return {
      showOrders: true//,
      //Orders: [],
     /* Orders: [
          {
            id: 1,
            value: "100",
            placementDate: "10 JAN 2010",
            deliveryDate: "11 JAN 2010",
            status: "Delivered",
            orderItems: [
              {
                id: 1001,
                itemName: "Falafel Wrap",
                price: "35.00",
                quantity: 10
              },
              { id: 1002, itemName: "Miso Soup", price: "25.00", quantity: 10 }
            ]
          },
          {
            id: 2,
            value: "200",
            placementDate: "10 JUN 2010",
            deliveryDate: "11 JUN 2010",
            status: "Cancelled",
            orderItems: [
              {
                id: 1003,
                itemName: "Burrito",
                price: "35.00",
                quantity: 10
              },
              {
                id: 1004,
                itemName: "Tofu Yakisoba",
                price: "25.00",
                quantity: 10
              }
            ]
          },
          {
            id: 3,
            value: "200",
            placementDate: "10 JUN 2010",
            deliveryDate: "11 JUN 2010",
            status: "Delivered",
            orderItems: [
              {
                id: 1005,
                itemName: "Falafel Wrap",
                price: "35.00",
                quantity: 10
              },
              { id: 1006, itemName: "Miso Soup", price: "25.00", quantity: 10 }
            ]
          },
          {
            id: 4,
            value: "200",
            placementDate: "10 JUN 2010",
            deliveryDate: "11 JUN 2010",
            status: "Delivered",
            orderItems: [
              {
                id: 1007,
                itemName: "Falafel Wrap",
                price: "35.00",
                quantity: 10
              },
              { id: 1008, itemName: "Miso Soup", price: "25.00", quantity: 10 }
            ]
          },
          {
            id: 5,
            value: "200",
            placementDate: "10 JUN 2010",
            deliveryDate: "11 JUN 2010",
            status: "Delivered",
            orderItems: [
              {
                id: 1009,
                itemName: "Falafel Wrap",
                price: "35.00",
                quantity: 10
              },
              { id: 1010, itemName: "Miso Soup", price: "25.00", quantity: 10 }
            ]
          },
          {
            id: 6,
            value: "200",
            placementDate: "10 JUN 2010",
            deliveryDate: "11 JUN 2010",
            status: "Cancelled",
            orderItems: [
              {
                id: 1011,
                itemName: "Falafel Wrap",
                price: "35.00",
                quantity: 10
              },
              { id: 1012, itemName: "Miso Soup", price: "25.00", quantity: 10 }
            ]
          },
          {
            id: 7,
            value: "200",
            placementDate: "10 JUN 2010",
            deliveryDate: "11 JUN 2010",
            status: "Pending Delivery",
            orderItems: [
              {
                id: 1013,
                itemName: "Falafel Wrap",
                price: "35.00",
                quantity: 10
              },
              { id: 1014, itemName: "Miso Soup", price: "25.00", quantity: 10 }
            ]
          }
      ]*/
    };
  }
};
</script>
<style>
#orders, #orders2 {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
  font-size: 16px;
}

#orders td, #orders th,
#orders2 td, #orders2 th  {
  border: 1px solid #ddd;
  padding: 5px;
}

#orders tr:nth-child(even),
#orders2 tr:nth-child(even) {
  background-color: #f2f2f2;
}

#orders tr:hover {
  background-color: #ddd;
  font-size: 14px;
}

#orders th,
#orders2 th {
  padding-top: 5px;
  padding-bottom: 5px;
  text-align: center;
  background-color: #759b77;
  color: white;
}

#orderDetailsDiv p {
    font-size: 15px;
    text-align: left;
    
}
</style>