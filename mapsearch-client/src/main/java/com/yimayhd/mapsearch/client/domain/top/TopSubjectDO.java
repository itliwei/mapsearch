package com.yimayhd.mapsearch.client.domain.top;

import java.io.Serializable;

/**
 * Created by menhaihao on 15/7/14.
 *
 */
public class TopSubjectDO implements Serializable {
    private static final long serialVersionUID = 5587568651459646542L;

    private long subjectId;

    private String content;

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
