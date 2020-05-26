package com.baoge.netty_protocol.message;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 */

import com.baoge.netty_protocol.common.MessageTypeEnum;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息实体
 */
public class Message {

    /**
     * 魔数:
     * 一个固定的数字，一般用于指定当前字节序列是当前类型的协议，
     * 比如Java生成的class文件起始就使用0xCAFEBABE作为其标识符，对于本服务，这里将其定义为0x1314
     */
    private int magicNumber;

    // 主版本号
    private byte mainVersion;

    // 次版本号
    private byte subVersion;

    // 修订版本号
    private byte modifyVersion;

    // 会话id
    private String sessionId;

    // 消息类型
    private MessageTypeEnum messageType;

    // 附加数据
    private Map<String, String> attachments = new HashMap<>();

    // 消息体
    private String body;

    public Map<String, String> getAttachments() {
        return Collections.unmodifiableMap(attachments);
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments.clear();
        if (null != attachments) {
            this.attachments.putAll(attachments);
        }
    }

    public void addAttachment(String key, String value) {
        attachments.put(key, value);
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public byte getMainVersion() {
        return mainVersion;
    }

    public void setMainVersion(byte mainVersion) {
        this.mainVersion = mainVersion;
    }

    public byte getSubVersion() {
        return subVersion;
    }

    public void setSubVersion(byte subVersion) {
        this.subVersion = subVersion;
    }

    public byte getModifyVersion() {
        return modifyVersion;
    }

    public void setModifyVersion(byte modifyVersion) {
        this.modifyVersion = modifyVersion;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public MessageTypeEnum getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
