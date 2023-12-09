package top.flobby.train.business.req;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppManageSaveReq {

    /**
    * 主键
    */
    private Long id;

    /**
    * 版本号
    */
    private String appVersion;

    /**
    * APP名
    */
    private String appName;

    /**
    * 平台
    */
    private String platform;

    /**
    * 下载地址
    */
    private String downloadUrl;

    /**
    * 描述
    */
    private String description;

    /**
    * 发行时间
    */
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", appVersion=").append(appVersion);
            sb.append(", appName=").append(appName);
            sb.append(", platform=").append(platform);
            sb.append(", downloadUrl=").append(downloadUrl);
            sb.append(", description=").append(description);
            sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}