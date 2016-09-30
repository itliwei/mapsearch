package com.yimayhd.mapsearch.constant;

/**
 * Created by menhaihao on 15/5/22.
 *
 */
public class TairConstant {
    //主贴评论数tair的key的前缀
    public static final String SUBJECT_COMMENT_NUM_PRE = "SNS_SUB_COM_NUM_";
    //主贴点赞数tair的key的前缀
    public static final String SUBJECT_PRAISE_NUM_PRE = "SNS_SUB_PRA_NUM_";
    //评论点赞数tair的key的前缀
    public static final String COMMENT_PRAISE_NUM_PRE = "SNS_COM_PRA_NUM_";
    //主贴数tair的key的前缀
    public static final String SUBJECT_NUM_PRE = "SNS_SUB_NUM_";
    //各种数量tair的key
    public static final String SUBJECT_NUM_RESULT = "SNS_SUB_NUM_RESULT";
    //热门列表缓存tair的key
    public static final String HOT_SUBJECTS = "HOT_SUBJECTS";
    //判断是不是热门的阀值 缓存tair的key
    public static final String HOT_THRESHOLD = "HOT_THRESHOLD";
    //置顶 缓存tair的key
    public static final String TOP_SUBJECT = "TOP_SUBJECT";
    //首页3条配置的健康圈 缓存tair的key
    public static final String HOME_PAGE_SUBJECT = "HOME_PAGE_SUBJECT";
    //话题关联所有的数量 缓存tair的key
    public static final String TOPIC_SB_COUNT_PRE = "TOPIC_SB_COUNT_";
    //俱乐部动态数量key
    public static final String SUBJECT_NUM_DYNAMIC = "SUBJECT_NUM_DYNAMIC";
    
    //俱乐部动态点赞数量key
    public static final String SUBJECT_NUM_DYNAMIC_SUPPORT = "SUBJECT_NUM_DYNAMIC_SUPPORT";
    //俱乐部动态评论数量key
    public static final String SUBJECT_NUM_DYNAMIC_COMMENT = "SUBJECT_NUM_DYNAMIC_COMMENT";
    
    //直播数量key
    public static final String SUBJECT_NUM_LIVE = "SUBJECT_NUM_LIVE";
    //直播评论数量key
    public static final String SUBJECT_NUM_LIVE_COMMENT = "SUBJECT_NUM_LIVE_COMMENT";
    //直播点赞数量key
    public static final String SUBJECT_NUM_LIVE_SUPPORT = "SUBJECT_NUM_LIVE_SUPPORT";
  	
    public static String getSubjectDynamicCommentTairKey(long subjectId) {
        return SUBJECT_NUM_DYNAMIC_COMMENT + subjectId;
    }
    
    public static String getSubjectDynamicSupportTairKey(long subjectId) {
        return SUBJECT_NUM_DYNAMIC_SUPPORT + subjectId;
    }
    
    public static String getSubjectLiveSupportTairKey(long subjectId) {
        return SUBJECT_NUM_LIVE_SUPPORT + subjectId;
    }
    
    public static String getSubjectLiveCommentTairKey(long subjectId) {
        return SUBJECT_NUM_LIVE_COMMENT + subjectId;
    }
    
    public static String getSubjectLiveNumTairKey(long subjectId) {
        return SUBJECT_NUM_LIVE + subjectId;
    }
    
    public static String getSubjectCommentNumTairKey(long subjectId) {
        return SUBJECT_COMMENT_NUM_PRE + subjectId;
    }
    public static String getSubjectNumTairKey(long communityId) {
        return SUBJECT_NUM_PRE + communityId;
    }

    public static String getSubjectPraiseNumTairKey(long subjectId) {
        return SUBJECT_PRAISE_NUM_PRE + subjectId;
    }
    
    public static String getSubjectDynamicNumTairKey(long subjectId) {
        return SUBJECT_NUM_DYNAMIC + subjectId;
    }

    public static String getCommentPraiseNumTairKey(long commentId) {
        return COMMENT_PRAISE_NUM_PRE + commentId;
    }

    public static String getTopicNumTairKey(long topic) {
        return TOPIC_SB_COUNT_PRE + topic;
    }
}
