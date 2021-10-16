<style scoped>
  .tools {
    display: inline-block;
    height: 45px;
    width: 45px;
    
    vertical-align: middle;
  }

  .el-header{
    background-color: #313131;
    display:flex;
    justify-content: center;
    padding: 0;
    color: #fff;
    align-items: center;
    font-size: 20px;
  }

  .el-aside{
    background-color: #313131;
  }

  .el-main{
    background-color: #b2b2ae;
  }

  .home-container{
    height: 100%;
  }
  .center-center {
    height: 100%;
    display: flex;
    align-items: center;
    align-content: center;
    justify-items: center;
    justify-content: center;
  }
</style>

<script>
  export default {
    data() {
      return {
        queryInfo:{
          query:'',
          pagenum:1,
          pagesize:2
        },
        total:0
      }
    },

    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleSizeChange(newSize){
        console.log(newSize)
      },
      handleCurrentChange(newPage){
        console.log(newPage)
      }
    }
  }
</script>

<template>
  <el-container class="home-container">
    <el-header>
      <div>
        <span>外部规章管理系统</span>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>制度管理</span>
            </template>
            <el-menu-item-group>
              <template slot="title">外规管理</template>
              <el-menu-item index="1-1">
                <i class="el-icon-setting"></i>
                <span slot="title">外规维护</span>
              </el-menu-item>
            </el-menu-item-group>
            <!--<el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项1</el-menu-item>
            </el-submenu>-->
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">导航二</span>
          </el-menu-item>
          <el-menu-item
            index="3"
            disabled
          >
            <i class="el-icon-document"></i>
            <span slot="title">导航三</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">导航四</span>
          </el-menu-item>

          <el-menu-item index="12">
            <i class="el-icon-setting"></i>
            <span slot="title">导航12</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <div>
          <h3>外观列表</h3>
          <br/>
          <div>
            <el-row :gutter="100">
            <el-col :span="10">
            <el-input placeholder="请输入内容" v-model="input1">
              <template slot="prepend">法规标题：</template>
            </el-input>
            </el-col>

            <el-col :span="10">
              <el-input placeholder="请输入内容" v-model="input1">
                <template slot="prepend">发布日期：</template>
              </el-input>
            </el-col>
            </el-row>
          </div>
          <div>
            <el-row :gutter="100">
            <el-col :span="10">
              <el-input placeholder="请输入内容" v-model="input1">
                <template slot="prepend">实施日期：</template>
              </el-input>
            </el-col>

            <el-col :span="10">
              <el-input placeholder="请输入内容" v-model="input1">
                <template slot="prepend">发文部门：</template>
              </el-input>
            </el-col>
            </el-row>
          </div>
          <div>

            效力级别：
          <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
            状态：
            <el-select v-model="value" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>

          </div>
          <br/>
          <el-button>查询</el-button>

        </div>
        <br/>
        <br/>
        <el-row>
          <el-button type="primary">新建</el-button>
          <el-button type="success">修改</el-button>
          <el-button type="info">删除</el-button>
          <el-button type="warning">发布</el-button>
          <el-button type="danger">废止</el-button>
        </el-row>
        <br/>
        <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="title"
            label="法规标题"
            width="180">
          </el-table-column>
          <el-table-column
            prop="organization"
            label="发文机构"
            width="180">
          </el-table-column>
          <el-table-column
            prop="time1"
            label="发布日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="time2"
            label="实施日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="grade"
            label="效力级别"
            width="180">
          </el-table-column>
          <el-table-column
            prop="rulestate"
            label="外规内化状态"
            width="180">
          </el-table-column>
          <el-table-column
            prop="state"
            label="状态"
            width="180">
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum"
          :page-sizes="[1, 2, 5, 10]"
          :page-size="queryInfo.pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>

      </el-main>
    </el-container>
  </el-container>


