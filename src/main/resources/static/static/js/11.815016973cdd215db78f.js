webpackJsonp([11],{THiX:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=i("3cXf"),a=i.n(r),n=i("x3UN"),l=i("cn9w"),s={data:function(){return{rolen:window.sessionStorage.rolen,allData:[],tableData:[],backupData:[],current_id:-1,updateAirlineBridgeForm:{airlineCd:"",airlinename:"",bridgerate:""},updateAirlineBridgeFormRules:{airlineCd:[{required:!0,message:"航司代码不能为空！",trigger:"blur"}],airlinename:[{required:!0,message:"航司名称不能为空！",trigger:"blur"}],bridgerate:[{required:!0,message:"100以内的整数",trigger:"blur"}]},newAirlineBridgeForm:{airlineCd:"",airlinename:"",bridgerate:""},newAirlineBridgeFormRules:{airlineCd:[{required:!0,message:"航司代码不能为空！",trigger:"blur"}],airlinename:[{required:!0,message:"航司名称不能为空！",trigger:"blur"}],bridgerate:[{required:!0,message:"100以内的整数",trigger:"blur"},{type:"number",message:"必须为数字值！",trigger:"blur"}]},select_airline_code:[],select_airline_name:[],bathcesForDelete:[],updateAirlineBridgeDialog:!1,newAirlineBridgeDialog:!1,multipleSelection:[],currentPage:1,totalCount:0,pageSizes:[5,10,20],PageSize:10}},created:function(){this.getData(),""===this.$store.state.airportNameVuex&&this.$message({message:"请在航班数据页面选择测试数据！",type:"warning",offset:300,center:!0,duration:3e3})},mounted:function(){},methods:{check_check:function(){console.log("CHECK-CHECK##",this.tableData)},getData:function(){var e=this,t={airportname:this.$store.state.airportNameVuex,userId:window.sessionStorage.usernamen};Object(n.k)(t).then(function(t){0==t.data.length||(e.allData=JSON.parse(a()(t.data)),e.tableData=JSON.parse(a()(e.allData)),console.log(t),e.totalCount=e.tableData.length,e.backupData=JSON.parse(a()(e.tableData)))})},SelectAll:function(){this.tableData=this.backupData,this.totalCount=this.tableData.length;for(var e=this.select_airline_name,t=this.select_airline_code,i=[],r=0;r<this.tableData.length;r++)-1!=this.tableData[r].airlinename.toString().indexOf(e)&&-1!=this.tableData[r].airlineCd.toString().indexOf(t)&&i.push(this.tableData[r]);this.tableData=i,this.totalCount=this.tableData.length,this.currentPage=1},reset:function(){this.select_airline_code=[],this.select_airline_name=[],this.tableData=this.backupData,this.totalCount=this.tableData.length},delete_in_bathces:function(){for(var e=this,t=0;t<this.multipleSelection.length;t++){var i=this.multipleSelection[t];this.bathcesForDelete.push(i)}0==this.bathcesForDelete.length?this.$confirm("请选择需要删除的数据","提示",{type:"warning"}):(console.log(this.bathcesForDelete),this.$confirm("确认删除所选数据吗","提示",{type:"warning"}).then(function(){Object(n.e)(e.bathcesForDelete).then(function(){e.getData().then(function(){e.multipleSelection=[],e.bathcesForDelete=[],e.$message({message:"delete successly"})})})}))},Delete:function(e){var t=this;this.$confirm("确认删除所选数据吗","提示",{type:"warning"}).then(function(){var i={id:e};console.log(i),Object(n.e)(i).then(function(e){console.log(e)}).then(function(){t.getData(),console.log("删除成功！"),t.$message({message:"delete successly"})})})},New:function(){this.newAirlineBridgeDialog=!0,this.newAirlineBridgeForm.airlineCd="",this.newAirlineBridgeForm.airlinename="",this.newAirlineBridgeForm.bridgerate="",this.resetNewForm()},change:function(e){this.updateAirlineBridgeDialog=!0,this.current_id=e;var t=this.tableData.findIndex(function(t){if(t.id==e)return!0}),i=this.tableData[t];this.updateAirlineBridgeForm.airlineCd=i.airlineCd,this.updateAirlineBridgeForm.airlinename=i.airlinename,this.updateAirlineBridgeForm.bridgerate=parseInt(i.bridgerate)},Confirm_update:function(){var e=this,t={airlinename:this.updateAirlineBridgeForm.airlinename,airlineCd:this.updateAirlineBridgeForm.airlineCd,id:this.current_id,bridgerate:this.updateAirlineBridgeForm.bridgerate,userId:window.sessionStorage.usernamen,updateTime:Object(l.a)()};t.bridgerate>100?this.$message({message:"靠桥率小于100！",type:"error",center:!0}):this.$refs.updateAirlineBridgeFormref.validate(function(i){if(!i)return alert("请输入相关信息"),!1;Object(n.K)(t).then(function(t){e.$message({message:"修改信息成功",type:"success",center:!0}),e.updateAirlineBridgeDialog=!1}).then(function(){e.getData()})})},Confirm_new:function(){var e=this,t={airlinename:this.newAirlineBridgeForm.airlinename,airlineCd:this.newAirlineBridgeForm.airlineCd,bridgerate:this.newAirlineBridgeForm.bridgerate,airportname:this.$store.state.airportNameVuex,userId:window.sessionStorage.usernamen,updateTime:Object(l.a)()};t.bridgerate>100?this.$message({message:"靠桥率小于100！",type:"error",center:!0}):this.$refs.newAirlineBridgeFormref.validate(function(i){if(!i)return alert("请填写相关信息"),!1;console.log(t),Object(n.r)(t).then(function(){e.$message({message:"插入信息成功",type:"success",center:!0}),e.newAirlineBridgeDialog=!1}).then(function(){e.getData()})})},resetNewForm:function(){this.$refs.newForm.resetFields()},resetUpdateForm:function(){this.$refs.updateForm.resetFields()},rule_detail:function(){},toggleSelection:function(e){var t=this;e?e.forEach(function(e){t.$refs.multipleTable.toggleRowSelection(e)}):this.$refs.multipleTable.clearSelection()},handleSelectionChange:function(e){this.multipleSelection=e},handleSizeChange:function(e){this.PageSize=e,this.currentPage=1},handleCurrentChange:function(e){this.currentPage=e}}},o={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("div",{staticClass:"crumbs"},[i("el-breadcrumb",{attrs:{separator:"/"}},[i("el-breadcrumb-item",{attrs:{to:{path:"/Dashboard"}}},[i("i",{staticClass:"el-icon-lx-text"}),e._v(" 规则配置\n      ")]),e._v(" "),i("el-breadcrumb-item",[e._v("航司靠桥率限制")])],1)],1),e._v(" "),i("div",{staticClass:"container"},[i("div",{staticClass:"handle-box"},[i("span",[e._v("航司名称:")]),e._v(" "),i("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"请输入"},model:{value:e.select_airline_name,callback:function(t){e.select_airline_name=t},expression:"select_airline_name"}}),e._v(" "),i("span",[e._v("航司代码:")]),e._v(" "),i("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"请输入"},model:{value:e.select_airline_code,callback:function(t){e.select_airline_code=t},expression:"select_airline_code"}}),e._v(" "),i("el-button",{staticClass:"handle-select mr10",attrs:{type:"primary"},on:{click:e.SelectAll}},[e._v("查询")]),e._v(" "),i("el-button",{staticClass:"handle-reset mr10",on:{click:e.reset}},[e._v("重置")])],1),e._v(" "),i("div",{staticClass:"handle-box1"},[i("el-button",{staticClass:"handle-select mr10",attrs:{type:"primary",icon:"el-icon-circle-plus-outline"},on:{click:e.New}},[e._v("新建")]),e._v(" "),i("el-button",{staticClass:"handle-reset mr10",attrs:{type:"danger",icon:"el-icon-delete"},on:{click:e.delete_in_bathces}},[e._v("批量删除")])],1),e._v(" "),i("el-table",{ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:e.tableData.slice((e.currentPage-1)*e.PageSize,e.currentPage*e.PageSize),border:"","tooltip-effect":"dark"},on:{"selection-change":e.handleSelectionChange}},[i("el-table-column",{attrs:{prop:"select_box",type:"selection",width:"55",align:"center"}}),e._v(" "),i("el-table-column",{attrs:{prop:"airlineCd",label:"航司代码",width:"100",align:"center"}}),e._v(" "),i("el-table-column",{attrs:{prop:"airlinename",label:"航司名称",width:"350",align:"center"}}),e._v(" "),i("el-table-column",{attrs:{prop:"bridgerate",label:"靠桥率",width:"250",align:"center","show-overflow-tooltip":""}}),e._v(" "),i("el-table-column",{attrs:{prop:"methods",label:"操作",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{type:"text",icon:"el-icon-edit"},on:{click:function(i){return e.change(t.row.id)}}},[e._v("修改")]),e._v(" "),i("el-button",{staticClass:"red",attrs:{type:"text",icon:"el-icon-delete"},on:{click:function(i){return e.Delete(t.row.id)}}},[e._v("删除")])]}}])})],1),e._v(" "),i("el-dialog",{ref:"updateForm",attrs:{visible:e.updateAirlineBridgeDialog,width:"40%",title:"修改航司靠桥率限制"},on:{"update:visible":function(t){e.updateAirlineBridgeDialog=t}}},[i("el-form",{ref:"updateAirlineBridgeFormref",staticClass:"form-inline",attrs:{inline:!0,model:e.updateAirlineBridgeForm,rules:e.updateAirlineBridgeFormRules}},[i("el-form-item",{attrs:{label:"航司代码",prop:"airlineCd"}},[i("el-input",{attrs:{placeholder:"航司代码"},model:{value:e.updateAirlineBridgeForm.airlineCd,callback:function(t){e.$set(e.updateAirlineBridgeForm,"airlineCd",t)},expression:"updateAirlineBridgeForm.airlineCd"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"航司名称",prop:"airlinename"}},[i("el-input",{attrs:{placeholder:"航司名称"},model:{value:e.updateAirlineBridgeForm.airlinename,callback:function(t){e.$set(e.updateAirlineBridgeForm,"airlinename",e._n(t))},expression:"updateAirlineBridgeForm.airlinename"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"靠桥率",prop:"bridgerate"}},[i("el-input",{attrs:{placeholder:"100以内的整数"},model:{value:e.updateAirlineBridgeForm.bridgerate,callback:function(t){e.$set(e.updateAirlineBridgeForm,"bridgerate",e._n(t))},expression:"updateAirlineBridgeForm.bridgerate"}})],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.Confirm_update}},[e._v("确认修改")])],1)],1)],1),e._v(" "),i("el-dialog",{ref:"newForm",attrs:{visible:e.newAirlineBridgeDialog,width:"40%",title:"新建航司靠桥率限制"},on:{"update:visible":function(t){e.newAirlineBridgeDialog=t}}},[i("el-form",{ref:"newAirlineBridgeFormref",staticClass:"form-inlinareae",attrs:{inline:!0,model:e.newAirlineBridgeForm,rules:e.newAirlineBridgeFormRules}},[i("el-form-item",{attrs:{label:"航司代码",prop:"airlineCd"}},[i("el-input",{attrs:{placeholder:"请输入航司代码"},model:{value:e.newAirlineBridgeForm.airlineCd,callback:function(t){e.$set(e.newAirlineBridgeForm,"airlineCd",t)},expression:"newAirlineBridgeForm.airlineCd"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"航司名称",prop:"airlinename"}},[i("el-input",{attrs:{placeholder:"请输入航司名称"},model:{value:e.newAirlineBridgeForm.airlinename,callback:function(t){e.$set(e.newAirlineBridgeForm,"airlinename",t)},expression:"newAirlineBridgeForm.airlinename"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"靠桥率",prop:"bridgerate"}},[i("el-input",{attrs:{placeholder:"100以内的整数"},model:{value:e.newAirlineBridgeForm.bridgerate,callback:function(t){e.$set(e.newAirlineBridgeForm,"bridgerate",e._n(t))},expression:"newAirlineBridgeForm.bridgerate"}})],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.Confirm_new}},[e._v("确认添加")])],1)],1)],1),e._v(" "),i("div",{staticClass:"pagination",staticStyle:{float:"right"}},[i("el-pagination",{attrs:{background:"",layout:"total, sizes, prev, pager, next, jumper","current-page":e.currentPage,"page-sizes":e.pageSizes,"page-size":e.PageSize,total:e.totalCount},on:{"current-change":e.handleCurrentChange,"size-change":e.handleSizeChange}})],1),e._v(" "),i("div",{staticClass:"handle-box2"},[i("el-button",{on:{click:function(t){return e.toggleSelection()}}},[e._v("取消选择")])],1)],1)])},staticRenderFns:[]};var c=i("C7Lr")(s,o,!1,function(e){i("Y0XR")},"data-v-3c948c11",null);t.default=c.exports},Y0XR:function(e,t){}});
//# sourceMappingURL=11.815016973cdd215db78f.js.map