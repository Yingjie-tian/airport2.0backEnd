webpackJsonp([3],{"4prm":function(e,t,s){e.exports=s.p+"static/img/bjtu-logo.e6c954a.png"},"4y5I":function(e,t){},"9BCT":function(e,t,s){e.exports=s.p+"static/img/0001.d9c0c7b.png"},BuHZ:function(e,t){},NaOX:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=s("lC5x"),i=s.n(r),o=s("a3Yh"),n=s.n(o),a=s("J0Oq"),l=s.n(a),c={data:function(){var e=this;return{loginForm:{username:"",password:""},registerForm:{usernames:"",passwords:"",checkpasswords:""},usersTable:"",ulist:[],ordinary:"ordinary",loginFormRules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"},{min:0,max:10,message:"长度为0~10个字符",trigger:"blur"}],password:[{required:!0,message:"请输入登录密码",trigger:"blur"},{min:6,max:10,message:"长度为6~10个字符",trigger:"blur"}]},registerFormRules:{usernames:[{required:!0,message:"请输入用户名",trigger:"blur"},{min:0,max:10,message:"长度为0~10个字符",trigger:"blur"},{validator:function(t,s,r){e.getUsers();for(var i=0;i<e.usersTable.length;i++)e.ulist.push(e.usersTable[i].username);for(var o=0;o<e.ulist.length;o++)s===e.ulist[o]&&r(new Error("该用户名已被注册，请重新输入"))},trigger:"blur"}],passwords:[{required:!0,message:"请输入登录密码",trigger:"blur"},{min:6,max:10,message:"长度为6~10个字符",trigger:"blur"}],checkpasswords:[{min:6,max:10,message:"长度为6~10个字符",trigger:"blur"},{validator:function(t,s,r){""===s?r(new Error("请再次输入密码")):s!==e.registerForm.passwords?r(new Error("两次输入密码不一致!")):r()},trigger:"blur"}]},showlogin:!1,showregister:!1,pictrues:[{url:s("9BCT")},{url:s("hObQ")},{url:s("orJ4")}],imgHeight:"",fullwidth:document.documentElement.clientWidth}},created:function(){this.getUsers()},watch:{fullwidth:function(e){this.timer||(this.fullwidth=e,this.timer=!0,setTimeout(function(){this.timer=!1},400))}},methods:{getUsers:function(){var e=this;return l()(i.a.mark(function t(){return i.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,e.$axios({method:"get",url:"/user/selectAllUsers",headers:n()({token:window.sessionStorage.token,"Content-type":"x-www-form-urlencoded;charset=UTF-8"},"Content-type","application/json")}).then(function(t){e.usersTable=t.data});case 2:case"end":return t.stop()}},t,e)}))()},resetLoginForm:function(){this.$refs.loginFormRef.resetFields()},resetregisterForm:function(){this.$refs.registerFormRef.resetFields()},login:function(){var e,t=this,s=this.loginForm.username,r=this.loginForm.password;this.$refs.loginFormRef.validate((e=l()(i.a.mark(function e(o){return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(o){e.next=2;break}return e.abrupt("return");case 2:return e.next=4,t.$axios({method:"post",url:"/user/login?username="+s+"&password="+r,headers:n()({"Content-type":"x-www-form-urlencoded;charset=UTF-8"},"Content-type","application/json")}).then(function(e){if(1e4!==e.data.code)return t.$message({message:"用户名或密码错误，登录失败！",type:"error",offset:100,center:!0,duration:2e3});t.$message({message:"登录成功！",type:"success",offset:300,center:!0,duration:1e3});var s=e.data.data.token;t.$store.commit("set_token",s);var r=e.data.data.role;t.$store.commit("set_role",r);var i=e.data.data.userName;t.$store.commit("set_username",i),t.$router.push("/flightData"),t.showregister=!1}).catch(function(e){console.log(e)});case 4:case"end":return e.stop()}},e,t)})),function(t){return e.apply(this,arguments)}))},register:function(){var e=this,t={username:this.registerForm.usernames,password:this.registerForm.passwords,name:this.ordinary};this.registerForm.checkpasswords===this.registerForm.passwords&&this.$axios({method:"post",url:"/user/insertUser",data:t,headers:n()({token:window.sessionStorage.token,"Content-type":"x-www-form-urlencoded;charset=UTF-8"},"Content-type","application/json")}).then(function(){e.showregister=!1,e.showlogin=!0})},setHeightSize:function(){var e=document.documentElement.clientWidth;this.imgHeight=722/1365*e},getBodyWidth:function(){var e=this;window.onresize=function(){return function(){window.fullwidth=document.documentElement.clientWidth,e.fullwidth=window.fullwidth}}}},mounted:function(){var e=this;this.getBodyWidth(),this.setHeightSize(),window.onresize=function(){e.setHeightSize()}}},u={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"loginContainer",style:"width:"+e.fullwidth+"px;"},[s("div",{staticClass:"header-top"},[s("div",{staticClass:"header-flex"},[e._m(0),e._v(" "),e._m(1),e._v(" "),s("div",{staticClass:"top-login"},[s("span",{on:{click:function(t){e.showlogin=!e.showlogin,e.showregister=!1}}},[e._v("登录")]),e._v(" "),s("span",{on:{click:function(t){e.showregister=!e.showregister,e.showlogin=!1}}},[e._v("注册")])])])]),e._v(" "),s("div",{staticClass:"bottom"},[s("div",{staticClass:"carousel"},[s("el-carousel",{attrs:{interval:3e3,arrow:"always",height:"100vh"}},e._l(e.pictrues,function(e,t){return s("el-carousel-item",{key:t},[s("img",{attrs:{src:e.url,alt:"图片未加载"}})])}),1)],1),e._v(" "),e.showlogin?s("div",{staticClass:"loginBox"},[s("div",{staticClass:"loginx",on:{click:function(t){e.showlogin=!1}}},[s("i",{staticClass:"el-icon-close"})]),e._v(" "),e._m(2),e._v(" "),s("el-form",{ref:"loginFormRef",staticClass:"loginForm",attrs:{model:e.loginForm,rules:e.loginFormRules}},[s("el-form-item",{staticClass:"loginUP",attrs:{prop:"username"}},[s("el-input",{attrs:{"prefix-icon":"iconfont icon-user",placeholder:"用户名"},model:{value:e.loginForm.username,callback:function(t){e.$set(e.loginForm,"username",t)},expression:"loginForm.username"}})],1),e._v(" "),s("el-form-item",{staticClass:"loginUP",attrs:{prop:"password"}},[s("el-input",{attrs:{"prefix-icon":"iconfont icon-3702mima",type:"password",placeholder:"密码"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.login.apply(null,arguments)}},model:{value:e.loginForm.password,callback:function(t){e.$set(e.loginForm,"password",t)},expression:"loginForm.password"}})],1),e._v(" "),s("el-form-item",{staticClass:"loginBtu"},[s("el-button",{attrs:{type:"primary"},on:{click:e.login}},[e._v("登录")]),e._v(" "),s("el-button",{attrs:{type:"info"},on:{click:e.resetLoginForm}},[e._v("重置")])],1)],1)],1):e._e(),e._v(" "),e.showregister?s("div",{staticClass:"registerBox"},[s("div",{staticClass:"loginx",on:{click:function(t){e.showregister=!1}}},[s("i",{staticClass:"el-icon-close"})]),e._v(" "),e._m(3),e._v(" "),s("el-form",{ref:"registerFormRef",staticClass:"registerForm",attrs:{model:e.registerForm,rules:e.registerFormRules}},[s("el-form-item",{staticClass:"registerUP",attrs:{prop:"usernames"}},[s("el-input",{attrs:{placeholder:"用户名"},model:{value:e.registerForm.usernames,callback:function(t){e.$set(e.registerForm,"usernames",t)},expression:"registerForm.usernames"}})],1),e._v(" "),s("el-form-item",{staticClass:"registerUP",attrs:{prop:"passwords"}},[s("el-input",{staticClass:"registerinput",attrs:{"prefix-icon":"iconfont icon-3702mima",type:"password",placeholder:"密码"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.login.apply(null,arguments)}},model:{value:e.registerForm.passwords,callback:function(t){e.$set(e.registerForm,"passwords",t)},expression:"registerForm.passwords"}})],1),e._v(" "),s("el-form-item",{staticClass:"registerUP",attrs:{prop:"checkpasswords"}},[s("el-input",{attrs:{"prefix-icon":"iconfont icon-3702mima",type:"password",placeholder:"重复密码"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.login.apply(null,arguments)}},model:{value:e.registerForm.checkpasswords,callback:function(t){e.$set(e.registerForm,"checkpasswords",t)},expression:"registerForm.checkpasswords"}})],1),e._v(" "),s("el-form-item",{staticClass:"loginBtu"},[s("el-button",{attrs:{type:"primary"},on:{click:e.register}},[e._v("注册")]),e._v(" "),s("el-button",{attrs:{type:"info"},on:{click:e.resetregisterForm}},[e._v("重置")])],1)],1)],1):e._e()])])},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"header-logo"},[t("img",{attrs:{src:s("4prm"),alt:"图片"}})])},function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"header-text"},[t("span",{staticClass:"top-c"},[this._v("机位分配人员辅助训练系统")]),this._v(" "),t("br"),this._v(" "),t("span",{staticClass:"top-e"},[this._v("Auxiliary Training System for Aircraft Allocation Personnel")])])},function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"loginTitle"},[t("h2",[this._v("请输入账号和密码")])])},function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"registerTitle"},[t("h2",[this._v("用户注册")])])}]};var m=s("C7Lr")(c,u,!1,function(e){s("4y5I"),s("BuHZ"),s("hTJI")},"data-v-475111a8",null);t.default=m.exports},hObQ:function(e,t,s){e.exports=s.p+"static/img/0002.34975f6.png"},hTJI:function(e,t){},orJ4:function(e,t,s){e.exports=s.p+"static/img/0003.4c4da4b.png"}});
//# sourceMappingURL=3.51eb3f215bd2950846e3.js.map