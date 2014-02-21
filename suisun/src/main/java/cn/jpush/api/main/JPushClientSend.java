package cn.jpush.api.main;

import cn.jpush.api.push.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.DeviceEnum;
import cn.jpush.api.report.ReceivedsResult;

import java.util.HashMap;
import java.util.Map;

public class JPushClientSend {
	protected static final Logger LOG = LoggerFactory
			.getLogger(JPushClientSend.class);

	// demo App defined in resources/jpush-api.conf
	private static final String appKey = "54d476f97e829b27630b3842";
	private static final String masterSecret = "16f8cb3057394b850fccf085";

	public static final String msgTitle = "Test from API example";
	public static final String msgContent = "Test Test";
	public static final String registrationID = "0900e8d85ef";

	// 这个就是tag标签
	public static final String tag_api = "tag_api";

	public static void main(String[] args) {
		send(tag_api);
		getReport();
	}

	public static boolean push(String tag) {
		return send(tag) && getReport();
	}

	private static boolean send(String tag) {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);

		// IOS设置通知铃声和badge
		Map<String, Object> extra = new HashMap<String, Object>();
		IosExtras iosExtras = new IosExtras(1, "Windows_Logon_Sound.wav");// badge
																			// and
																			// sound
		extra.put("ios", iosExtras);

		NotificationParams p = new NotificationParams();
		p.setReceiverType(ReceiverTypeEnum.TAG);
		p.setReceiverValue(tag);

		p.setAndroidNotificationTitle(msgContent);
		p.setAndroidBuilderId(2);

		MessageResult msgResult = jpushClient.sendNotification(msgTitle, p,
				extra);
		LOG.debug("responseContent - "
				+ msgResult.responseResult.responseContent);
		if (msgResult.isResultOK()) {
			LOG.info("msgResult - " + msgResult);
			LOG.info("messageId - " + msgResult.getMessageId());
			return true;
		} else {
			if (msgResult.getErrorCode() > 0) {
				// 业务异常
				LOG.warn("Service error - ErrorCode: "
						+ msgResult.getErrorCode() + ", ErrorMessage: "
						+ msgResult.getErrorMessage());
			} else {
				// 未到达 JPush
				LOG.error("Other excepitons - "
						+ msgResult.responseResult.exceptionString);
			}
			return false;
		}
	}

	public static boolean getReport() {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		ReceivedsResult receivedsResult = jpushClient
				.getReportReceiveds("1708010723,1774452771");
		LOG.debug("responseContent - "
				+ receivedsResult.responseResult.responseContent);
		if (receivedsResult.isResultOK()) {
			LOG.info("Receiveds - " + receivedsResult);
			return true;
		} else {
			if (receivedsResult.getErrorCode() > 0) {
				// 业务异常
				LOG.warn("Service error - ErrorCode: "
						+ receivedsResult.getErrorCode() + ", ErrorMessage: "
						+ receivedsResult.getErrorMessage());
			} else {
				// 未到达 JPush
				LOG.error("Other excepitons - "
						+ receivedsResult.responseResult.exceptionString);
			}
			return false;
		}
	}

}
