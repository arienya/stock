package com.bimt.thesisquery.common.util;

/**
 * 系统变量
 * @author xufeng
 *
 */
public class Const {
	// -------------------- 缓存变量 ----------------
	public static final int BASE_USER_NUM = 150000;
	/**
	 * 人员信息缓存key的前缀
	 * 一个问题缓存key为 cache_social_userinfo_,缓存value为userModel对象，人员的其它缓存信息也可以放入该对象
	 */
	public static final String TOKEN_ = "token:";
	
	/**
	 * 删除标识
	 */
	public static final String __DELETE__ = "__delete__";
	
	/**
	 * 人员信息缓存key的前缀
	 * 一个问题缓存key为 cache_social_userinfo_,缓存value为userModel对象，人员的其它缓存信息也可以放入该对象
	 */
	public static final String CACHE_BIMT_USERINFO_ = "cache:bimt:userinfo:";
	
	/**
	 * 缓存使用，缓存前缀
	 */
	public static final String SOCIAL_ = "social:";
	
	public static final String SOCIAL_CACHE_BIMT_USERINFO_ = "social:cache:bimt:userinfo:";
	/**
	 * 一个问题缓存key的前缀，问题详情页使用
	 * 一个问题缓存key为 cache_social_question_front_view_questionId,缓存value为详情页显示的除需要分页信息外的信息
	 */
	public static final String CACHE_SOCIAL_QUESTION_FRONT_VIEW_ = "cache_social_question_front_view_";
	/**
	 * 一个论文缓存key的前缀，论文详情页使用
	 * 一个论文缓存key为 cache_social_thesis_front_view_thesisId,缓存value为详情页显示的除需要分页信息外的信息
	 */
	public static final String CACHE_SOCIAL_THESIS_FRONT_VIEW_ = "cache_social_thesis_front_view_";
	/**
	 * 一个未发表工作缓存key的前缀，未发表工作详情页使用
	 * 一个未发表工作缓存key为 cache_social_unpubthesis_front_view_unpubthesisId,缓存value为详情页显示的除需要分页信息外的信息
	 */
	public static final String CACHE_SOCIAL_UNPUBTHESIS_FRONT_VIEW_ = "cache_social_unpubthesis_front_view_";
	/**
	 * 个人主页缓存key的前缀，个人主页使用
	 * 个人主页缓存key为 cache_social_userfirst_front_view_userId,缓存value为个人主页显示的除需要分页信息外的信息
	 */
	public static final String CACHE_SOCIAL_USERFIRST_FRONT_VIEW_ = "cache_social_userfirst_front_view_";
	/**
	 * 个人首页缓存key的前缀，个人首页使用
	 * 个人首页缓存key为 cache_social_usermain_front_view_userId,缓存value为个人首页显示的除需要分页信息外的信息
	 */
	public static final String CACHE_SOCIAL_USERMAIN_FRONT_VIEW_ = "cache_social_usermain_front_view_";
	/**
	 * 讨论组缓存key的前缀，个人首页使用
	 * 讨论组缓存key为 cache_social_group_front_view_userId,缓存value为讨论组详细页显示的除需要分页信息外的信息
	 */
	public static final String CACHE_SOCIAL_GROUP_FRONT_VIEW_ = "cache_social_group_front_view_";
	
	/**
	 * 好友排名，个人首页使用
	 * 讨论组缓存key为 cache_social_friend_ranking_userId,缓存value为该用户在好友中影响值排名
	 */
	public static final String CACHE_SOCIAL_FRIEND_RANKING_ = "cache_social_friend_ranking_";
	/**
	 * 个人消息数量
	 * 讨论组缓存key为 cache_user_message_num_{userid},缓存value为该用户个人消息数量
	 */
	public static final String CACHE_USER_MESSAGE_NUM_ = "cache_user_message_num_";
	
	/**
	 * 注册验证码缓存前缀
	 */
	public static final String CACHE_REGISTER_VALIDCODE_ = "cache_register_validcode_";
	/**
	 * 个人动态缓存，存储每个人的动态信息，存储前10000条
	 */
	public static final String CACHE_SOCIAL_DYNAMIC_ = "cache:social:dynamic:";

    public static final int FOLLOW_TYPE_USER = 1;
    public static final int FOLLOW_TYPE_THESIS = 2;
    public static final int FOLLOW_TYPE_QUESTION = 3;
    
    /** 邮箱host */
    public static final String MAIL_HOST = PropertiesUtils.getInstance().getProperty("mail.smtp.host");
    /** 发件邮箱 */
    public static final String MAIL_FROM = PropertiesUtils.getInstance().getProperty("mail.from");
    /** 邮箱用户名 */
    public static final String MAIL_USER = PropertiesUtils.getInstance().getProperty("mail.user");
    /** 邮箱密码 */
    public static final String MAIL_PWD = PropertiesUtils.getInstance().getProperty("mail.pwd");
    /** 邮箱密码 */
    public static final String MAIL_REGISTER_SUBJECT = PropertiesUtils.getInstance().getProperty("mail.register.subject");
    /** 大数据组api地址 */
    public static final String BIGDATA_URI = PropertiesUtils.getInstance().getProperty("bigdata_uri");
    /** 会议详情页面地址 */
    public static final String CONFERENCE_URI = PropertiesUtils.getInstance().getProperty("conference_uri");
    
    /** 消息请求类型 */
    public static final int MESSAGE_REQ_TYPE_1 = 1;	// 请求论文
    public static final int MESSAGE_REQ_TYPE_2 = 2;	// 请求加入讨论组
    public static final int MESSAGE_REQ_TYPE_3 = 3;	// 邀请加入讨论组
    
    /** 删除类型 */
    public static final int DELETE_FLAG_0 = 0;	// 已删除
    public static final int DELETE_FLAG_1 = 1;	// 未删除
    
    /** 已读未读 */
    public static final int IS_SHOW_0 = 0;	// 未读
    public static final int IS_SHOW_1 = 1;	// 已读
    
    /** 是否好友 */
    public static final int IS_Friend_0 = 0;	// 不是好友
    public static final int IS_Friend_1 = 1;	// 是 好友
    
    /** 是否本人 */
    public static final int IS_OWNER_0 = 0;	// // 0不是本人
    public static final int IS_OWNER_1 = 1;	// 1是本人
    
    /** 是否关注, 0未关注1已关注 */
    public static final int IS_FOLLOW_0 = 0;	// // 0未关注
    public static final int IS_FOLLOW_1 = 1;	// 1已关注
    
    /** 附件类型 */
    public static final int THESIS_ATTACHMENT_1 = 1;	// 正文
    public static final int THESIS_ATTACHMENT_2 = 2;	// 补充数据
    
    /**  论文附件权限类型，1自己可见2指定用户可见3仅相互关注好友可见4关注我的可见     */
    public static final int THESIS_ATTACHMENT_AUTHORITY_1 = 1;	// 自己可见
    public static final int THESIS_ATTACHMENT_AUTHORITY_2 = 2;	// 指定用户可见
    public static final int THESIS_ATTACHMENT_AUTHORITY_3 = 3;	// 仅相互关注好友可见
    public static final int THESIS_ATTACHMENT_AUTHORITY_4 = 4;	// 关注我的可见
    
    /**
     * 动态类型   
     * 11认领论文12关注论文13评论论文15作者上传了全文16作者上传了补充数据21新问题22关注问题23回答问题31报名了会议
     * 41新建讨论组42加入讨论组43有新成员44回复51上传了未发表工作    
     */
    public static final int DYNAMIC_TYPE_11 = 11;	// 认领论文
    public static final int DYNAMIC_TYPE_12 = 12;	// 关注论文
    public static final int DYNAMIC_TYPE_13 = 13;	// 评论论文
    public static final int DYNAMIC_TYPE_15 = 15;	// 作者上传了全文
    public static final int DYNAMIC_TYPE_16 = 16;	// 作者上传了补充数据
    public static final int DYNAMIC_TYPE_21 = 21;	// 新问题
    public static final int DYNAMIC_TYPE_22 = 22;	// 关注问题
    public static final int DYNAMIC_TYPE_23 = 23;	// 回答问题
    public static final int DYNAMIC_TYPE_31 = 31;	// 报名了会议
    public static final int DYNAMIC_TYPE_41 = 41;	// 新建讨论组
    public static final int DYNAMIC_TYPE_42 = 42;	// 加入讨论组
    public static final int DYNAMIC_TYPE_43 = 43;	// 讨论组有新成员
    public static final int DYNAMIC_TYPE_44 = 44;	// 讨论组回复
    public static final int DYNAMIC_TYPE_51 = 51;	// 上传了未发表工作   
    public static final int DYNAMIC_TYPE_52 = 52;	// 评论未发表工作   
    
    /**  关注类型,1关注人2关注论文3关注问题4未发表工作     */
    public static final int FOLLOW_TYPE_1 = 1;	// 关注人
    public static final int FOLLOW_TYPE_2 = 2;	// 关注论文
    public static final int FOLLOW_TYPE_3 = 3;	// 关注问题
    public static final int FOLLOW_TYPE_4 = 4;	// 未发表工作
    
    /**  申请讨论组状态,是否通过,0未操作1通过2未通过     */
    public static final int GROUP_PASSED_0 = 0;	// 未操作
    public static final int GROUP_PASSED_1 = 1;	// 通过
    public static final int GROUP_PASSED_2 = 2;	// 未通过
    
    /**  消息提醒类型,1用户相关2论文相关3提问相关     */
    public static final int MESSAGE_REMIND_TYPE_1 = 1;	// 用户相关
    public static final int MESSAGE_REMIND_TYPE_2 = 2;	// 论文相关
    public static final int MESSAGE_REMIND_TYPE_3 = 3;	// 提问相关  
    public static final int MESSAGE_REMIND_TYPE_4 = 4;	// 讨论组相关  
    
    /**  来源类型,1由关注用户得到2由关注论文得到3由关注提问得到     */
    public static final int MESSAGE_REMIND_FROM_TYPE_1 = 1;	// 由关注用户得到
    public static final int MESSAGE_REMIND_FROM_TYPE_2 = 2;	// 由关注论文得到
    public static final int MESSAGE_REMIND_FROM_TYPE_3 = 3;	// 由关注提问得到 
    public static final int MESSAGE_REMIND_FROM_TYPE_4 = 4;	// 由讨论组得到 
    
    /**  讨论组人员角色,组角色,1创始人2管理人3组员     */
    public static final int GROUP_ROLE_TYPE_1 = 1;	// 创始人
    public static final int GROUP_ROLE_TYPE_2 = 2;	// 管理人
    public static final int GROUP_ROLE_TYPE_3 = 3;	// 组员
    
    /** 隐私，1所有人可见2组内人可见	*/
    public static final int GROUP_PRIVACY_1 = 1;	// 所有人可见
    public static final int GROUP_PRIVACY_2 = 2;	// 组内人可见
    
    /**  动态产生原因,1关注人的动态2关注论文的动态3关注问题的动态4加入的讨论组的动态5我未发表工作的动态     */
    public static final int DYNAMIC_REASON_1 = 1;	// 由关注用户得到
    public static final int DYNAMIC_REASON_2 = 2;	// 关注论文的动态
    public static final int DYNAMIC_REASON_3 = 3;	// 关注问题的动态
    public static final int DYNAMIC_REASON_4 = 4;	// 加入的讨论组的动态
    public static final int DYNAMIC_REASON_5 = 5;	// 我未发表工作的动态  
    
    public static final String SOCIAL_SERVICE_URL = PropertiesUtils.getInstance().getProperty("social-service-url");
}
