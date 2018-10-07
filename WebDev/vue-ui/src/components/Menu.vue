<template>
<div v-if="displayMenu">

    <ul class="menuList">
        <li v-for="regionalItems in menuItemsMap" v-bind:key="regionalItems.region">
            <div id="">
                <br>
                <table id="menuTable">
                    <th>{{regionalItems.region}}</th>
                    <tr v-for="item in regionalItems.items" v-bind:key="item.id">
                        <td id="itemName">{{item.itemName}}</td>
                        <!--td id="itemCategory">{{item.category}}</td-->
                        <td id="itemDesc">{{item.desc}}</td>
                        <td id="itemPrice">rs. {{item.price}}</td>
                        <td>
                            <input v-on:click="addToBasket(item)"       type="button"   value="+"/>
                            <input v-on:blur ="adjustQuantity(item)"    type="text"     v-model="item.newQuantity" size="4"/>
                            <input v-on:click="removeFromBasket(item)"  type="button"   value="-"/>
                        </td>
                    </tr>
                </table>
            </div>
        </li>
    </ul>

</div>
</template>

<script>
import { eventBus } from "../main";

export default {
  props: ["displayMenu"],
  name: "Menu",
  created() {
    eventBus.$on('itemRemovedFromBasket', item => {
        for(let i=0;i<this.menuItemsMap.length;i++){
            let regionalItems = this.menuItemsMap[i].items;
            for(let j=0;j<regionalItems.length;j++){
                if(regionalItems[j].id == item.id){
                    regionalItems[j].newQuantity = regionalItems[j].quantity = item.quantity;
                    break;
                }
            }
        }
    });
  },
  methods: {
    addToBasket: function(item) {
        let _this = this;
        item.quantity++;
        item.newQuantity = item.quantity;
        _this.publishOrderModifiedEvent(item);
    },
    removeFromBasket: function(item) {
        let _this = this;
      if(item.quantity>0){
          item.quantity--;
          item.newQuantity = item.quantity;
          _this.publishOrderModifiedEvent(item);
      }
    },
    adjustQuantity: function(item){
        let _this = this;
        if(item.newQuantity>=0 && item.newQuantity != item.quantity){
            item.quantity = item.newQuantity;
        _this.publishOrderModifiedEvent(item);
        }else{
            item.newQuantity = item.quantity;
        }
    },
    publishOrderModifiedEvent: function(item){
        eventBus.$emit('orderItemsModified',item);
    }
  },
  data() {
    return {
      menuItemsMap: [
        {
          region: "ITALIAN",
          items: [
            {
              id: 1001,
              itemName: "PASTA",
              desc:
                "Description of PASTA and its ingredients, nutrition info .. ",
              menuType: "ITALIAN",
              category: "FOOD",
              price: "45.00",
              quantity: 0,
              newQuantity: 0
            },
            {
              id: 1002,
              itemName: "PIZZA",
              desc:
                "Type of Pizza, double crest of single, toppings, nutrition info etc.. ",
              menuType: "ITALIAN",
              category: "FOOD",
              price: "45.00",
              quantity: 0,
              newQuantity: 0
            },
            {
              id: 1003,
              itemName: "LASAGNE",
              desc: "Type of Lasange, ingredients, nutrition info etc..",
              menuType: "ITALIAN",
              category: "FOOD",
              price: "45.00",
              quantity: 0,
              newQuantity: 0
            }
          ]
        },
        {
          region: "MEXICAN",
          items: [
            {
              id: 1004,
              itemName: "BURRITO",
              desc: "Burrito speciality, Ingredients, nutrition info etc ... ",
              menuType: "MEXICAN",
              category: "FOOD",
              price: "45.00",
              quantity: 0,
              newQuantity: 0
            }
          ]
        },
        {
          region: "JAPANESE",
          items: [
            {
              id: 1005,
              itemName: "MISO SOUP",
              desc: "Details of the soup .. ",
              menuType: "JAPANESE",
              category: "SOUP",
              price: "25.00",
              quantity: 0,
              newQuantity: 0
            },
            {
              id: 1006,
              itemName: "TOFU CURRY",
              desc:
                "Details and introduction to the delicacy, Nutrition info etc ...",
              menuType: "JAPANESE",
              category: "FOOD",
              price: "45.00",
              quantity: 0,
              newQuantity: 0
            },
            {
              id: 1007,
              itemName: "TOFU YAKISOBA",
              desc:
                "Details and introduction to the delicacy, Nutrition info etc ...",
              menuType: "JAPANESE",
              category: "FOOD",
              price: "45.00",
              quantity: 0,
              newQuantity: 0
            },
            {
              id: 1008,
              itemName: "VEG TANMEN",
              desc:
                "Details and introduction to the delicacy, Nutrition info etc ...",
              menuType: "JAPANESE",
              category: "NOODLE SOUP",
              price: "45.00",
              quantity: 0,
              newQuantity: 0
            }
          ]
        },
        {
          region: "OTHER",
          items: [
            {
              id: 1009,
              itemName: "SALAD",
              desc: "Type of Salad, Ingredients, Nutrition info etc .. ",
              menuType: "OTHER",
              category: "FOOD",
              price: "45.00",
              quantity: 0,
              newQuantity: 0
            }
          ]
        }
      ]
    };
  }
};
</script>
<style>
.menuList {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
  font-size: 12px;
}
#menuTable {
  font-family: Arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
  font-size: 18px;
}

#menuTable th {
  border: 1px solid #ddd;
  padding: 5px;
}

#menuTable tr:nth-child(odd) {
  background-color: #f2f2f2;
}

#menuTable tr:nth-child(even) {
  background-color: #e5e5e5;
}

#menuTable td {
  border: 1px solid #ddd;
  padding: 10px;
}

#menuTable tr:hover {
  background-color: #ddd;
  font-size: 17px;
}

.menuList > li {
  display: block;
  color: #000;
  text-decoration: none;
  padding: 0px 0px;
  font-size: 20px;
}

#itemName {
  text-align: left;
  padding: 10px;
  width: 200px;
}

#itemCategory {
  text-align: center;
  padding: 10px;
  width: 40px;
}
#itemDesc {
  text-align: left;
  padding: 10px;
  width: 400px;
}
#itemPrice {
  text-align: right;
  padding: 10px;
  width: 80px;
}
</style>