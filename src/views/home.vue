<template>
  <div class="page">
    <CJS ref="ref_CJS"></CJS>
  </div>
</template>

<script>
  import CJS from './components/cjs';
  import {findChartAPI, saveChartAPI} from "@/api/chart";

  export default {
    name: "Test",
    components: {CJS,},
    watch: {},
    mounted() {
      this.addEles();
    },
      methods: {
      //TODO:模拟数据
        
    },
  }
</script>

<style scoped>
  .page{
    height: 100%; width: 100%; border: 1px solid #4977dc
  }

</style>
