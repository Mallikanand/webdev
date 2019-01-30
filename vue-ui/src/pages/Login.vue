<template>
  <div>
    <form @submit.prevent="login">
    Enter your User Id:  <input type="text" v-model="loginForm.userId"/>
    Enter your Password: <input type="password" v-model="loginForm.password"/>
    <button type="submit">Login</button>
    </form>
  </div>
</template>
<script>
export default {
  data () {
    return {
      loginForm: {
        userId: null,
        password: null
      }
    }
  },
  methods: {
    login: function () {
      this.$http
        .post("http://localhost:8080/login", {
          userId: this.loginForm.userId,
          password: this.loginForm.password
        })
        .then(response => {
          this.$store.dispatch("loginSuccess", response.data);
          this.loginForm.password = null;
          this.$router.push('/')
        })
    },
    logout: function() {
      this.$store.dispatch("logout");
    }
  }
}
</script>
