import { axios } from '@/utils/request'
const api = {
  edgePre: '/api/edge'
}

// 增加边
// 返回{group: 'edges', data: {id: '', name: '', source: '', target: ''},classes:''}
export function addRelationAPI(data) {
  console.log(data);
  return axios({
    url: `${api.edgePre}/add`,
    method: 'POST',
    data,
  })
}

export function deleteRelationAPI(chartId,edge_id){
  return axios({
    url: `${api.edgePre}/delete/${chartId}/${edge_id}`,
    method: 'POST',
  })
}

export function updateRelationAPI(data){
  return axios({
    url: `${api.edgePre}/update`,
    method: 'POST',
    data
  })
}

//
export function getRelationAPI(chartId,edge_id){
  return axios({
    url: `${api.edgePre}/${chartId}/${edge_id}/get`,
    method: 'GET',
  })
}


//todo:获得知识图谱中边的数量
export function getEdgeNumberAPI(cid){
  return axios({
    url: `${api.edgePre}/getEdgeNumber/${cid}`,
    method: 'GET',
  })
}
