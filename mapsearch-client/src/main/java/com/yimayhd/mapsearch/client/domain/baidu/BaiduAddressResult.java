package com.yimayhd.mapsearch.client.domain.baidu;

import java.io.Serializable;
import java.util.List;

public class BaiduAddressResult implements Serializable {
    private static final long serialVersionUID = -2952387846601433756L;

    public BaiduLocation location;

    public String formatted_address;

    public String business;

    public BaiduAddressComponent addressComponent;

    public List<BaiduPoi> pois;

    public String sematic_description;

}