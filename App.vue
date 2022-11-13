<script>
	import {writeFontFile,readFileTopLine,judgeFileExists} from '@/javascript/file.js';
	import {mapMutations} from 'vuex'
	export default {
		onLaunch: function() {
			let _this = this
			console.log('App Launch')
			//获取手机的系统状态栏高度

			// #ifdef APP-PLUS
			getApp().globalData.statusBarHeight = plus.navigator.getStatusbarHeight()
			// #endif
			// console.log(uni.getSystemInfoSync().statusBarHeight)
			// 获取手机信息
			uni.getSystemInfo({
				success: (res) => {
					console.log(res.screenWidth)
					console.log(res.screenHeight)
					getApp().globalData.screenWidth = res.screenWidth
					getApp().globalData.screenHeight = res.screenHeight
				},
				fail: function ()  {
					console.log("获取失败！")
				}
			})
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
		globalData:{
			//整个屏幕的宽高
			screenWidth:0,
			screenHeight:0,
			statusBarHeight:0,
			testst:"132"
		},
		methods:{
			// 预览图片
			lookImage(url){
				console.log(5555)
				uni.previewImage({
					urls: url,
					longPressActions: {
						itemList: ['发送给朋友', '保存图片', '收藏'],
						success: function(data) {
							console.log('选中了第' + (data.tapIndex + 1) + '个按钮,第' + (data.index + 1) + '张图片');
						},
						fail: function(err) {
							console.log(err.errMsg);
						}
					}
				});
			}
			
		},
		beforeCreate() {
			
			// #ifndef APP-PLUS
			uni.loadFontFace({
				family: 'unapphyx',
				source: 'url("http://192.168.0.103:8010/changefile/downloadFile?file=HanYingXiu.ttf")',
				success() {
					console.log('success')
				},
				fail(){
					console.log("fail")
				}
			})
			uni.loadFontFace({
			  family: 'unapphwkt',
			  source: 'url("http://192.168.0.103:8010/changefile/downloadFile?file=HuaWenKaiTi.ttf")',
			  success() {
				  console.log('success')
			  },
				fail(){
					console.log("fail")
				}
			})
			// #endif
			
			// #ifdef APP-PLUS 
			// 缓存字体
			// if(plus.storage.getItem("font") == null){
			// 	console.log(this.$store.state.servicrUrl)
				
			// }else{
			// 	console.log("存在")
			// }
			// const domModule = uni.requireNativePlugin('dom')
			// domModule.addRule('fontFace', {
			// 	'fontFamily': "myIconfont",
			// 	'src': "url("+"'"+getApp().globalData.font+"'"+")"
			// });
			console.log(plus.io.convertLocalFileSystemURL("static/font/HuaWenKaiTi.ttf"))
			//获取字体
			const domModule = weex.requireModule('dom')
			domModule.addRule('fontFace', {
				'fontFamily': "hyx",
				'src': "url('http://192.168.0.103:8010/changefile/downloadFile?file=HanYingXiu.ttf')"
			});
			domModule.addRule('fontFace', {
				'fontFamily': "hwxk",
				'src': "url('file:/"+ plus.io.convertLocalFileSystemURL("static/font/HuaWenKaiTi.ttf") +"')"
			});
			//获取字体图标
			domModule.addRule('fontFace',{
				'fontFamily': "iconfont",
				'src': "url('file:/"+ plus.io.convertLocalFileSystemURL("static/font/fontIcon/iconfont.ttf") +"')"
			})
			// #endif 
		}
	}
</script>

<style>
	/*每个页面公共css */
	/* 字体的主题颜色 */
	.firstColor-font{
		color: #4C8045;
	}
	/* 主题背景颜色 */
	.firstBackgroundColor{
		background-color: #4C8045;
	}
	/* 导航栏文字颜色 */
	.nav-font{
		color: #FFFFFF;
	}
	/* 顶部状态栏样式 */
	.status_bar{
		background-color: #FFFFFF;
	}
	
	/* #ifndef APP-PLUS */
	.textHua{
		font-family: unapphyx;
	}
	.textHanYing{
		font-family: unapphwkt;
	}
	/* #endif */
	/* 字体样式 */
	.textHua{
		font-family: hwxk;
	}
	.textHanYing{
		font-family: hyx;
	}
	
	/* 字体图标 */
	.iconfont{
		font-family: iconfont !important;
	}
</style>
