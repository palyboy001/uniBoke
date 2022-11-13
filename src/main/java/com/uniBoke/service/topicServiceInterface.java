package com.uniBoke.service;

import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.Topic;
import org.springframework.web.multipart.MultipartFile;

public interface topicServiceInterface {
//  topic to send:
    RequestMessage sendTopic(Topic topic);
//  the user upload image File
    RequestMessage uploadService(MultipartFile[] files, String userID);
}
