package org.ditto.lib;

import java.util.UUID;

public class Constants {
	// APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "wxd930ea5d5a258f4f";
	public final static String TEST_ARTICLE_ID="TEST_ARTICLE_ID12345678";
	public static final String MYLOGIN = "conanchen";
	public static final String TEST_USER_ID = UUID.nameUUIDFromBytes("TEST_USER_ID0000000000001".getBytes()).toString();;
    public static final String ARTICLE_TYPE_BUYANSWER_UUID = UUID.nameUUIDFromBytes("ARTICLE_TYPE_BUYANSWER_UUID000001".getBytes()).toString();
	public static final String ARTICLE_TYPE_BUYREAD_UUID = UUID.nameUUIDFromBytes("ARTICLE_TYPE_BUYREAD_UUID000001".getBytes()).toString();
	public static final String ARTICLE_TYPE_SELLREAD_UUID = UUID.nameUUIDFromBytes("ARTICLE_TYPE_SELLREAD_UUID000001".getBytes()).toString();
    public static final String DATA1 = "DATA1";
    public static final String DATA2 = "DATA2";
    public static final String DATA3 = "DATA3";
    public static final String DATA4 = "DATA4";
    public static final String DATA5 = "DATA5";
    public static final String DATA6 = "DATA6";
    public static final String DATA7 = "DATA7";
    public static final String DATA8 = "DATA8";
    public static final String DATA9 = "DATA9";

    public static class ShowMsgActivity {
		public static final String STitle = "showmsg_title";
		public static final String SMessage = "showmsg_message";
		public static final String BAThumbData = "showmsg_thumb_data";
	}

	//2045436852
	/** 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
//	public static final String APP_KEY      = "2045436852";
	public static final String APP_KEY      = "2810973698";

	/**
	 * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
	 *
	 * <p>
	 * 注：关于授权回调页对移动客户端应用来说对用户是不可见的，所以定义为何种形式都将不影响，
	 * 但是没有定义将无法使用 SDK 认证登录。
	 * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
	 * </p>
	 */
	public static final String REDIRECT_URL = "http://www.sina.com";

	/**
	 * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
	 * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
	 * 选择赋予应用的功能。
	 *
	 * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
	 * 使用权限，高级权限需要进行申请。
	 *
	 * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
	 *
	 * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
	 * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
	 */
	public static final String SCOPE =
			"email,direct_messages_read,direct_messages_write,"
					+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
					+ "follow_app_official_microblog," + "invitation_write";
}