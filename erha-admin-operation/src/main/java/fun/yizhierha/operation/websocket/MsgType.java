package fun.yizhierha.operation.websocket;

/**
 * @author xaopohi
 * @date 2023-01-06
 */
public enum MsgType {
	/** 连接 */
	CONNECT,
	/** 关闭 */
	CLOSE,
	/** 信息 */
	INFO,
	/** 错误 */
	ERROR
}
