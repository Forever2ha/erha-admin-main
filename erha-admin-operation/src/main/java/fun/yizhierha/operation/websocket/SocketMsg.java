package fun.yizhierha.operation.websocket;

import lombok.Data;


/**
 *
 * @author wxf
 * @date 2023年1月6日 16:42:57
 */
@Data
public class SocketMsg {
	private String msg;
	private MsgType msgType;

	public SocketMsg(String msg, MsgType msgType) {
		this.msg = msg;
		this.msgType = msgType;
	}
}
