package com.cnhutong.cs.mobile.web.request;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * http://js.jsnlog.com/Documentation/JSNLogJs
 * 
 * @author wilson
 *
 */
public class JsnLog {

	@JSONField(name = "r")
	private String requestId;

	@JSONField(name = "lg")
	private List<Log> logs;

	public JsnLog() {
		
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public static class Log {

		@JSONField(name = "l")
		private String level;

		@JSONField(name = "m")
		private String message;

		@JSONField(name = "n")
		private String name;

		@JSONField(name = "t")
		private Long timestamp;

		@JSONField(name = "u")
		private String eventId;

		public boolean isErrorLevel() {
			return "ERROR".equals(level) || "FATAL".equals(level) || StringUtils.isInteger(level) && Integer.valueOf(level) > 4000;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}

		public String getEventId() {
			return eventId;
		}

		public void setEventId(String eventId) {
			this.eventId = eventId;
		}
		
	}

}
