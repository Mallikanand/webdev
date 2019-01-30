<template>
    <div id="footer" class="footer">
        <footer>{{copyright}}</footer>
    </div>
</template>

<script>
export default {
  data() {
    return {
      copyright: 'CopyRight 2018 ... '
    };
  }
};
</script>
<style>
.footer {
  grid-area: footer;
  font-size: 10px;
}

</style>