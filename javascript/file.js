// #ifdef APP-PLUS


let environment = plus.android.importClass("android.os.Environment");
var rootDir = environment.getExternalStorageDirectory(); //文件夹根目录
var File = plus.android.importClass("java.io.File");
var sdRoot = rootDir + "/bokeApp"
var BufferedReader = plus.android.importClass("java.io.BufferedReader");
var FileReader = plus.android.importClass("java.io.FileReader");
var FileWriter = plus.android.importClass("java.io.FileWriter");
var RandomAccessFile = plus.android.importClass("java.io.RandomAccessFile");
var Str = plus.android.importClass("java.lang.String");

/**
 * 写入字体文件（base64）
 * @param {数据} res 
 * @param {文件名加后缀} fileName
 * @param {文件路径} fileDir
 */
export const writeFontFile = function(res, fileName,fileDir) {
	console.log(res)
	try{
		//判断是否存在font文件夹
		let file = new File(sdRoot + "/" + fileDir);
		
		//目录不存在创建目录
		if(!file.exists()){
			file.mkdirs();
		}

		//打开文件
		let fileText = new File(sdRoot + "/" + fileDir+ "/" + fileName);
		if(!fileText.exists()){
			//如果文件不存在，创建文件
			fileText.createNewFile();
		}
		
		let writeFile = new FileWriter(sdRoot + "/"+fileDir+"/" + fileName);
		writeFile.write(res);
		writeFile.close();
		return "写入成功";
	}catch(e){
		//TODO handle the exception
		console.log(e);
		return
	}
	
	return "写入失败";
}

/**
 * 读取文件第一行
 * @param {文件路径} fileDir 
 * @param {文件名字加后缀} fileName 
 * @param {回调函数} call 
 */
export const readFileTopLine = function(fileDir,fileName,call){
	//打开文件
	let readFile = new File(sdRoot + "/" + fileDir + "/" + fileName);
	try{
		//建立读取缓冲区
		let reader = new BufferedReader(new FileReader(readFile));
		let text = reader.readLine();
		reader.close();
		call(text);
		return
	}catch(e){
		//TODO handle the exception
		console.log("读取失败！！")
	}
}

/**
 * 判断文件是否存在
 * @param {文件路径} fileDir 
 * @param {文件名字包含后缀} fileName 
 */
export const judgeFileExists = function(fileDir,fileName){
	let file = new File(sdRoot + "/" + fileDir + "/" + fileName);
	return file.exists();
}


// #endif