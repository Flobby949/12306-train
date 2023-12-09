package top.flobby.train.business.req;

import lombok.Data;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-12-01 14:15
 **/

@Data
public class AppVersionCheckReq {
    public String appVersion;
    public String appName;
    public String platform;
}
