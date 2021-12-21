<template>
  <el-menu
    default-active="0"
    class="el-menu-vertical"
    @open="handleOpen"
    @close="handleClose"
    @select="handleClick"
    :collapse="isCollapse"
  >
    <el-menu-item @click="emit(1)">
      <i class="el-icon-add-location"></i>
      <span slot="title">添加节点</span>
    </el-menu-item>
    <el-menu-item @click="emit(2)">
      <i class="el-icon-back"></i>
      <span slot="title">恢复视图</span>
    </el-menu-item>
    <el-menu-item @click="emit(3)">
      <i class="el-icon-more"></i>
      <span slot="title">排版模式</span>
    </el-menu-item>
    <el-menu-item @click="emit(4)">
      <i class="el-icon-s-marketing"></i>
      <span slot="title">力导图模式</span>
    </el-menu-item>
    <el-menu-item @click="emit(5)">
      <i class="el-icon-s-operation"></i>
      <span slot="title">自定义节点</span>
    </el-menu-item>
    <el-menu-item @click="emit(6)">
      <i class="el-icon-magic-stick"></i>
      <span slot="title">展示效果调节</span>
    </el-menu-item>
    <el-menu-item @click="emit(7)">
      <i class="el-icon-picture-outline"></i>
      <span slot="title">图片导出</span>
    </el-menu-item>
    <el-menu-item @click="emit(8)">
      <i class="el-icon-document"></i>
      <span slot="title">文本导出</span>
    </el-menu-item>
    <el-menu-item @click="emit(9)">
      <i class="el-icon-s-opportunity"></i>
      <span slot="title">操作指南</span>
    </el-menu-item>
    <el-menu-item v-if="isCollapse" @click="collapse()">
      <i class="el-icon-caret-right"></i>
      <span slot="title">展开菜单</span>
    </el-menu-item>
    <el-menu-item v-else @click="collapse()">
      <i class="el-icon-caret-left"></i>
      <span slot="title">收起菜单</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      isCollapse: false,
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    collapse() {
      this.isCollapse = !this.isCollapse;
    },
    emit(key) {
      this.$emit("operation", key);
    },
  },
};
</script>

<style scoped>
.el-menu-item.is-active {
  color: #303133;
}
.el-menu-item.is-active i {
  color: #909399;
}
.el-menu-item:active {
  color: rgb(64, 158, 255);
}
</style>
