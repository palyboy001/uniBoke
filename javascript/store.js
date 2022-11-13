import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
	state:{
		servicrUrl:"http://192.168.0.103:8010/",//后端地址
		backgroundColor:"#4C8045",//导航栏背景
		inputStyle:{
			'color':'#4C8045'
		},//输入框样式
		//设备信息
		equipment:{
			screenWidth:0,//手机屏幕的宽
			screenHeight:0,//手机屏幕的高
		},
		color:"#FFFFFF",//导航栏文字颜色
		user: {
			nickName: "怕昵称",
			userId: "",
			infoImage: "/static/swipeImage/1.jpg",
			tle: "",
			address: "北京",
			sex: 1,
			birthday: "",
			personaliy: ""
		},//登录用户的信息
	},
	//修改status中的值
	mutations:{
		//修改屏幕的宽高
		setHeightAndWidth(state,vlaues){
			state.equipment.screenWidth = vlaues.width;
			state.equipment.screenHeight = vlaues.height
		}
	},
	//异步处理函数，修改state值需要调用mutations里面的方法
	actions:{}
})