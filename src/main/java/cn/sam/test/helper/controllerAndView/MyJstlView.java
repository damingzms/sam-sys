package cn.sam.test.helper.controllerAndView;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.JstlView;

public class MyJstlView extends JstlView {
	
	public static final String CONTEXT_PATH = "ctx";
	
	public static final String HOST = "host";

	@Override
	protected void exposeHelpers(HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String localAddr = request.getLocalAddr();
		int localPort = request.getLocalPort();
		request.setAttribute(CONTEXT_PATH, path);
		request.setAttribute(HOST, localAddr + ":" + localPort);
		super.exposeHelpers(request);
	}
	
}
