package com.hzdjr.hzwd.modules.buss.entity;

import org.hibernate.validator.constraints.Length;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 短信模板Entity
 * @author xuchenglin
 * @version 2017-03-23
 */
public class MessageModules extends DataEntity<MessageModules> {
	
	private static final long serialVersionUID = 1L;
	private String receiver;		// 接收人
	private String messageType;		// 类型
	private String messageContent;		// 短信内容
	private String messageStatus;		// 状态(0未发布 1发布)
	
	public MessageModules() {
		super();
	}

	public MessageModules(String id){
		super(id);
	}

	@Length(min=1, max=128, message="接收人长度必须介于 1 和 128 之间")
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	@Length(min=1, max=128, message="类型长度必须介于 1 和 128 之间")
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	@Length(min=1, max=1, message="状态(0未发布 1发布)长度必须介于 1 和 1 之间")
	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	
}