/**
 * 
 */
package com.zzl.web.resolver;

import com.zzl.exception.AuthorizationException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

/**
 * @author Wilson
 *
 */
public class SecureArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION = "Authorization";
    
//    @Reference
//    private TokenService tokenService;
    
    private String mockAuthorizationToken;
    
    private boolean mock = false;

    /**
     * @param mock the mock to set
     */
    public void setMock(boolean mock) {
        this.mock = mock;
    }
    
    /**
     * @param mockAuthorizationToken the mockAuthorizationToken to set
     */
    public void setMockAuthorizationToken(String mockAuthorizationToken) {
        this.mockAuthorizationToken = mockAuthorizationToken;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
     */
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Secure.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
     */
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
        // Transform NativeWebRequest to HttpServletRequest
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        // get signature from HttpHeader
        String authorization = request.getHeader(AUTHORIZATION);

        if(this.mock && StringUtils.isEmpty(authorization)) {
            authorization = this.mockAuthorizationToken;
        }

        // check signature from the request
        if(StringUtils.isNotBlank(authorization) && authorization.startsWith("Bearer")) {

        	// Get the base64 encoded credentials from authentication http header
			String encodedCredential = authorization.substring("Bearer".length()).trim();

			// Decode credentials
			String credential = new String(Base64.decodeBase64(encodedCredential), Charset.forName("UTF-8"));
//            Long userId = tokenService.getUserId(credential);
            Long userId = 111L;

            if(userId != null) {

                return Long.valueOf(userId);

            } else {
            	// send error for invalid http request
            	throw new AuthorizationException("401", "Access token expired");
            }

        }

        // send error for invalid http request
        throw new AuthorizationException("401", "Access token required");
     }

}
