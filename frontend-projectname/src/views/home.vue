<script>
  import CJS from './components/cjs';
  import {findChartAPI, saveChartAPI} from "@/api/chart";
  import {set_chartId} from "../store/modules/chart"
  import { mapActions } from "vuex"

  export default {
    name: "Test",
    components: {CJS,},
    watch: {},
    data() {
      return {
        data:{}
      };
    },
    mounted() {
      this.addEles();
      this.getNumberOfEdge(4);
      this.getNumberOfNode(4)
    },
    methods: {
      ...mapActions([
        "findChart"
      ]),
      //TODO:模拟数据

      async addEles() {
        this.data =await this.findChart(4);
        console.log(this.data);
        let mydata = new Array(this.data.length);
        for(var i=0;i<this.data.length;i++) {
          if(this.data[i].group === "nodes"){
            let obj = {
              "group": this.data[i].group,
              "data": {
                "tid": this.data[i].data.id,
                "id": this.data[i].data.name,
                "name": this.data[i].data.name
              },
              "classes": this.data[i].classes,
              "position": {
                "x": this.data[i].position.x,
                "y": this.data[i].position.y
              }
            }
            //console.log(obj)
            mydata[i] = obj
          }else {
            let obj = {
              "group": this.data[i].group,
              "data": {
                "id": this.data[i].data.id,
                "name": this.data[i].data.name,
                "source": this.data[i].data.source,
                "target": this.data[i].data.target
              },
              "classes": this.data[i].classes
            }
            mydata[i] = obj
          }
        }
        console.log("mydata")
        console.log(mydata)
        this.$refs['ref_CJS'].addEles(
          //console.log("4"),
          mydata
          // [{group: 'nodes', data: {id: 'zlm', name: 'zlm'}, classes: 'classes-A', position: {x: 100, y: 0}},
          // {group: 'nodes', data: {id: '学生', name: '学生'}, classes: 'classes-A', position: {x: 700, y: 0}},
          // {group: 'nodes', data: {id: '男性', name: '男性'}, classes: 'classes-A', position: {x: 150, y: 100}},
          // {group: 'nodes', data: {id: '游戏', name: '游戏'}, classes: 'classes-A', position: {x: 300, y: 150}},
          // {group: 'nodes', data: {id: '干饭', name: '干饭'}, classes: 'classes-A', position: {x: 0, y: 100}},
          // {group: 'nodes', data: {id: '代码', name: '代码'}, classes: 'classes-A', position: {x: 250, y: 200}},
          // {group: 'nodes', data: {id: '甜点', name: '甜点'}, classes: 'classes-A', position: {x: 400, y: 250}},
          // {group: 'edges', data: {id: 'e0', name: 'is', source: 'zlm', target: '学生'},classes:'relationA'},
          // {group: 'edges', data: {id: 'e1', name: 'is', source: 'zlm', target: '男性'},classes:'relationA'},
          // {group: 'edges', data: {id: 'e2', name: 'like', source: 'zlm', target: '游戏'},classes:'relationA'},
          // {group: 'edges', data: {id: 'e3', name: 'like', source: 'zlm', target: '甜点'},classes:'relationA'},
          // {group: 'edges', data: {id: 'e4', name: 'like', source: 'zlm', target: '干饭'},classes:'relationA'},
          // {group: 'edges', data: {id: 'e5', name: 'like', source: '男性', target: '游戏'},classes:'relationA'},
          // {group: 'edges', data: {id: 'e6', name: 'like', source: '男性', target: '代码'},classes:'relationA'},]
        );
        //console(findChart(4))
      },

      async  getNumberOfNode(cid) {
        this.$refs['ref_CJS'].getNumberOfNode(cid);
      },
      async  getNumberOfEdge(cid) {
        this.$refs['ref_CJS'].getNumberOfEdge(cid);
      },

      //删除节点

      delEles() {
        this.$refs['ref_CJS'].delEles();
      },
    },
  }
</script>

<style scoped>
  .page{
    height: 100%; width: 100%; border: 1px solid #7373dc
  }

</style>
