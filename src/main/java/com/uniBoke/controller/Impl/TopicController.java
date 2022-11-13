package com.uniBoke.controller.Impl;

import com.uniBoke.controller.topicInterface;
import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.Topic;
import com.uniBoke.service.Impl.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/topic")
@ResponseBody
public class TopicController implements topicInterface {

    @Autowired
    private TopicService topicService;

    /**
     * 发布话题
     * */
    @PostMapping("/sendTopic")
    @Override
    public RequestMessage sendTopic(@RequestBody Topic topic) {
        return topicService.sendTopic(topic);
    }

    /**
     * 上传图片
     *
     */
    @RequestMapping("/uploadTopicImage")
    @Override
    public RequestMessage uploadFiles(MultipartFile[] files, @RequestParam("id") String userID) {
        System.out.println(userID);
        return topicService.uploadService(files,userID);
//        System.out.println("多文件上传");
////        System.out.println("length" + files.length);
//        OutputStream outputStream = null;
////        int i = 1;
////        ArrayList<String> img = new ArrayList<String>();
//        for (MultipartFile file : files){
//            if (file != null) {
//                try {
//                    byte[] data = file.getBytes();
////                    System.out.println(data);
//                    String fileRealName = file.getOriginalFilename();//获得原始文件名;
////                    System.out.println(fileRealName);
//                    int pointIndex =  fileRealName.lastIndexOf(".");//点号的位置
//                    String fileSuffix = fileRealName.substring(pointIndex);//截取文件后缀
////                将两个字符串合并再一起,形成新的文件名
//                    //生成四个字符串
//                    String newChar = randomChar.getRandomString(4);
//                    String saveFileName = newChar.concat(fileSuffix);//新文件完整名（含后缀）
//                    String filePath  = "E:\\idea\\buke\\src\\main\\webapp\\img\\" + userID + "\\topicImg";
////                    System.out.println(filePath);
//
//                    File path = new File(filePath); //判断文件路径下的文件夹是否存在，不存在则创建
//                    if (!path.exists()) {
//                        path.mkdirs();
//                    }
//                    //创建文件
//                    File savedFile = new File(filePath,saveFileName);
//                    boolean isCreateSuccess = savedFile.createNewFile(); // 是否创建文件成功
////                    System.out.println("文件路径为：" + savedFile.getPath());
//                    if(!isCreateSuccess){      //文件名存在则重新生成文件名
//                        //第一种
////                    file.transferTo(savedFile);
//                        //第二种
////                    savedFile = new File(filePath,saveFileName);
//                        System.out.println("文件存在-----");
//                        //生成新的文件名
//                        String fileName = randomChar.getRandomString(4);
//                        String newSaveFileName = fileName.concat(fileSuffix);//新文件完整名（含后缀）
//                        //创建新的文件
//                        File newSavedFile = new File(filePath,newSaveFileName);
////                        System.out.println("新完整路径："+newSavedFile.getPath());
//                        outputStream = new FileOutputStream(newSavedFile);
//                        outputStream.write(data);
//
//                        outputStream.close();
//
//                        String imgUrl =ip + url + "/" + userID + "/topicImg/" + newSaveFileName;
////                        System.out.println(imgUrl);
//
//                        return imgUrl;
//
//                        // 使用下面的jar包
////                    FileUtils.copyInputStreamToFile(file.getInputStream(),savedFile);
//                    }
//                    outputStream = new FileOutputStream(savedFile);
//                    outputStream.write(data);
////                    System.out.println(url + "/" + userID + "/topicImg/" + saveFileName);
//                    String imgUrl = url + "/" + userID + "/topicImg/" + saveFileName;
//                    outputStream.close();
//                    return imgUrl;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }finally {
//                    try {
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }else {
//                System.out.println("文件是空的");
//            }
//        }
//        return "false";
    }

}
