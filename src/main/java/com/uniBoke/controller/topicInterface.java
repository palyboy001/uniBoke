package com.uniBoke.controller;

import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.Topic;
import org.springframework.web.multipart.MultipartFile;

public interface topicInterface {
//    sendTopic：发布话题
    RequestMessage sendTopic(Topic topic);
//    uploadFiles：上传图片
    RequestMessage uploadFiles(MultipartFile[] files, String userID);
}
