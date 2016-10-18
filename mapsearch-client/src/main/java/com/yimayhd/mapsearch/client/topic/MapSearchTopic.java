package com.yimayhd.mapsearch.client.topic;

/**
 * Created by luyichen on 14-7-31.
 *
 */
public enum MapSearchTopic {

    CREATE(MapSearchTopic.TOPIC,"CREATE","创建"),
	CARLOC(MapSearchTopic.TOPIC,"CARLOC","车实时位置"), 
	CARTRACE(MapSearchTopic.TOPIC,"CARTRACE","车行车轨迹");

    public static final String TOPIC = "MAPSEARCH";
    
    /**
     * 主题
     */
    private String topic;

    /**
     * 标签
     */
    private String tags;

    /**
     * 描述
     */
    private String desc;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private MapSearchTopic(String topic, String tags, String desc) {
        this.topic = topic;
        this.tags = tags;
        this.desc = desc;
    }

}
