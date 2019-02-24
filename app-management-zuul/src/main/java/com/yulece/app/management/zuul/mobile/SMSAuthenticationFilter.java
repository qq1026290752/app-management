package com.yulece.app.management.zuul.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

/**
 * 短信验证码过滤器
 * 
 * @author w4837
 *
 */
public class SMSAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
	// =====================================================================================

	public static final String PROJECT_MOBILE_KEY = "mobile";

	private String mobileParameter = PROJECT_MOBILE_KEY;
	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================
	/**
	 * 设置处理请求路径
	 */
	public SMSAuthenticationFilter() {
		super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
	}

	// ~ Methods
	// ========================================================================================================

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);

		if (mobile == null) {
			mobile = "";
		}
		mobile = mobile.trim();

		SMSAuthenticationToken authRequest = new SMSAuthenticationToken(mobile);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * Provided so that subclasses may configure what is put into the authentication
	 * request's details property.
	 *
	 * @param request
	 *            that an authentication request is being created for
	 * @param authRequest
	 *            the authentication request object that should have its details set
	 */
	protected void setDetails(HttpServletRequest request, SMSAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	/**
	 * Enables subclasses to override the composition of the Mobile, such as by
	 * including additional values and a separator.
	 *
	 * @param request
	 *            so that request attributes can be retrieved
	 *
	 * @return the Mobile that will be presented in the <code>Authentication</code>
	 *         request token to the <code>AuthenticationManager</code>
	 */
	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	/**
	 * 获取手机号
	 */
	public void setmobileParameter(String mobileParameter) {
		Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
		this.mobileParameter = mobileParameter;
	}

	/**
	 * Defines whether only HTTP POST requests will be allowed by this filter. If
	 * set to true, and an authentication request is received which is not a POST
	 * request, an exception will be raised immediately and authentication will not
	 * be attempted. The <tt>unsuccessfulAuthentication()</tt> method will be called
	 * as if handling a failed authentication.
	 * <p>
	 * Defaults to <tt>true</tt> but may be overridden by subclasses.
	 */
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getmobileParameter() {
		return mobileParameter;
	}
}
