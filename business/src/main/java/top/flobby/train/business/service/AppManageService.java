package top.flobby.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.flobby.train.business.domain.AppManage;
import top.flobby.train.business.domain.AppManageExample;
import top.flobby.train.business.mapper.AppManageMapper;
import top.flobby.train.business.req.AppManageQueryReq;
import top.flobby.train.business.req.AppManageSaveReq;
import top.flobby.train.business.req.AppVersionCheckReq;
import top.flobby.train.business.resp.AppManageQueryResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.common.utils.SnowUtil;

import java.util.List;

/**
 * @author Flobby
 */

@Service
public class AppManageService {

    private static final Logger LOG = LoggerFactory.getLogger(AppManageService.class);

    @Resource
    private AppManageMapper appManageMapper;

    public void save(AppManageSaveReq req) {
        DateTime now = DateTime.now();
        AppManage appManage = BeanUtil.copyProperties(req, AppManage.class);
        if (ObjectUtil.isNull(appManage.getId())) {
            appManage.setId(SnowUtil.getSnowflakeNextId());
            appManage.setCreateTime(now);
            appManageMapper.insert(appManage);
        } else {
            appManageMapper.updateByPrimaryKey(appManage);
        }
    }

    public PageResp<AppManageQueryResp> queryList(AppManageQueryReq req) {
        AppManageExample appManageExample = new AppManageExample();
        appManageExample.setOrderByClause("id desc");
        AppManageExample.Criteria criteria = appManageExample.createCriteria();

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<AppManage> appManageList = appManageMapper.selectByExample(appManageExample);

        PageInfo<AppManage> pageInfo = new PageInfo<>(appManageList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<AppManageQueryResp> list = BeanUtil.copyToList(appManageList, AppManageQueryResp.class);

        PageResp<AppManageQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        appManageMapper.deleteByPrimaryKey(id);
    }

    public AppManageQueryResp checkVersion(AppVersionCheckReq req) {
        AppManageExample appManageExample = new AppManageExample();
        // 时间倒序
        appManageExample.setOrderByClause("create_time desc");
        AppManageExample.Criteria criteria = appManageExample.createCriteria();
        criteria.andAppNameEqualTo(req.getAppName());
        criteria.andPlatformEqualTo(req.getPlatform());
        List<AppManage> appManageList = appManageMapper.selectByExample(appManageExample);
        // 判断最新版本是否大于当前版本
        AppManage latestVersion = appManageList.get(0);
        if (versionCompare(req.getAppVersion(), latestVersion.getAppVersion())) {
            return BeanUtil.copyProperties(latestVersion, AppManageQueryResp.class);
        } else {
            return null;
        }
    }

    /**
     * 版本比较
     *
     * @param version1 手机端版本
     * @param version2 后台版本
     * @return boolean
     */
    private boolean versionCompare(String version1, String version2) {
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        for (int i = 0; i < version1Array.length; i++) {
            if (Integer.parseInt(version1Array[i]) < Integer.parseInt(version2Array[i])) {
                return true;
            } else if (Integer.parseInt(version1Array[i]) > Integer.parseInt(version2Array[i])) {
                return false;
            }
        }
        // 版本号相等
        return false;
    }

    public AppManageQueryResp query(Long id) {
        AppManage appManage = appManageMapper.selectByPrimaryKey(id);
        return BeanUtil.copyProperties(appManage, AppManageQueryResp.class);
    }
}