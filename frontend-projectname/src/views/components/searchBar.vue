<template>
  <div style="margin-top: 15px; text-align: center; width: 80%">
    <el-input
      placeholder="搜索节点或关系"
      v-model="input"
      class="input-with-select"
    >
      <el-select v-model="type" slot="prepend" placeholder="请选择">
        <el-option label="节点" value="1"></el-option>
        <el-option label="关系" value="2"></el-option>
      </el-select>
      <el-button
        slot="append"
        icon="el-icon-search"
        @click="search"
      ></el-button>
    </el-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: "1",
      input: "",
    };
  },
  methods: {
    search() {
      this.$emit("handleSearch", { input: this.input, type: this.type });
    },
  },
};
</script>

<style>
.el-select {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
.input-with-select {
  width: 800px;
}
</style>
