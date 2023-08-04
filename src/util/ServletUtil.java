package util;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class ServletUtil {
	
	public static final String SESSION_USER = "SESSION_USER";

	
	private ServletUtil(){
		
	}
	
	public static void resultJson(HttpServletResponse resp, AjaxResult ajaxResult) throws IOException{
		resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSONString(ajaxResult));
	}
	
	/**
	 * request succeed
	 * @param resp
	 * @param data
	 * @throws IOException
	 */
	public static void ok(HttpServletResponse resp, Object data) throws IOException{
		resultJson(resp, new AjaxResult(1, data, ""));
	}
	
	/**
	 * 请求失败
	 * @param resp
	 * @param msg
	 * @throws IOException
	 */
	public static void fail(HttpServletResponse resp, String msg) throws IOException{
		resultJson(resp, new AjaxResult(0, null, msg));
	}
}
