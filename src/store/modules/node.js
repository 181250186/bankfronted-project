import {
  addNodeAPI,
  updateNodeAPI,
  deleteNodeAPI,
  getNodeAPI,
  getNodeNumberAPI
} from '@/api/node'
import {message} from 'ant-design-vue'

const node={
  state :{
    addNodeParams:{
      chartId:'',
      group: '',
      data: {},
      classes:'',
      position:{x:0,y:0},
    },
    addNodeVisible:false,
    dialogVisible:false,
    nodeId:'',
    updateNodeParams:{
      chartId:'',
      group: '',
      data: {},
      classes:'',
      position:{x:0,y:0}
    },
    positionX:0,
    positionY:0,
    nodeArray: [],
  },
  mutations:{
    set_addNodeParams: function (state, data) {
      console.log("state add")
      state.addNodeParams = {
        ...state.addNodeParams,
        ...data,
      }
    },
    set_updateNodeParams: function (state, data) {
      state.updateNodeParams = {
        ...state.updateNodeParams,
        ...data,
      }
    },
    set_addNodeVisible: function (state, data) {
      state.addNodeVisible = data
    },
    set_dialogVisible: function (state, data) {
      state.dialogVisible = data
    },
    set_nodeId: function (state, data) {
      state.nodeId = data
    },
    set_positionX: function (state, data) {
      state.positionX = data
    },
    set_positionY: function (state, data) {
      state.positionY = data
    },
    set_nodeArray: function (state, data) {
      state.nodeArray = data
    },



  },
  actions:{
    addNode: async ({state, commit}, node) => {
      const res = await addNodeAPI(node)
      // console.log("res++", res)
      // if (res) {
      //   commit('set_addNodeParams', {
      //     chartId: '',
      //     classes: '',
      //     data: {},
      //     group: '',
      //   })
      //   commit('set_addNodeVisible', false)
      //   message.success('添加成功')
      //   // dispatch('findChart')
      // } else {
      //   message.error('添加失败')
      // }

      // console.log("123")
      // console.log(res)
      return res
    },

    deleteNode: async ({state, commit},info) => {
      console.log("come modules")
      console.log("go api")
      console.log(info)
      const res = await deleteNodeAPI(info[0],info[1])
      if (res) {
        message.success('删除成功')
      } else {
        message.error('删除失败')
      }
    },

    updateNode: async ({state, commit}) => {
      const res = await updateNodeAPI(state.updateNodeParams)
      if (res) {
        message.success('修改成功')
      } else {
        message.error('修改失败')
      }
    },

    getNode: async ({state, commit}) => {
      const res = await getNodeAPI(state.chartId, state.nodeId)
      if (res) {
        //返回的是什么，没有用到这个接口
        message.success('获取成功')
      } else {
        message.error('获取失败')
      }
    },

    getNodeNumber:async ({state, commit},cid)=>{
      console.log("store node");
      const res=await getNodeNumberAPI(cid);
      return res;
    },

  },
}
export default node
