//package com.zzl.web.interceptor;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.servlet.ReadListener;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
//public class LoggingHttpServletRequestWrapper extends HttpServletRequestWrapper {
//
//	private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
//	private static final String JSON_CONTENT_TYPE = "application/json";
//
//	private static final String METHOD_POST = "POST";
//
//	private byte[] content;
//
//	private final Map<String, String[]> parameterMap;
//
//	private final HttpServletRequest delegate;
//
//	public LoggingHttpServletRequestWrapper(HttpServletRequest request) {
//		super(request);
//		this.delegate = request;
//		if (isFormPost()) {
//			this.parameterMap = request.getParameterMap();
//		} else {
//			this.parameterMap = Collections.emptyMap();
//		}
//		getContent();
//	}
//
//	@Override
//	public ServletInputStream getInputStream() throws IOException {
//		if (ArrayUtils.isEmpty(content)) {
//			return delegate.getInputStream();
//		}
//		return new LoggingServletInputStream(content);
//	}
//
//	@Override
//	public BufferedReader getReader() throws IOException {
//		if (ArrayUtils.isEmpty(content)) {
//			return delegate.getReader();
//		}
//		return new BufferedReader(new InputStreamReader(getInputStream()));
//	}
//
//	@Override
//	public String getParameter(String name) {
//		if (ArrayUtils.isEmpty(content) || this.parameterMap.isEmpty()) {
//			return super.getParameter(name);
//		}
//		String[] values = this.parameterMap.get(name);
//		if (values != null && values.length > 0) {
//			return values[0];
//		}
//		return values == null ? null : Arrays.toString(values);
//	}
//
//	@Override
//	public Map<String, String[]> getParameterMap() {
//		if (ArrayUtils.isEmpty(content) || this.parameterMap.isEmpty()) {
//			return super.getParameterMap();
//		}
//		return this.parameterMap;
//	}
//
//	@Override
//	public Enumeration<String> getParameterNames() {
//		if (ArrayUtils.isEmpty(content) || this.parameterMap.isEmpty()) {
//			return super.getParameterNames();
//		}
//		return new ParamNameEnumeration(this.parameterMap.keySet());
//	}
//
//	@Override
//	public String[] getParameterValues(String name) {
//		if (ArrayUtils.isEmpty(content) || this.parameterMap.isEmpty()) {
//			return super.getParameterValues(name);
//		}
//		return this.parameterMap.get(name);
//	}
//
//	public String getContent() {
//		try {
//			if (ArrayUtils.isEmpty(content)) {
//				if (this.parameterMap.isEmpty()) {
//					content = IOUtils.toByteArray(delegate.getInputStream());
//				} else {
//					content = getContentFromParameterMap(this.parameterMap);
//				}
//			}
//			String requestEncoding = delegate.getCharacterEncoding();
//			String normalizedContent = StringUtils.normalizeSpace(new String(content, requestEncoding != null ? requestEncoding : StandardCharsets.UTF_8.name()));
//			return StringUtils.isBlank(normalizedContent) ? "[EMPTY]" : normalizedContent;
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new IllegalStateException();
//		}
//	}
//
//	private byte[] getContentFromParameterMap(Map<String, String[]> parameterMap) {
//		List<String> result = new ArrayList<String>();
//		for(Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//			String[] value = entry.getValue();
//			result.add(entry.getKey() + "=" + (value.length == 1 ? value[0] : Arrays.toString(value)));
//		}
//		return StringUtils.join(result, "&").getBytes();
//	}
//
//	public Map<String, String> getHeaders() {
//		Map<String, String> headers = new HashMap<>(0);
//		Enumeration<String> headerNames = getHeaderNames();
//		while (headerNames.hasMoreElements()) {
//			String headerName = headerNames.nextElement();
//			if (headerName != null) {
//				headers.put(headerName, getHeader(headerName));
//			}
//		}
//		return headers;
//	}
//
//	public Map<String, String> getParameters() {
//		Map<String, String> result = new HashMap<String, String>();
//		for(Map.Entry<String, String[]> entry : getParameterMap().entrySet()) {
//			String[] values = entry.getValue();
//			result.put(entry.getKey(), values.length > 0 ? values[0] : "[EMPTY]");
//		}
//
//		return result;
//	}
//
//	public boolean isFormPost() {
//		String contentType = getContentType();
//		return contentType != null && contentType.contains(FORM_CONTENT_TYPE) && METHOD_POST.equalsIgnoreCase(getMethod());
//	}
//
//	public boolean isJsonPost() {
//		String contentType = getContentType();
//		return contentType != null && contentType.contains(JSON_CONTENT_TYPE) && METHOD_POST.equalsIgnoreCase(getMethod());
//	}
//
//	private class ParamNameEnumeration implements Enumeration<String> {
//
//		private final Iterator<String> iterator;
//
//		@SuppressWarnings("unchecked")
//		private ParamNameEnumeration(Set<String> values) {
//			this.iterator = (Iterator<String>) (values != null ? values.iterator() : Collections.emptyIterator());
//		}
//
//		@Override
//		public boolean hasMoreElements() {
//			return iterator.hasNext();
//		}
//
//		@Override
//		public String nextElement() {
//			return iterator.next();
//		}
//	}
//
//	private class LoggingServletInputStream extends ServletInputStream {
//
//		private final InputStream is;
//
//		private LoggingServletInputStream(byte[] content) {
//			this.is = new ByteArrayInputStream(content);
//		}
//
//		@Override
//		public boolean isFinished() {
//			return true;
//		}
//
//		@Override
//		public boolean isReady() {
//			return true;
//		}
//
//		@Override
//		public void setReadListener(ReadListener readListener) {
//		}
//
//		@Override
//		public int read() throws IOException {
//			return this.is.read();
//		}
//
//		@Override
//		public void close() throws IOException {
//			super.close();
//			is.close();
//		}
//	}
//}
