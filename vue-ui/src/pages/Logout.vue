<template>
  <div>
    <p>Are you sure you want to signout?</p>
    <form @submit.prevent="logout">
      <button type="submit">Sign Out</button>
    </form>

  </div>
</template>
<script>
export default {
  methods: {
    logout () {
      this.$http
        .post("http://localhost:8080/logout")
        .then(() => {
          this.$store.dispatch("logoutSuccess");
        })
    }
  }
}
</script>

