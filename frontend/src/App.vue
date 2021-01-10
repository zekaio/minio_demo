<template>
  <div id="app">
    <router-view :key="$route.fullPath"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
/* body {
  margin: 0;
  padding: 0;
  min-height: 100vh;
  min-width: 100vw;
} */
</style>
