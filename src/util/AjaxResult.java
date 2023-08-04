package util;

/**
 * ajxa
 * @author follow
 *
 */
public class AjaxResult {
	
	private Integer code;
	
	private Object data;
	
	private String msg;

	public AjaxResult(Integer code, Object data, String msg) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
