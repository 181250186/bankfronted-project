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

<template>
  <div style="position: relative; height: 100%; width: 100%; z-index: 0;">
    <div id="cytoscape_id" style="height: 100%; width: 100%; z-index: 1;"></div>
    <div id="cytoolbar_id"
         style="position: absolute; right: 5pt; top: 5pt; z-index: 2; background-color: rgba(255, 255, 158, 0.9);">
      <div class="tools">
        <div class="center-center">
          <Icon style="font-size: 32px; cursor: pointer;" title="寻找关系" type="ios-search-outline"
                @click="searchDialogVisible1=true"/>
        </div>
      </div>

      <div class="tools">
        <div class="center-center">
          <Icon style="font-size: 32px; cursor: pointer;" title="源节点" type="ios-search-outline"
                @click="searchDialogVisible2=true"/>
        </div>
      </div>

      <div class="tools">
        <div class="center-center">
          <Icon style="font-size: 32px; cursor: pointer;" title="目标节点" type="ios-search-outline"
                @click="searchDialogVisible3=true"/>
        </div>
      </div>

      <div class="tools">
        <div class="center-center">
          <Icon style="font-size: 32px; cursor: pointer;" title="添加节点" type="ios-add-circle-outline"
                @click="addNode_Visible=true"/>
        </div>
      </div>


      <div class="tools">
        <div class="center-center">
          <Icon style="font-size: 32px; cursor: pointer;" title="缩小" type="ios-remove-circle-outline" @click="contractible()"/>
        </div>
      </div>

      <div class="tools">
              <div class="center-center">
                <Icon style="font-size: 32px; cursor: pointer;" title="返回" type="ios-undo-outline" @click="resize()"/>
              </div>
            </div>

            <div class="tools">
              <div class="center-center">
                <Icon style="font-size: 32px; cursor: pointer;" title="排版模式" type="ios-keypad-outline"
                      @click="refresh({name: 'grid'})"/>
              </div>
            </div>

            <div class="tools">
              <div class="center-center">
                <Icon style="font-size: 32px; cursor: pointer;" title="力导图模式" type="ios-git-merge"
                      @click="refresh({name: 'avsdf'})"/>
              </div>
            </div>

      <div class="tools">
                  <div class="center-center">
                    <Icon style="font-size: 32px; cursor: pointer;" title="高亮" type="ios-bulb-outline"
                          @click="highlight()"/>
                  </div>
                </div>

                <div class="tools">
                  <div class="center-center">
                    <Icon style="font-size: 32px; cursor: pointer;" title="展示效果" type="ios-eye-outline"
                          @click="editShowStyleVisible=true"/>
                  </div>
                </div>

                <div class="tools">
                  <div class="center-center">
                    <Icon style="font-size: 32px; cursor: pointer;" title="自定义节点" type="ios-locate-outline"
                          @click="editNodeVisible=true"/>
                  </div>
                </div>

                <div class="tools">
                  <div class="center-center">
                    <Icon style="font-size: 32px; cursor: pointer;" title="图片导出" type="ios-reverse-camera-outline"
                          @click="exportPng()"/>
                  </div>
                </div>

                <div class="tools">
                  <div class="center-center">
                    <Icon style="font-size: 32px; cursor: pointer;" title="文本导出" type="ios-document-outline" @click="exportFile()"/>
                  </div>
                </div>
                
                <div class="tools">
                  <div class="center-center">
                    <Icon style="font-size: 32px; cursor: pointer;" title="教程" type="ios-text-outline" @click="teach=true"/>
                  </div>
              </div>

              </div>
              <div id="information" style="position: absolute; left: 5pt; top: 5pt; z-index: 2; background-color: white;font-size:25px">
                <p>节点个数：{{this.nodeNumber}}</p>
                <p>关系个数：{{this.edgeNumber}}</p>
              </div>
              <el-dialog v-dialogDrag title="修改节点" :visible.sync="dialogVisible" :modal-append-to-body=false>
                <el-form :model="node_form">
                  <el-form-item label="节点名称">
                    <el-input v-model="node_form.data.name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="节点类型">
                    <el-select v-model="node_form.classes" placeholder="选择节点类型">
                     <el-option label="green" value="classes-A"></el-option>
                      <el-option label="purple" value="classes-B"></el-option>
                      <el-option label="blue" value="classes-C"></el-option>
                      <el-option label="red" value="classes-D"></el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="modify">确 定</el-button>
                </div>
              </el-dialog>

              <el-dialog v-dialogDrag title="修改关系" :visible.sync="dialogRelation_Visible" :modal-append-to-body=false>
                <el-form :model="relation_form">
                  <el-form-item label="关系">
                    <el-input v-model="relation_form.data.name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="关系类型">
                    <el-select v-model="relation_form.classes" placeholder="选择关系类型">
                     <el-option label="solid" value="relationA"></el-option>
                      <el-option label="dotted" value="relationB"></el-option>
                      <el-option label="hidden" value="relationC"></el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogRelation_Visible = false">取 消</el-button>
                  <el-button type="primary" @click="modify_r">确 定</el-button>
                </div>
              </el-dialog>

              <el-dialog v-dialogDrag title="增加实体的关系" :visible.sync="addSon_Visible" :modal-append-to-body=false>
                <el-form :model="relation_form">
                  <el-form-item label="源节点名称">
                    <el-input v-model="source_name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="目标节点名称">
                    <el-input v-model="target_name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="关系">
                    <el-input v-model="relation_form.data.name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="关系类型">
                    <el-select v-model="relation_form.classes" placeholder="选择关系类型">
                     <el-option label="solid" value="relationA"></el-option>
                      <el-option label="dotted" value="relationB"></el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="addSon_Visible = false">取 消</el-button>
                  <el-button type="primary" @click="add_relation(source_name,target_name)">确 定</el-button>
                </div>
              </el-dialog>

              <el-dialog v-dialogDrag title="增加节点" :visible.sync="addNode_Visible" :modal-append-to-body=false>
                <el-form :model="node_form">
                  <el-form-item label="节点名称">
                    <el-input v-model="node_form.data.id" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="节点类型">
                    <el-select v-model="node_form.classes" placeholder="选择节点类型">
                      <el-option label="green" value="classes-A"></el-option>
                      <el-option label="purple" value="classes-B"></el-option>
                      <el-option label="blue" value="classes-C"></el-option>
                      <el-option label="red" value="classes-D"></el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="addNode_Visible = false">取 消</el-button>
                  <el-button type="primary" @click="add_new_node">确 定</el-button>
                </div>
              </el-dialog>

              <el-dialog v-dialogDrag title="寻找关系" :visible.sync="searchDialogVisible1" :modal-append-to-body=false>
                <el-form :model="node_form">
                  <el-form-item label="节点1">
                    <el-input v-model="source_name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="节点2">
                    <el-input v-model="target_name" autocomplete="off"></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="searchDialogVisible1 = false">取 消</el-button>
                  <el-button type="primary" @click="question_answer1(source_name,target_name)">确 定</el-button>
                </div>
              </el-dialog>
              <el-dialog v-dialogDrag title="源节点" :visible.sync="searchDialogVisible2" :modal-append-to-body=false>
                <el-form :model="relation_form">
                  <el-form-item label="节点名称">
                    <el-input v-model="source_name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="关系">
                    <el-input v-model="relation_form.data.name" autocomplete="off"></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="searchDialogVisible2 = false">取 消</el-button>
                  <el-button type="primary" @click="question_answer2(source_name,relation_form.data.name)">确 定</el-button>
                </div>
              </el-dialog>
              <el-dialog v-dialogDrag title="目标节点" :visible.sync="searchDialogVisible3" :modal-append-to-body=false>
                <el-form :model="relation_form">
                  <el-form-item label="目标节点">
                    <el-input v-model="target_name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="关系">
                    <el-input v-model="relation_form.data.name" autocomplete="off"></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="searchDialogVisible3 = false">取 消</el-button>
                  <el-button type="primary" @click="question_answer3(target_name,relation_form.data.name)">确 定</el-button>
                </div>
              </el-dialog>
              <el-dialog
                v-dialogDrag
                title="提示"
                :visible.sync="messageVisible"
                width="30%"
                :modal-append-to-body=false>
                <span>该元素已存在</span>
                <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="messageVisible = false">确 定</el-button>
            </span>

              </el-dialog>
              <el-dialog v-dialogDrag title="自定义节点" :visible.sync="editNodeVisible" :modal-append-to-body=false>
                <el-form :model="editNode_form">
                  <el-form-item label="节点大小">
                    <el-slider style="margin-left: 70px" v-model="editNode_form.size"></el-slider>
                  </el-form-item>
                  <el-form-item label="字体大小">
                    <el-slider style="margin-left: 70px" v-model="editNode_form.fontSize"></el-slider>
                  </el-form-item>
                  <el-form-item label="节点颜色">
                    <el-color-picker v-model="editNode_form.color"></el-color-picker>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="editNodeVisible = false">取 消</el-button>
                  <el-button type="primary" @click="addNodeStyle">确 定</el-button>
                </div>
              </el-dialog>
              <el-dialog v-dialogDrag title="教程" :visible.sync="teach" :modal-append-to-body=false>
                <div class="demo-image__lazy">
                  <span style="font-size:15px">注：选中节点（关系）后右键鼠标进入辅助菜单，再次点击节点（关系）取消操作，如下图:</span>
                  <el-image v-for="url in urls" :key="url" :src="url" lazy></el-image>
                </div>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="teach = false">确 认</el-button>
                </div>
              </el-dialog>
              <el-dialog v-dialogDrag title="展示效果调节" :visible.sync="editShowStyleVisible" :modal-append-to-body=false>
                <el-form :model="editShowStyle_form">
                  <el-form-item label="节点直径">
                    <div class="block">
                      <el-slider
                        style="margin-left: 100px"
                        v-model="editShowStyle_form.iconSize"
                        :step="25"
                        :show-tooltip=false
                        show-stops
                        :marks="marks">
                      </el-slider>
                    </div>
                  </el-form-item>
                  <el-form-item label="文字大小">
                    <div class="block">
                      <el-slider
                        style="margin-left: 100px"
                        v-model="editShowStyle_form.fontSize"
                        :step="25"
                        show-stops
                        :show-tooltip=false
                        :marks="marks">
                      </el-slider>
                    </div>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="editShowStyleVisible = false">取 消</el-button>
                  <el-button type="primary" @click="editShowStyle">确 定</el-button>
                </div>
              </el-dialog>

            </div>


          </template>

          <script>
            import cytoscape from 'cytoscape';
            import FileSaver from 'file-saver';
            import cxtmenu from 'cytoscape-cxtmenu';
            import cola from 'cytoscape-cola';
            import avsdf from 'cytoscape-avsdf';
            import coseBilkent from 'cytoscape-cose-bilkent';

            import {message} from 'ant-design-vue'

            import {addRelationAPI, deleteRelationAPI, updateRelationAPI} from "../../api/edge";
            import {addNodeAPI, updateNodeAPI,deleteNodeAPI} from "../../api/node";
            import {deleteNodeAndEdgeAPI, findChartAPI, saveChartAPI, withdrawAPI} from "@/api/chart";

            import { mapActions } from "vuex"


            export default {
              name: "CJS",
              beforeCreate() {
                this.$cy && this.$cy.destroyed() && this.$cy.destroy();
                delete this.$cy;
              },
              beforeDestroy() {
                this.$cy && this.$cy.destroyed() && this.$cy.destroy();
                delete this.$cy;
              },
              mounted() {
                // Cxtmenu圆形菜单主要依赖组件
                if (!cytoscape().cxtmenu) {
                  cytoscape.use(cxtmenu);
                  cytoscape.use(cola);
                  cytoscape.use(avsdf);
                  cytoscape.use(coseBilkent);
                  // cytoscape.use(popup);
                }

                this.$cy = cytoscape({
                  // initial viewport state:
                  zoom: 1, // 图表的初始缩放级别.可以设置options.minZoom和options.maxZoom设置缩放级别的限制.
                  pan: {x: 0, y: 0}, // 图表的初始平移位置.
                  // interaction options:
                  minZoom: 1e-50, // 图表缩放级别的最小界限.视口的缩放比例不能小于此缩放级别.
                  maxZoom: 1e50, // 图表缩放级别的最大界限.视口的缩放比例不能大于此缩放级别.
                  zoomingEnabled: true, // 是否通过用户事件和编程方式启用缩放图形.
                  userZoomingEnabled: true, // 是否允许用户事件(例如鼠标滚轮,捏合缩放)缩放图形.对此缩放的编程更改不受此选项的影响.
                  panningEnabled: true, // 是否通过用户事件和编程方式启用平移图形.
                  userPanningEnabled: true, // 是否允许用户事件(例如拖动图形背景)平移图形.平移的程序化更改不受此选项的影响.
                  boxSelectionEnabled: true, // 是否启用了框选择(即拖动框叠加,并将其释放为选择).如果启用,则用户必须点击以平移图表.
                  selectionType: 'single', // 一个字符串，指示用户输入的选择行为.对于'additive',用户进行的新选择将添加到当前所选元素的集合中.对于'single',用户做出的新选择成为当前所选元素的整个集合.
                  touchTapThreshold: 8, // 非负整数,分别表示用户在轻击手势期间可以在触摸设备和桌面设备上移动的最大允许距离.这使得用户更容易点击.
                                        // 这些值具有合理的默认值,因此建议不要更改这些选项,除非您有充分的理由这样做.大值几乎肯定会产生不良后果.
                  desktopTapThreshold: 4, // 非负整数,分别表示用户在轻击手势期间可以在触摸设备和桌面设备上移动的最大允许距离.这使得用户更容易点击.
                                          // 这些值具有合理的默认值,因此建议不要更改这些选项,除非您有充分的理由这样做.大值几乎肯定会产生不良后果.
                  autolock: false, // 默认情况下是否应锁定节点(根本不可拖动,如果true覆盖单个节点状态).
                  autoungrabify: false, // 默认情况下节点是否不允许被拾取(用户不可抓取,如果true覆盖单个节点状态).
                  autounselectify: false, // 默认情况下节点是否允许被选择(不可变选择状态,如果true覆盖单个元素状态).
                  // rendering options:
                  headless: false, // true:空运行,不显示不需要容器容纳.false:显示需要容器容纳.
                  styleEnabled: true, // 一个布尔值,指示是否应用样式.
                  hideEdgesOnViewport: true, // 渲染提示,设置为true在渲染窗口时,不渲染边.例如,移动某个顶点时或缩放时,边信息会被临时隐藏,移动结束后,边信息会被执行一次渲染.由于性能增强,此选项现在基本上没有实际意义.
                  hideLabelsOnViewport: true, // 渲染提示,当设置为true使渲染器在平移和缩放期间使用纹理而不是绘制元素时,使大图更具响应性.由于性能增强,此选项现在基本上没有实际意义.
                  textureOnViewport: true, // 渲染提示,当设置为true使渲染器在平移和缩放期间使用纹理而不是绘制元素时,使大图更具响应性.由于性能增强,此选项现在基本上没有实际意义.
                  motionBlur: true, // 渲染提示,设置为true使渲染器使用运动模糊效果使帧之间的过渡看起来更平滑.这可以增加大图的感知性能.由于性能增强,此选项现在基本上没有实际意义.
                  motionBlurOpacity: 0.2, // 当motionBlur:true,此值控制运动模糊帧的不透明度.值越高,运动模糊效果越明显.由于性能增强,此选项现在基本上没有实际意义.
                  wheelSensitivity: 0.3, // 缩放时更改滚轮灵敏度.这是一个乘法修饰符.因此,0到1之间的值会降低灵敏度(变焦较慢),而大于1的值会增加灵敏度(变焦更快).
                  pixelRatio: 'auto', // 使用手动设置值覆盖屏幕像素比率(1.0建议,如果已设置).这可以通过减少需要渲染的有效区域来提高高密度显示器的性能,
                                      // 尽管在最近的浏览器版本中这是不太必要的.如果要使用硬件的实际像素比,可以设置pixelRatio: 'auto'(默认).
                  // DOM容器,决定内容展示的位置,方式一(原生):document.getElementById('xx'),方式二(jQuery):$('#xx')
                  container: document.getElementById('cytoscape_id'),
                  // 一个指定布局选项的普通对象.
                  layout: {name: 'random'},
                });
                // Cxtmenu圆形菜单--开始
                this.$cy.cxtmenu({
                  menuRadius: 60, // the radius of the circular menu in pixels
                  selector: 'node', // elements matching this Cytoscape.js selector will trigger cxtmenus
                  commands: () => {
                    return [
                      {
                        fillColor: 'rgba(255, 255，138，0.75)', // optional: custom background color for item
                        content: '<span class="fa fa-flash fa-2x">修改</span>', // html/text content to be displayed in the menu
                        contentStyle: {}, // css key:value pairs to set the command's css in js if you want
                        select: (ele) => this.modify_inf([ele.id()]),
                        enabled: true, // whether the command is selectable
                      },
                      {
                        fillColor: 'rgba(255, 255，138, 0.75)', // optional: custom background color for item
                        content: '<span class="fa fa-flash fa-2x">添加关系</span>', // html/text content to be displayed in the menu
                        contentStyle: {}, // css key:value pairs to set the command's css in js if you want
                        select: (ele) => this.add_son([ele.id()]), // `ele` holds the reference to the active element
                        enabled: true, // whether the command is selectable
                      },
                      {
                        fillColor: 'rgba(255, 255，138, 0.75)', // optional: custom background color for item
                        content: '删除', // html/text content to be displayed in the menu
                        contentStyle: {}, // css key:value pairs to set the command's css in js if you want
                        select: (ele) => this.delEle([ele.id()]),  // a function to execute when the command is selected
                        enabled: true, // whether the command is selectable
                      }
                    ]
                  },
                  fillColor: 'rgba(0, 0, 0, 0.75)', // 指令默认颜色(the background colour of the menu)
                  activeFillColor: 'rgba(144, 238 ,144, 0.75)', // 所选指令的颜色(the colour used to indicate the selected command)
                  activePadding: 10, // additional size in pixels for the active command
                  indicatorSize: 14, // the size in pixels of the pointer to the active command
                  separatorWidth: 4, //连续命令之间的空白间隔(以像素为单位)
                  spotlightPadding: 10, //元素和聚光灯之间的额外间距(以像素为单位)
                  minSpotlightRadius: 10, // the minimum radius in pixels of the spotlight
                  maxSpotlightRadius: 14, // the maximum radius in pixels of the spotlight
                  openMenuEvents: 'cxttap', // space-separated cytoscape events that will open the menu; only `cxttapstart` and/or `taphold` work here
                  itemColor: 'red', // 各指令元素内字体颜色
                  itemTextShadowColor: 'green', // 各指令元素内字体阴影颜色
                  zIndex: 9999, // the z-index of the ui div
                  atMouse: true, // draw menu at mouse position
                });
                //Cxtmenu圆形菜单--结束

                //边-Cxtmenu圆形菜单--开始
                this.$cy.cxtmenu({
                  menuRadius: 60, // the radius of the circular menu in pixels
                  selector: 'edge', // elements matching this Cytoscape.js selector will trigger cxtmenus
                  commands: () => {
                    return [
                      {
                        fillColor: 'rgba(255, 255, 138, 0.75)', // optional: custom background color for item
                        content: '<span class="fa fa-flash fa-2x">修改</span>', // html/text content to be displayed in the menu
                        contentStyle: {}, // css key:value pairs to set the command's css in js if you want
                        select: (ele) => this.modify_relation([ele.id()]),
                        enabled: true, // whether the command is selectable
                      },
                      {
                        fillColor: 'rgba(255, 255, 138, 0.75)', // optional: custom background color for item
                        content: '删除', // html/text content to be displayed in the menu
                        contentStyle: {}, // css key:value pairs to set the command's css in js if you want
                        select: (ele) => this.delEle([ele.id()]),  // a function to execute when the command is selected
                        enabled: true, // whether the command is selectable
                      }
                    ]
                  },
                  fillColor: 'rgba(0, 0, 0, 0.75)', // 指令默认颜色(the background colour of the menu)
                  activeFillColor: 'rgba(144, 238 ,144, 0.75)', // 所选指令的颜色(the colour used to indicate the selected command)
                  activePadding: 10, // additional size in pixels for the active command
                  indicatorSize: 14, // the size in pixels of the pointer to the active command
                  separatorWidth: 4, //连续命令之间的空白间隔(以像素为单位)
                  spotlightPadding: 10, //元素和聚光灯之间的额外间距(以像素为单位)
                  minSpotlightRadius: 10, // the minimum radius in pixels of the spotlight
                  maxSpotlightRadius: 14, // the maximum radius in pixels of the spotlight
                  openMenuEvents: 'cxttap', // space-separated cytoscape events that will open the menu; only `cxttapstart` and/or `taphold` work here
                  itemColor: 'red', // 各指令元素内字体颜色
                  itemTextShadowColor: 'green', // 各指令元素内字体阴影颜色
                  zIndex: 9999, // the z-index of the ui div
                  atMouse: true, // draw menu at mouse position
                });
                //边-Cxtmenu圆形菜单--结束
                // 节点的样式
                this.$cy
                  .style()
                  .selector('.classes-A')
                  .css({'background-color': '#78fc68', 'content': 'data(name)', 'border-color': '#78fc68', 'border-width': "5px"})
                  .selector('.classes-B')
                  .css({'background-color': '#c96396', 'content': 'data(name)', 'border-color': '#c96396', 'border-width': "5px"})
                  .selector('.classes-C')
                  .css({'background-color': '#499bea', 'content': 'data(name)','border-color':'#499bea','border-width':"5px"})
                   .selector('.classes-D')
                   .css({'background-color': '#fd2b38', 'content': 'data(name)','border-color':'#fd2b38','border-width':"5px"})
                  .selector('.relationA')
                  .css({
                    'target-arrow-color': '#100605', /*箭头颜色*/
                    'curve-style': 'bezier', /*线条样式曲线*/
                    'line-color': '#100605', /*线条颜色*/
                    'width': '1px', /*线条宽度*/
                    'font-size': '10px', /*标签字体大小*/
                    'color': '#000000', /*标签字体大小*/
                    'text-outline-color': 'white', /*文本轮廓颜色*/
                    'text-outline-width': '1px', /*文本轮廓宽度*/
                    'text-rotation': 'autorotate', /*标签方向*/
                  })
                  .selector('.relationB')
                  .css({
                    'line-style': 'dotted',
                    'target-arrow-color': '#100605', /*箭头颜色*/
                    'curve-style': 'bezier', /*线条样式曲线*/
                    'line-color': '#100605', /*线条颜色*/
                    'line-dash-offset': '1',
                    'width': '1px', /*线条宽度*/
                    'font-size': '10px', /*标签字体大小*/
                    'color': '#000000', /*标签字体大小*/
                    'text-outline-color': 'white', /*文本轮廓颜色*/
                    'text-outline-width': '1px', /*文本轮廓宽度*/
                    'text-rotation': 'autorotate', /*标签方向*/
                  })
                  .selector('.relationC')
                  .css({
                    'line-style': 'dotted',
                    'target-arrow-color': '#fdfffb', /*箭头颜色*/
                    'curve-style': 'bezier', /*线条样式曲线*/
                    'line-color': '#fffbfb', /*线条颜色*/
                    'line-dash-offset': '1',
                    'width': '1px', /*线条宽度*/
                    'font-size': '10px', /*标签字体大小*/
                    'color': '#ffffff', /*标签字体大小*/
                    'text-outline-color': 'white', /*文本轮廓颜色*/
                    'text-outline-width': '1px', /*文本轮廓宽度*/
                    'text-rotation': 'autorotate', /*标签方向*/
                  })
                ;
                // 通用的样式
                this.$cy.style()
                /*未选择节点样式*/
                  .selector('node')
                  .style({'label': 'data(name)', 'font-size': '10pt', 'width': '8pt', 'height': '8pt'})
                  /*已选择节点样式*/
                  .selector('node:selected')
                  .style({'border-color': '#100605', 'border-width': "10px",})
                  /*未选择边样式*/
                  .selector('edge')
                  .style({
                    'label': 'data(name)',
                    'target-arrow-shape': 'triangle-backcurve', /*箭头样式*/
                    'target-arrow-size': '1px', /*箭头大小*/
                  })
                  /*已选择边样式*/
                  .selector('edge:selected')
                  .style({
                    'color': '#fc1c33', /*标签字体大小*/
                    'target-arrow-color': '#fc1c33', /*箭头颜色*/
                    'line-color': '#fc1c33', /*线条颜色*/
                  })
                  /*高亮样式*/
                  .selector('.light-off')
                  .style({'opacity': '0.1',})
                ;
              },
              data() {
                return {
                  dialogVisible: false,
                  addSon_Visible: false,
                  addNode_Visible: false,
                  messageVisible: false,
                  uploadVisible:false,
                  dialogRelation_Visible: false,
                  chartNameVisible:false,
                  editNodeVisible:false,
                  editEdgeVisible:false,
                  searchDialogVisible1:false,
                  searchDialogVisible2:false,
                  searchDialogVisible3:false,
                  statisticVisible:false,
                  relation_index: 10,
                  teach:false,
                  urls: [
                    'https://181250186reverse.oss-cn-beijing.aliyuncs.com/3.png',
                    'https://181250186reverse.oss-cn-beijing.aliyuncs.com/4.png'
                  ],
                  relation_form: {
                    group: 'edges',
                    //id根据获得的数据递增
                    data: {id: '', name: '', source: '', target: ''},
                    classes: '',
                    chartId:4
                  },
                  node_form: {
                    group: 'nodes',
                    data: {id: '', name: ''},
                    classes: '',
                    position: {x: 0, y: 0},
                    chartId:4,
                  },
                  chart_form:{
                    name:"",
                  },
                  positionX: 0,
                  positionY: 0,
                  relation_array: [],
                  node_array: [],
                  chartId:4,
                  //用于保存增加关系时用户输入的节点名称
                  source_name: '',
                  target_name: '',
                  fileList:[],
                  testNode:{},
                  nodeNumber:0,
                  edgeNumber:0,

                  editNode_form:{
                    name:"",
                    color:'#ffc0cb',
                    size:15,
                    fontSize:15,
                  },//用于编辑节点样式
                  editShowStyle_form:{
                    iconSize:0,
                    fontSize:0,
                    relationVisible:true
                  },//用于调节展示效果
                  editShowStyleVisible:false,//用于调节展示效果
                  nodeStyle:
                    [{
                      value: 'classes-A',
                      label: 'classes-A'
                    }, {
                      value: 'classes-B',
                      label: 'classes-B'
                    },],
                };
              },
              methods: {
                /**
                 * eles : Array or Map.
                 * node_eg: {group: 'nodes', data: {id: 'nid1', name: 'name1', label: 'l1 l2', others: 'others'}, classes: 'like label', position: {x: 100, y: 100}};
                 * edge_eg: {group: 'edges', data: {id: 'eid1', name: 'name1', source: 'A', target: 'B', label: 'l1 l2', others: 'others'}, classes: 'like label', position: {x: 100, y: 100}};
                 * node_eg: [
                 *   {group: 'nodes', data: {id: 'nid1', name: 'name1', label: 'l1 l2', others: 'others'}, classes: 'like label', position: {x: 100, y: 100}};
                 *   {group: 'nodes', data: {id: 'nid2', name: 'name2', label: 'l1 l2', others: 'others'}, classes: 'like label', position: {x: 100, y: 100}};
                 * ];
                 * edge_eg: [
                 *   {group: 'edges', data: {id: 'eid1', name: 'name1', source: 'nid1', target: 'nid2', label: 'l1 l2', others: 'others'}, classes: 'like label', position: {x: 100, y: 100}};
                 *   {group: 'edges', data: {id: 'eid2', name: 'name1', source: 'nid2', target: 'nid3', label: 'l1 l2', others: 'others'}, classes: 'like label', position: {x: 100, y: 100}};
                 * ];
                 * @param eles 元素集合.
                 */
                //添加新节点的方法

                  ...mapActions([
                    'addNode',
                    'addRelation',
                    'getNodeNumber',
                    'getEdgeNumber',
                    'deleteNode',
                    'deleteRelation'
                  ]),

                question_answer1(source_n, target_n){
                  var s = '';
                  var t = '';
                  var r = '';
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["name"] == source_n) {
                      s = ele.data().id;
                    }
                  });
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["name"] == target_n) {
                      t = ele.data().id;
                    }
                  });
                  this.relation_form.data.source = s;
                  this.relation_form.data.target = t;
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["source"] == s && ele.data()["target"] == t) {
                      r = ele.data().name;
                    }
                  });
                  alert(r);
                  this.searchDialogVisible1 = false;
                },

                question_answer2(source_n, target_r){
                  var t = '';
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["source"] == source_n && ele.data()["name"] == target_r) {
                      t = ele.data().target;
                      alert(t);
                    }
                  });
                  this.searchDialogVisible2 = false;
                },

                question_answer3(target_n, target_r){
                  var s = '';
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["target"] == target_n && ele.data()["name"] == target_r) {
                      s = ele.data().source;
                      alert(s);
                    }
                  });
                  this.searchDialogVisible3= false;
                },

                add_new_node() {
                  this.node_form.data.name = this.node_form.data.id;
                  var n=this.node_form.data.name;
                  const that = this;
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["name"] == n) {
                      that.addNode_Visible = false;
                      alert("节点名称已存在！");
                    }
                  });
                  if (this.addNode_Visible==true){
                    this.node_form.chartId = this.chartId;
                    const that = this;
                    this.node_array.push(this.node_form);
                    this.$cy.batch(async () => {
                      let node = that.node_array.pop();
                      //that.testNode = await this.addNode(node);
                      let res = await this.addNode(node)
                      console.log("调用后")
                      console.log(res);
                      // that.data.testNode = res
                      this.$cy.add(node);


                    });
                    this.addNode_Visible = false;
                    this.positionX += 0;
                    this.positionY += 0;
                  }
                  this.node_form_update();
                },

                //增加节点或边
                addEles(eles) {
                  if (eles) {
                    this.$cy.startBatch();
                    this.$cy.batch(() => {
                      let elements = ((Array.isArray ? Array.isArray(eles) : null != eles && eles instanceof Array) ? eles : [eles]);
                      let filterElements = elements.filter(__ => !this.$cy.getElementById(__.data.id).length)
                      console.log("filterElement", filterElements)
                      this.$cy.add(filterElements);
                      this.resize();

                    });
                    this.$cy.endBatch();
                  }
                },
                //圆形菜单——增加son[id需要一个接口]
                /**
                 * 圆形菜单——增加son
                 * @param ele 某元素ID
                 **/
                add_son(ele) {
                  this.$cy.startBatch();
                  const that = this;
                  this.$cy.batch(() => {
                    that.relation_form.source = ele;
                    that.relation_form.data.id = that.relation_index;
                    // that.relation_form.data.source=ele;
                    that.relation_index += 1;
                    that.addSon_Visible = true;
                  });
                  this.$cy.endBatch();

                },

                //增加关系调用的方法
                add_relation(source_n, target_n) {
                  var s = '';
                  var t = '';
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["name"] == source_n) {
                      s = ele.data().id;
                    }
                  });
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["name"] == target_n) {
                      t = ele.data().id;
                    }
                  });
                  this.relation_form.data.source = s;
                  this.relation_form.data.target = t;
                  if (this.relation_form.data.source == '' || this.relation_form.data.target == '') {
                    this.addSon_Visible = false;
                    alert("请输入正确的节点名称！")
                    return -1;
                  }
                  this.relation_array.push(this.relation_form);
                  const that = this;
                  this.$cy.batch(() => {
                    this.$cy.add(that.relation_array.pop());
                    this.addSon_Visible = false;
                  });
                  this.add_relation_api(this.relation_form);
                  this.relation_form_update();

                },

                get_ele_by_name(name) {
                  this.$cy.elements().map(function (ele) {
                    if (ele.data()["name"] == name) {
                      var id_ = ele.data().id
                      return id_.toString;
                    }
                    return -1;

                  });
                },

                //TODO:
                add_relation_api: async (relation) => {
                  const res = await addRelationAPI(relation);
                  if (res) {
                    this.addSon_Visible = false;
                    message.success('添加成功')
                  } else {
                    message.error('添加失败')
                  }
                },

                relation_form_update() {
                  this.relation_form = {
                    group: 'edges',
                    //id根据获得的数据递增
                    data: {id: '', name: '', source: '', target: ''},
                    classes: '',
                    chartId:this.chartId,
                  };
                },

                node_form_update() {
                  this.node_form = {
                    group: 'nodes',
                    data: {id: '', name: ''},
                    classes: '',
                    position: {x: this.positionX, y: this.positionY},
                    chartId:this.chartId,
                  };
                },

                /**
                 * 圆形菜单——修改实体
                 * @param ele 某元素ID
                 **/
                modify_inf(ele) {
                  this.$cy.startBatch();
                  const that = this;
                  this.$cy.batch(() => {
                    that.node_form.data.id = ele;
                     that.node_form.data.name = ele;
                    that.dialogVisible = true;
                  });
                  this.$cy.endBatch();
                },
                modify() {
                  let element = this.$cy.getElementById(this.node_form.data.id);
                  const that = this;
                  this.$cy.batch(() => {
                    let relations = this.find_relations_modify(that.node_form.data.id, that.node_form.data.name);
                    this.$cy.remove(element);
                    that.node_form.position = element.position()
                    that.node_form.data.id = element.id();
                    updateNodeAPI(that.node_form);
                    this.$cy.add(that.node_form);
                    this.$cy.add(relations);
                    that.dialogVisible = false;
                  });
                  this.node_form_update();
                },
                /**
                 * 边的修改方法
                 * @param ele 某元素ID
                 **/
                modify_relation(ele) {
                  let element = this.$cy.getElementById(ele);
                  this.dialogRelation_Visible = true;
                  this.relation_form.data.name = element.data().name;
                  this.relation_form.data.id = element.data().id;
                  this.relation_form.data.source = element.data().source;
                  this.relation_form.data.target = element.data().target;
                },
                modify_r() {
                  const that = this;
                  this.relation_array.push(this.relation_form);
                  this.$cy.batch(() => {
                    let element = this.$cy.getElementById(this.relation_form.data.id);
                    this.$cy.remove(element);
                    let relation = that.relation_array.pop();
                    this.$cy.add(relation);
                    updateRelationAPI(relation);
                    that.dialogRelation_Visible = false;
                  });
                  this.relation_form_update();
                },

                cancel() {
                  this.$cy.startBatch();
                  const that = this;
                  that.$cy.remove("elements");
                  this.$cy.batch(() => {
                    const groups =withdrawAPI(this.chartId);
                    console.log(groups)
                    groups.then((result)=>{
                      that.addEles(result);
                      that.id=result.length;
                    })
                  });
                  this.$cy.endBatch();
                },

                /**
                 * 在修改过程中，修改相关关系的信息
                 * @param ele 某元素ID
                 **/
                find_relations_modify(ele, modif_ele) {
                  var collection = [];
                  var element = this.$cy.getElementById(ele);
                  const that=this;
                  if (element.group() == 'nodes') {
                    var id = element.id();
                    this.$cy.elements().map(function (ele) {
                      var relation = {
                        group: 'edges',
                        data: {id: '', name: '', source: '', target: ''},
                        classes: '',
                        chartId:that.chartId,
                      };
                      if (ele.data()["source"] == id) {
                        relation.data = ele.data();
                        relation.data.source = id;
                        relation.classes = ele.classes();
                        relation.chartId = ele.chartId;
                        collection.push(relation);
                      }
                      if (ele.data()["target"] == id) {
                        relation.data = ele.data();
                        relation.data.target = id;
                        relation.classes = ele.classes();
                        relation.chartId = ele.chartId;
                        collection.push(relation);
                      }
                    });
                  }
                  return collection;
                },


                closepop() {
                  this.dialogVisible = false;
                  this.addSon_Visible = false;
                },


                /**
                 * 删除选择的内容(可能是顶点, 也可能是关系)
                 */
                delEles() {
                  this.$cy.startBatch();
                  const that = this;
                  this.$cy.batch(() => {
                    let selectedEles = this.$cy.elements(':selected');
                    that.deleted_array = this.find_relations(selectedEles.id());
                    // 未选择不进行操作
                    if (!selectedEles || 1 > selectedEles.length) {
                      return false;
                    }
                    if (selectedEles.group() == "nodes") {
                      this.delete_node_api(that.chartId, selectedEles.id());
                    } else {
                      this.delete_relation_api(that.chartId,selectedEles.id());
                    }
                    selectedEles.remove();
                  });
                  this.$cy.endBatch();
                },
                /**
                 * 圆形菜单——删除
                 * @param ele 某元素ID
                 **/

                delEle(ele) {
                  this.$confirm('此操作将导致删除, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                  }).then(() => {
                    this.$cy.startBatch();
                    const that = this;
                    this.$cy.batch(() => {
                      let elements = this.$cy.getElementById(ele);
                      that.deleted_array = this.find_relations(ele);
                      if (elements.group() == "nodes") {
                        this.delete_node_api(this.chartId, ele);
                      } else {
                        this.delete_relation_api(this.chartId, ele);
                      }
                      elements.remove();
                    });
                    this.$cy.endBatch();
                  }).catch(() => {
                    this.$message({
                      type: 'info',
                      message: '已取消删除'
                    });
                  });
                },
                
                
                /**
                 * 根据节点找关系
                 * @param ele 某元素ID
                 **/
                find_relations(ele) {
                  var collection = this.$cy.collection();
                  var element = this.$cy.getElementById(ele);
                  collection = collection.add(element);
                  if (element.group() == 'nodes') {
                    var id = element.id();
                    this.$cy.elements().map(function (ele) {
                      if (ele.data()["source"] == id || ele.data()["target"] == id) {
                        collection.merge(ele);
                      }
                    });
                  }
                  return collection;
                },

                //将删除点信息传给后端，已测，没问题
                delete_node_api: async (chartId, node) => {
                  const res = await deleteNodeAPI(node, chartId);
                  console.log(res)
                  if (res) {
                    message.success('删除成功')
                  } else {
                    message.error('删除失败')
                  }
                },

                //TODO:将删除边信息传给后端，已测
                delete_relation_api: async (chartId, relation) => {
                  const res = await deleteRelationAPI(chartId, relation);
                  if (res) {
                    message.success('删除成功')
                  } else {
                    message.error('删除失败')
                  }
                },


                /***************************工具栏************************/
                /**
                 * 导出图片.
                 */
                exportPng() {
                  let blob = this.$cy.png({output: 'blob', bg: 'white', full: true, scale: 4, quality: 1});
                  let [aLink, evt] = [document.createElement('a'), document.createEvent("HTMLEvents")];
                  evt.initEvent("click", true, true);
                  [aLink.download, aLink.href] = [`${new Date().getTime()}.png`, URL.createObjectURL(blob)];
                  aLink.dispatchEvent(evt);
                  aLink.click();
                },
                zoom(zoom) {
                  /** 获取已选择内容 */
                  let selectedEles = this.$cy.elements('node:selected');
                  /** 获取已选择内容中得第一个, 没有选择为null */
                  let selectedEle = selectedEles && selectedEles.length ? selectedEles[0] : null;
                  /** 获取画布偏移位置 */
                  let pan = this.$cy.pan();
                  /** 计算原点坐标 */
                  let [x, y] = selectedEle ? [selectedEle.position('x'), selectedEle.position('y')] : [pan.x, pan.y];
                  let level = this.$cy.zoom() + zoom;
                  (level > this.$cy.maxZoom) && (level = this.$cy.maxZoom);
                  (level < this.$cy.minZoom) && (level = this.$cy.minZoom);
                  this.$cy.zoom({level: level, renderedPosition: {x: x, y: y}});
                },
                /** 缩小 */
                contractible() {
                  this.zoom(-0.3);
                },
                /** 合适大小 */
                resize() {
                  this.$cy.fit();
                },
                /**
                 * 高亮.
                 * @param ele 某元素ID
                 */
                lightOn(ele) {
                  this.$cy.startBatch();
                  this.$cy.batch(() => {
                    this.$cy.elements().addClass("light-off"); //*添加样式*/
                    let elements = ((Array.isArray ? Array.isArray(ele) : null != ele && ele instanceof Array) ? ele : [ele]);
                    elements.forEach(__ => {
                      this.$cy.getElementById(__).removeClass("light-off");
                      this.$cy.getElementById(__).neighborhood().removeClass("light-off");
                    });
                  });
                  this.$cy.once('click', () => this.lightOff());
                  this.$cy.endBatch();
                },
                /**
                 * 取消高亮
                 */
                lightOff() {
                  this.$cy.startBatch();
                  this.$cy.batch(() => this.$cy.elements().removeClass("light-off") /*移除样式*/);
                  this.$cy.endBatch();
                },
                /** 高亮邻居 */
                highlight() {
                  /** 获取已选择内容 */
                  let selectedEles = this.$cy.elements('node:selected');
                  /** 获取已选择内容中得第一个, 没有选择为null */
                  let selectedEle = selectedEles && selectedEles.length ? selectedEles[0] : null;
                  (selectedEle) && (this.lightOn(selectedEle.id()));
                },

                refresh({name = 'cola', randomize = false, animate = true} = {}) {
                  this.$cy.layout({name: name, randomize: randomize, animate: animate,}).run();
                },

                exportFile() {
                  const data = JSON.stringify(this.$cy.json(), null, "\t");
                  const blob = new Blob([data], {type: 'json'})
                  FileSaver.saveAs(blob, 'tempcoin.json')
                },
                /***************************工具栏************************/

                addNodeStyle(){
                  this.editNode_form.size=this.editNode_form.size*0.15
                  console.log(this.editNode_form.size)
                  this.$cy.style()
                    .selector('.'+this.editNode_form.name)
                    .css({'background-color': this.editNode_form.color, 'content': 'data(name)', 'border-color': this.editNode_form.color, 'border-width': this.editNode_form.size+"px",'font-size':this.editNode_form.fontSize+"px",'pie-size': '100%',})
                    .update()
                  console.log(this.$cy.style())
                  this.editNodeVisible=false;
                  var t={value:this.editNode_form.name,label:this.editNode_form.name}
                  this.nodeStyle.push(t)
                },
                showNodeStyle(){
                  var obj = document.getElementById("nodeStyle");
                  obj.style.backgroundColor= this.editNode_form.color;
                  obj.style.height=(this.editNode_form.size*0.3)+'px'
                  obj.style.width=(this.editNode_form.size*0.3)+'px'
                  var font=document.getElementById('nodeFont');
                  font.style.fontSize=this.editNode_form.fontSize+'px';
                },
                editShowStyle(){
                  var fontSize=10;
                  var iconSize=8;
                  switch (this.editShowStyle_form.fontSize){
                    case 25:{
                      fontSize=5;
                      break;
                    }
                    case 50:{
                      fontSize=15;
                      break;
                    }
                    case 75:{
                      fontSize=20;
                      break;
                    }
                    case 100:{
                      fontSize=25;
                      break;
                    }
                  }
                  switch (this.editShowStyle_form.iconSize){
                    case 25:{
                      iconSize=4;
                      break;
                    }
                    case 50:{
                      iconSize=12;
                      break;
                    }
                    case 75:{
                      iconSize=16;
                      break;
                    }
                    case 100:{
                      iconSize=20;
                      break;
                    }
                  }
                  this.$cy.style()
                    .selector('node')
                    .style({'label': 'data(name)', 'font-size': fontSize+'pt', 'width': iconSize+'pt', 'height':iconSize+'pt'})
                    .selector('edge')
                    .style({
                      'label': 'data(name)',
                      'target-arrow-shape': 'triangle-backcurve', /*箭头样式*/
                      'target-arrow-size': '1px', /*箭头大小*/
                      'font-size': fontSize+'px', /*标签字体大小*/
                      'color': '#000000', /*标签字体颜色*/
                      'text-outline-color': 'white', /*文本轮廓颜色*/
                      'text-outline-width': '1px', /*文本轮廓宽度*/
                      'text-rotation': 'autorotate', /*标签方向*/
                    }).update()
                  if(!this.editShowStyle_form.relationVisible){
                    this.$cy.style().selector('edge').style({'font-size': '0px'}).update()
                  }
                  this.editShowStyleVisible=false;
                },
                /***************************信息栏***********************/
                async getNumberOfNode(cid) {
                  //console.log("getNode here")
                  this.nodeNumber=await this.getNodeNumber(cid)
                },

                async getNumberOfEdge(cid) {
                  //console.log("getEdge here")
                  this.edgeNumber=await this.getEdgeNumber(cid)
                },
                /***************************信息栏***********************/


              },
            }
          </script>

