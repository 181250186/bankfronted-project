import {
  saveChartAPI,
  findChartAPI,
  deleteNodeAndEdgeAPI,
  withdrawAPI
} from '@/api/chart'
import {message} from 'ant-design-vue'
import {getAllChartAPI} from "../../api/chart";
import cytoscape from 'cytoscape';

const chart = {
  state: {
    cy:cytoscape(),
    chartId: '',
    chartName: '',
    chartNameVisible: false,//上传文件时为图表命名
    uploadVisible: false,//上传文件弹窗
    elementsArray: [],
    chartArray:[],
    nodeId:'',
    //显示提示已存在
    messageVisible: false,
    fileList:[],
  },

  mutations: {
    set_chartId: function (state, data) {
      console.log(data)
      state.chartId = data
    },
    set_chartName: function (state, data) {
      state.chartName = data
    },
    set_chartNameVisible: function (state, data) {
      state.chartNameVisible = data
    },
    set_uploadVisible: function (state, data) {
      state.uploadVisible = data
    },
    set_elementsArray: function (state, data) {
      state.elementsArray = data
    },
    set_nodeId: function (state, data) {
      state.nodeId = data
    },
    set_chartArray: function (state, data) {
      state.chartArray = data
    },
    set_messageVisible: function (state, data) {
      state.messageVisible = data
    },
    set_fileList: function (state, data) {
      state.fileList = data
    },
    set_cy: function (state,data){
      state.cy = data;
    },
  },

  actions: {
    //上传数据以及表名称，返回id和chart_name
    saveChart: async ({commit, state},data) => {
      const res = await saveChartAPI(data.uploadArray, data.localChartName)
      if (res) {
        commit('set_chartId', res.id)
        commit('set_chartNameVisible', false)
        commit('set_uploadVisible', false)
        commit('set_chartName',data.localChartName)
        commit('set_elementsArray',data.uploadArray)
        message.success('上传成功')
      } else {
        message.error('上传失败')
      }

    },

    findChart: async ({commit, state},chartId) => {

      const res = await findChartAPI(chartId)
      if (res) {
        commit('set_elementsArray', res)
      }
      return res;
    },

    deleteNodeAndEdge: async ({commit, state}) => {
      const res = await deleteNodeAndEdgeAPI(state.nodeId, state.chartId)
      if (res) {
        commit('set_elementsArray', res)
      }
    },

    withdraw: async ({commit, state}) => {
      const res = await withdrawAPI(state.chartId)
      if (res) {
        commit('set_elementsArray', res)
      }
    },
    getAllChart:async ({commit, state}) => {
      const res = await getAllChartAPI()
      if (res) {
        commit('set_chartArray', res)
      }
    },
  },

}
export default chart
