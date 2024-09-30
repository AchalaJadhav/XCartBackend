package com.innovation.xcartbackend.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * This class extends AuthenticationEntryPoint class of Spring Security and rejects every unauthenticated request with an error code 401 sent back to the client. We have overridden the commence() method of AuthenticationEntryPoint class to do that.
 * @author Ajinkya.Deshpande
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * @param  request that resulted in an AuthenticationException
	 * @param  response so that the user agent can begin authentication
	 * @param  authException that caused the invocation
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied !!");
		
	}

}
