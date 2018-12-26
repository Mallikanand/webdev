<template>
    <div id="header" class="header">
        <header><h1>{{ title }} </h1></header>
        <div align="right">
          <div v-if="!authenticated">
            <router-link :to="{name:'Login'}">Sign In</router-link>
          </div>
          <div v-else>
            <p>Dear {{$store.state.user.firstName}}, Welcome to Your Favourite Food Service! </p>
            <form @submit.prevent="logout">
              <button type="submit">Sign Out</button>
            </form>
            <!-- <router-link :to="{name:'Logout'}">Sign Out</router-link> -->
          </div>
        </div>
    </div>
</template>

<script>
export default {
  name: "Header",
  computed: {
      authenticated () {
          return this.$store.state.authenticated
      }
    },
  data() {
    return {
      title: "Hyderabad Lunch Box"
    }
  },
  methods: {
    logout () {
      this.$http
        .post("http://localhost:8080/logout")
        .then(() => {
          this.$store.dispatch("logoutSuccess");
        })
    }
    // login: function(){
    //   this.$http.get("http://localhost:8080/login/facebook", {
    //     // headers: {
    //     //   'Access-Control-Allow-Origin': 'http://localhost:8080'
    //     // }
    //   })
    //   .then(function(response){
    //         response.status;
    //   })        
    // }
  }
};
</script>
<style>
.header {
  grid-area: header;
}
</style>