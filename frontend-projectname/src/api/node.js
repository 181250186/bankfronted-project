import { axios } from '@/utils/request'
const api = {
  nodePre: '/api/node'
}

// 返回{group: 'nodes', data: {id: '', name: ''},classes:''}
export function addNodeAPI(data) {
  console.log("come here")
  console.log(data);
  // let that = this;
  // let result = null;
  // axios.post(`${api.nodePre}/add`, data).then(res => {
  //   console.log("axios", res)
  //   result = res
  // })
  // return result;
  return axios({
    url: `${api.nodePre}/add`,
    method: 'POST',
    data,
  })
}

export function updateNodeAPI(data){
  return axios({
    url: `${api.nodePre}/update`,
    method: 'POST',
    data:data
  })
}

export function deleteNodeAPI(chartId,node_id){
  console.log("come api")
  console.log(chartId,node_id)
  return axios({
    url: `${api.nodePre}/delete/${chartId}/${node_id}`,
    method: 'GET',
  })
}


//
export function getNodeAPI(chartId,node_id){
  return axios({
    url: `${api.nodePre}/${chartId}/${node_id}/get`,
    method: 'GET',
  })
 }

export function getNodeNumberAPI(cid){
  return axios({
    url:`${api.nodePre}/getNumberNode/${cid}`,
    method:'GET',
  })
}

