import {
  addRelationAPI,
  deleteRelationAPI,
  updateRelationAPI,
  getRelationAPI,
  getEdgeNumberAPI
} from '@/api/edge'
import {message} from 'ant-design-vue'

const edge = {
  state: {
    addRelationParams: {
      chartId: '',
      classes: '',
      data: {},
      group: '',
      source: [],
    },
    updateRelationParams: {
      chartId: '',
      classes: '',
      data: {},
      group: '',
    },
    addSonVisible: false,
    edgeId: '',
    dialogRelationVisible: false,
    //用于保存增加关系时用户输入的节点名称
    sourceName: '',
    targetName: '',
    relationArray: [],
  },
  mutations: {
    set_addRelationParams: function (state, data) {
      state.addRelationParams = {
        ...state.addRelationParams,
        ...data,
      }
    },
    set_updateRelationParams: function (state, data) {
      state.updateRelationParams = {
        ...state.updateRelationParams,
        ...data,
      }
    },
    set_addSonVisible: function (state, data) {
      state.set_addSonVisible = data
    },
    set_edgeId: function (state, data) {
      state.edgeId = data
    },
    set_dialogRelationVisible: function (state, data) {
      state.dialogRelationVisible = data
    },
    set_sourceName: function (state, data) {
      state.sourceName = data
    },
    set_targetName: function (state, data) {
      state.targetName = data
    },
    set_relationArray: function (state, data) {
      state.relationArray = data
    },
  },
  actions: {
    addRelation: async ({state, commit},relation) => {
      console.log("come modules")
      const res = await addRelationAPI(relation)
      // if (res) {
      //   commit('set_addRelationParams', {
      //     chartId: '',
      //     classes: '',
      //     data: {},
      //     group: '',
      //     source: [],
      //   })
      //   commit('set_addSonVisible', false)
      //   message.success('添加成功')
      //   dispatch('findChart')
      // } else {
      //   message.error('添加失败')
      // }
      return res;
    },

    deleteRelation: async ({state, commit},info) => {
      const res = await deleteRelationAPI(info[0],info[1])
      if (res) {
        message.success('删除成功')
      } else {
        message.error('删除失败')
      }
    },

    updateRelation: async ({state, commit}) => {
      const res = await updateRelationAPI(state.updateRelationParams)
      if (res) {
        message.success('修改成功')
      } else {
        message.error('修改失败')
      }
    },

    getRelation: async ({state, commit}) => {
      const res = await getRelationAPI(state.chartId, state.edgeId)
      if (res) {
        //返回的是什么，没有用到这个接口
        message.success('获取成功')
      } else {
        message.error('获取失败')
      }
    },

    getEdgeNumber:async  ({state, commit},cid)=>{
      console.log("store edge")
      const res=getEdgeNumberAPI(cid);
      return res;
    },

  },
}
export default edge
