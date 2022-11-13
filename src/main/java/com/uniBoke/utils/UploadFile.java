package com.uniBoke.utils;

import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.RequestMessageBody;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class UploadFile {

//    Logger logger = Logger.getLogger(UploadFile.class);

    @Autowired
    private RandomChar randomChar;
    @Value("${url}")
    private String url;
    @Value("${imageUrl}")
    private String imageUrl;

    /**
     * @param file MultipartFile文件
     * @param filePath 保存的文件路径
     * @param userID userID
     * @param typeImage 需要保存什么类型的图片，保存到对应路径
     * @return 返回保存好的虚拟路径
     * */
    public String save(MultipartFile file,String filePath, String userID,String typeImage){
        OutputStream outputStream =null;
        if(file != null){
            try {
                // 转换字节
                byte[] imageByte = file.getBytes();
                // 获取文件名
                String fileName = file.getOriginalFilename();
                // 获取点号的位置
                int index = fileName.indexOf(".");
                // 截取文件后缀
                String suffix = fileName.substring(index);

                // 创建用户目录
                String path = filePath + userID + "\\" + typeImage;
                File userFilePath = new File(path);
                // 判断用户文件夹是否存在
                if(!userFilePath.exists()){
                    userFilePath.mkdirs();
                }
                // 生成新文件名
                String _fileName = "";
                // 新文件名加后缀
                String _fileName_ = "";
                File saveFile = null;
                // 文件重名,重复生成di
                do {
                    _fileName = randomChar.getRandomString(10,true);
                    _fileName_ = _fileName.concat(suffix);
                    saveFile = new File(path,_fileName_);
//                    logger.info("头像文件生成id："+_fileName);
                }while (!saveFile.createNewFile());
                outputStream = new FileOutputStream(saveFile);
                outputStream.write(imageByte);
                outputStream.flush();
                String image_url = url+imageUrl+userID+ "/userImage/" +_fileName_;
//                logger.info("头像文件："+_fileName_+"保存成功");
//                logger.info("头像文件地址为：："+image_url);
                return image_url;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }finally {
                try {
                    if(outputStream != null){
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }
}
