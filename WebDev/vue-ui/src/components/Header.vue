<template>
    <div id="header" class="header">
        <header>{{ title }} </header>
    </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      title: "Welcome to Your Favourite Lunch Service"
    };
  }
};
</script>
<style>
.header {
  grid-area: header;
}
</style>