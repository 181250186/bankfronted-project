import { axios } from '@/utils/request'
const api = {
  chartPre: '/api/chart'
}

//
export function saveChartAPI(data) {
  return axios({
    url: `${api.chartPre}/save`,
    method: 'POST',
    data,
  })
}

//通过图id获取所有图的数据
export function findChartAPI(chartId) {
 //console.log(chartId);
  return axios({
    url: `${api.chartPre}/findChart/${chartId}`,
    method: 'GET'
  })
}

//删除结点和对应的边
export function deleteNodeAndEdgeAPI(nodeId,chartId) {
  return axios({
    url: `${api.chartPre}//withdraw/${nodeId}/${chartId}`,
    method: 'GET',
  })
}
