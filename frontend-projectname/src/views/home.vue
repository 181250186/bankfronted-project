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
      addEles() {
        this.$refs['ref_CJS'].addEles([
          {group: 'nodes', data: {id: '张三', name: '张三'}, classes: 'classes-A', position: {x: 100, y: 0}},
          {group: 'nodes', data: {id: '学生', name: '学生'}, classes: 'classes-A', position: {x: 700, y: 0}},
          {group: 'nodes', data: {id: '男性', name: '男性'}, classes: 'classes-A', position: {x: 150, y: 100}},
          {group: 'nodes', data: {id: '游戏', name: '游戏'}, classes: 'classes-A', position: {x: 300, y: 150}},
          {group: 'nodes', data: {id: '干饭', name: '干饭'}, classes: 'classes-A', position: {x: 0, y: 100}},
          {group: 'nodes', data: {id: '代码', name: '代码'}, classes: 'classes-A', position: {x: 250, y: 200}},
          {group: 'nodes', data: {id: '甜点', name: '甜点'}, classes: 'classes-A', position: {x: 400, y: 250}},
          {group: 'edges', data: {id: 'e0', name: 'is', source: '张三', target: '学生'},classes:'relationA'},
          {group: 'edges', data: {id: 'e1', name: 'is', source: '张三', target: '男性'},classes:'relationA'},
          {group: 'edges', data: {id: 'e2', name: 'like', source: '张三', target: '游戏'},classes:'relationA'},
          {group: 'edges', data: {id: 'e3', name: 'like', source: '张三', target: '甜点'},classes:'relationA'},
          {group: 'edges', data: {id: 'e4', name: 'like', source: '张三', target: '干饭'},classes:'relationA'},
          {group: 'edges', data: {id: 'e5', name: 'like', source: '男性', target: '游戏'},classes:'relationA'},
          {group: 'edges', data: {id: 'e6', name: 'like', source: '男性', target: '代码'},classes:'relationA'},
        ]);
      },

      //删除节点
      delEles() {
        this.$refs['ref_CJS'].delEles();
      },

    },
  }
</script>

<style scoped>
  .page{
    height: 100%; width: 100%; border: 1px solid #4977dc
  }

</style>
