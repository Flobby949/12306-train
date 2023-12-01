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
import top.flobby.train.business.domain.Student;
import top.flobby.train.business.domain.StudentExample;
import top.flobby.train.business.mapper.StudentMapper;
import top.flobby.train.business.req.StudentQueryReq;
import top.flobby.train.business.req.StudentSaveReq;
import top.flobby.train.business.resp.StudentQueryResp;
import top.flobby.train.common.resp.PageResp;
import top.flobby.train.common.utils.SnowUtil;

import java.util.List;

/**
 * @author Flobby
 */

@Service
public class StudentService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    @Resource
    private StudentMapper studentMapper;

    public void save(StudentSaveReq req) {
        DateTime now = DateTime.now();
        Student student = BeanUtil.copyProperties(req, Student.class);
        if (ObjectUtil.isNull(student.getId())) {
            student.setId(SnowUtil.getSnowflakeNextId());
            studentMapper.insert(student);
        } else {
            studentMapper.updateByPrimaryKey(student);
        }
    }

    public PageResp<StudentQueryResp> queryList(StudentQueryReq req) {
        StudentExample studentExample = new StudentExample();
        studentExample.setOrderByClause("id desc");
        StudentExample.Criteria criteria = studentExample.createCriteria();

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Student> studentList = studentMapper.selectByExample(studentExample);

        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<StudentQueryResp> list = BeanUtil.copyToList(studentList, StudentQueryResp.class);

        PageResp<StudentQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        studentMapper.deleteByPrimaryKey(id);
    }

    public List<StudentQueryResp> queryByKeyWord(String keyWord) {
        StudentExample studentExample = new StudentExample();
        if (!ObjectUtil.isEmpty(keyWord)) {
            studentExample.or().andSchoolLike(keyWord + "%");
            studentExample.or().andUsernameEqualTo(keyWord);
        }
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        List<StudentQueryResp> list = BeanUtil.copyToList(studentList, StudentQueryResp.class);
        return list;
    }
}