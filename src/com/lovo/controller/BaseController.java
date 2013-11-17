package com.lovo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class BaseController {
	private static final Logger LOGGER = Logger.getLogger(BaseController.class);

	/**执行错误时返回的结果*/
	protected static String ERROR = "{\"result\":\"0\"" + "}";
	/**执行成功时返回的结果*/
	protected static String SUCCESS = "{\"result\":\"1\"" + "}";

	public void senderText(HttpServletResponse response, String result) {
		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.print(result);
		} catch (IOException e) {
			LOGGER.error("Response获取PrintWriter流失败。", e);
		} finally {
			if (out != null)
				out.close();
		}
	}

}
