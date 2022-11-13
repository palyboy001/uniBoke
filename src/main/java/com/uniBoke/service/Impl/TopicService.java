package com.uniBoke.service.Impl;

import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.RequestMessageBody;
import com.uniBoke.entity.Topic;
import com.uniBoke.mapper.UserMapper;
import com.uniBoke.service.topicServiceInterface;
import com.uniBoke.utils.RandomChar;
import com.uniBoke.utils.UploadFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TopicService implements topicServiceInterface {

    Logger logger = Logger.getLogger(TopicService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RandomChar randomChar;

    @Autowired
    private UploadFile uploadFile;

    @Value("${filePath}")
    private String filePath;

    /**
     * 发布话题页
     * */
    @Override
    public RequestMessage sendTopic(Topic topic) {
        System.out.println(topic);
        return null;
    }

    /**
     * 上传图片
     * */
    @Override
    public RequestMessage uploadService(MultipartFile[] files, String userID) {
        List<String> list = new ArrayList<String>(8);
        System.out.println(files);
        int i = 1;
        for(MultipartFile file: files){
            // 保存文件，返回虚拟路径
            String url = uploadFile.save(file,filePath,userID,"topicImage");
            if(url == null){
                logger.info("第"+i+"文件保存失败");
                // 文件保存失败
                return new RequestMessage(RequestMessageBody.save_file_fail.getMessage(),
                        RequestMessageBody.save_file_fail.getCode(),null);
            }
            list.add(url);
            i++;
        }
        // 文件保存失败
        return new RequestMessage(RequestMessageBody.save_file_fail.getMessage(),
                RequestMessageBody.save_file_fail.getCode(),list);
    }
}
