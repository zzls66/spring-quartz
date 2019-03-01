//package com.cnhutong.cs.mobile.web.interceptor;
//
//import com.cnhutong.business.entity.LoggingRequestData;
//import com.cnhutong.staff.enums.system.Platform;
//import com.demo2do.core.utils.JsonUtils;
//import com.demo2do.core.utils.StringUtils;
//import org.slf4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import static java.util.Arrays.asList;
//import static java.util.Collections.emptySet;
//import static org.apache.commons.lang3.StringUtils.isNotBlank;
//import static org.slf4j.LoggerFactory.getLogger;
//
//public class LoggingFilter implements Filter {
//
//
//	private Logger log = getLogger(getClass());
//
//	private Set<String> excludedPaths = emptySet();
//
//	private String requestPrefix = "REQUEST: ";
//
//	public LoggingFilter() {
//
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//
//		String excludedPaths = filterConfig.getInitParameter("excludedPaths");
//		if (isNotBlank(excludedPaths)) {
//			String[] paths = excludedPaths.split("\\s*,\\s*");
//			this.excludedPaths = new HashSet<>(asList(paths));
//		}
//
//    }
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//
//		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
//			throw new ServletException("LoggingFilter just supports HTTP requests");
//		}
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//		// exclude match path
//		for (String excludedPath : excludedPaths) {
//			String requestURI = httpRequest.getRequestURI();
//			if (requestURI.startsWith(excludedPath)) {
//				filterChain.doFilter(httpRequest, httpResponse);
//				return;
//			}
//		}
//
//		// transfer request
//		LoggingHttpServletRequestWrapper requestWrapper = new LoggingHttpServletRequestWrapper(httpRequest);
//
//		LoggingRequestData loggingRequest = getLoggingRequest(requestWrapper);
//		if(log.isDebugEnabled()) {
//			log.debug(requestPrefix + JsonUtils.toJsonString(loggingRequest));
//		}
//
//		filterChain.doFilter(requestWrapper, httpResponse);
//	}
//
//	@Override
//	public void destroy() {
//	}
//
//	protected LoggingRequestData getLoggingRequest(LoggingHttpServletRequestWrapper requestWrapper) {
//
//		Long userId = null;
//		String sender = requestWrapper.getLocalAddr();
//		String method = requestWrapper.getMethod();
//		String path = requestWrapper.getRequestURI();
//		String postParam = requestWrapper.isFormPost() ? StringUtils.toQueryString(requestWrapper.getParameters()) : null;
//		String getParam = (requestWrapper.getQueryString() == null ? "" : requestWrapper.getQueryString());
//		String headerJson = JsonUtils.toJsonString(requestWrapper.getHeaders());
//		String bodyJson = requestWrapper.isJsonPost() ? requestWrapper.getContent() : null;
////        String authorization = requestWrapper.getHeader("Authorization");
////        if(org.apache.commons.lang3.StringUtils.isNotBlank(authorization) && authorization.startsWith("Bearer")) {
////			String encodedCredential = authorization.substring("Bearer".length()).trim();
////			String credential = new String(Base64.decodeBase64(encodedCredential), Charset.forName("UTF-8"));
//		userId = 0L;
////        }
//		Date time = new Date();
//		LoggingRequestData loggingRequest = new LoggingRequestData(userId, sender, method, path, postParam, getParam, headerJson, bodyJson, time, Platform.WECHAT_QY_CC_CONTRACT);
//		return loggingRequest;
//	}
//
//}
