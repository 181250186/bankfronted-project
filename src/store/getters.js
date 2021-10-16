const getters = {
  //chart
  cy: state=>state.chart.cy,
  chartId: state=>state.chart.chartId,
  chartName: state=>state.chart.chartName,
  chartNameVisible: state=>state.chart.chartNameVisible,//上传文件时为图表命名
  uploadVisible: state=>state.chart.uploadVisible,//上传文件弹窗
  elementsArray: state=>state.chart.elementsArray,
  chartArray:state=>state.chart.chartArray,
  messageVisible:state=>state.chart.messageVisible,
  fileList:state=>state.chart.fileList,

  //edge
  addRelationParams: state=>state.edge.addRelationParams,
  updateRelationParams: state=>state.edge.updateRelationParams,
  addSonVisible: state=>state.edge.addSonVisible,
  edgeId: state=>state.edge.edgeId,
  dialogRelationVisible:state=>state.edge.dialogRelationVisible,
  sourceName:state=>state.edge.sourceName,
  targetName:state=>state.edge.targetName,
  relationArray:state=>state.edge.relationArray,

  //node
  addNodeParams:state=>state.node.addNodeParams,
  addNodeVisible:state=>state.node.addNodeVisible,
  dialogVisible:state=>state.node.dialogVisible,
  nodeId:state=>state.node.nodeId,
  updateNodeParams:state=>state.node.updateNodeParams,
  positionX:state=>state.node.positionX,
  positionY:state=>state.node.positionY,
  nodeArray:state=>state.node.nodeArray,
  

}

export default getters
