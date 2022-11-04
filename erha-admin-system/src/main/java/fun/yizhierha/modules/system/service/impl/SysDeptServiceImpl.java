package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysRolesDepts;
import fun.yizhierha.modules.system.domain.SysUser;
import fun.yizhierha.modules.system.domain.vo.CreateDeptVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveDeptVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDeptVo;
import fun.yizhierha.modules.system.service.SysRolesDeptsService;
import fun.yizhierha.modules.system.service.SysUserService;
import fun.yizhierha.modules.system.service.dto.DeptDto;
import fun.yizhierha.modules.system.service.dto.SummaryDeptDto;
import fun.yizhierha.modules.system.service.dto.SummaryRoleDto;
import fun.yizhierha.modules.system.service.mapstruct.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.domain.SysDept;
import fun.yizhierha.modules.system.mapper.SysDeptMapper;
import fun.yizhierha.modules.system.service.SysDeptService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService{

    // 解决autowired爆红 required = false
    @Autowired(required = false)
    DeptMapper deptMapper;

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRolesDeptsService sysRolesDeptsService;

    public static Comparator<SysDept> comparator = Comparator.comparing(SysDept::getDeptSort, Comparator.comparingInt(i -> i));


    public void removeByIds(Set<Long> deptIds) {
        List<SysDept> allSysDeptList = list();
        Map<Long, SysDept> allSysDeptMap = allSysDeptList.stream()
                .collect(Collectors.toMap(SysDept::getDeptId, (t) -> t));

        List<SysDept> toDeleteSysDeptList = list(new QueryWrapper<SysDept>().in(SysDept.COL_DEPT_ID, deptIds));
        // 0.检测 这些deptIds是否属于同一层级
        if (!checkIsSameLayer(toDeleteSysDeptList)){
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "这些deptId不在同一个层级"
            );
        }

        // 1.根据id找出所有子部门id,放入集合中
        List<Long> allToDeleteDeptIds = new ArrayList<>(deptIds);
        for (Long deptId : deptIds) {
            List<DeptDto> sonDeptList = getSonDept(deptId, allSysDeptList);
            for (DeptDto sonDept : sonDeptList) {
                allToDeleteDeptIds.add(sonDept.getDeptId());
                allToDeleteDeptIds.addAll(getSonDeptId(sonDept));
            }
        }
        // 2.检测 role与dept的关联
        if (sysRolesDeptsService
                .list(new QueryWrapper<SysRolesDepts>().in(SysRolesDepts.COL_DEPT_ID,allToDeleteDeptIds)).size() > 0
        ){
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "存在role与dept的关联,请先解除关联"
            );
        }
        // 3.检测 user与dept的关联
        if (sysUserService
                .list(new QueryWrapper<SysUser>().in(SysUser.COL_DEPT_ID,allToDeleteDeptIds)).size() > 0){
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "存在role与user的关联,请先解除关联"
            );
        }
        // 4.删除
        removeByIds(toDeleteSysDeptList);
    }

    @Override
    public void download(HttpServletResponse response) {
        List<SummaryDeptDto> summaryDeptDtos = deptMapper.toSummaryDeptDtos(list());
        ExcelUtils.export(response,"部门信息表",summaryDeptDtos, SummaryDeptDto.class);
    }

    @Override
    public List<Object> listForTree() {
        ArrayList<Object> res = new ArrayList<>();
        List<SysDept> all = this.list(new QueryWrapper<SysDept>().eq(SysDept.COL_ENABLED, true));
        // 先找出顶级部门
        List<SysDept> level1Depts = all
                .stream()
                .filter((t) -> t.getPid() == null)
                .collect(Collectors.toList());

        // 排序

        level1Depts.sort(comparator);
        // 再找出其对应的子部门
        for (SysDept level1Dept : level1Depts) {
            HashMap<String, Object> t = new HashMap<>();
            t.put("title",level1Dept.getName());
            t.put("key",level1Dept.getDeptId());
            t.put("children",getSonDeptTree(level1Dept,all));
            res.add(t);
        }

        return res;
    }

    @Override
    public DeptDto listForDeptDtoByDeptId(Long deptId) {
        List<SysDept> sysDepts = this.list();
        DeptDto res = null;
        for (SysDept t : sysDepts) {
            if (t.getDeptId().equals(deptId)){
                res = deptMapper.toDto(t);
                break;
            }
        }
        if (res != null){
            List<DeptDto> childrenDepts = getSonDept(deptId,sysDepts);
            res.setChildrenDept(childrenDepts);
        }


        return  res;
    }

    private List<DeptDto> getSonDept(Long deptId, List<SysDept> all) {
        Objects.requireNonNull(deptId);
        List<DeptDto> res = new ArrayList<>();

        List<SysDept> childrenDept = all
                .stream()
                .filter((t) -> {
                    return deptId.equals(t.getPid());
                })
                .collect(Collectors.toList());

        if (!childrenDept.isEmpty()){
            res = deptMapper.toDtoList(childrenDept);
            // 递归再找子部门
            for (DeptDto dept : res) {
                List<DeptDto> sonDept = getSonDept(dept.getDeptId(), all);
                dept.setChildrenDept(sonDept);
            }
        }

        return res;
    }

    @Override
    public Set<Long> listForDeptIdsByDeptId(Long deptId) {
        DeptDto deptDto = listForDeptDtoByDeptId(deptId);
        HashSet<Long> res = new HashSet<>();
        res.add(deptId);
        if (deptDto != null){
            res.addAll(getSonDeptId(deptDto));
        }

        return res;
    }

    @Override
    public String[] getNameByRoleId(Long id) {
        return baseMapper.selectNameByRoleId(id);
    }

    @Override
    public PageUtils<SummaryDeptDto> listDept(RetrieveDeptVo retrieveDeptVo, Query.PageVo pageVo) {
        QueryWrapper<SysDept> wrapper = new QueryWrapper<>();

        Boolean deptStatus = retrieveDeptVo.getDeptStatus();
        String name = retrieveDeptVo.getName();
        Timestamp startCreateTime = retrieveDeptVo.getStartCreateTime();
        Timestamp endCreateTime = retrieveDeptVo.getEndCreateTime();
        Long deptId = retrieveDeptVo.getDeptId();

        if (deptStatus != null){
            wrapper.eq(SysDept.COL_ENABLED,deptStatus);
        }
        if (name != null){
            wrapper.like(SysDept.COL_NAME,name);
        }
        if (startCreateTime != null && endCreateTime != null){
            wrapper.between(SysDept.COL_CREATE_TIME,startCreateTime,endCreateTime);
        }

        if (deptId != null){
            // 找此deptId的子部门
            wrapper.eq(SysDept.COL_PID,deptId);
        }else if (name == null){
            // 找顶级部门
            wrapper.isNull(SysDept.COL_PID);
        }

        wrapper.orderByAsc(SysDept.COL_DEPT_SORT);
        IPage<SysDept> deptIPage = baseMapper.selectPage(new Query<SysDept>().getPage(pageVo), wrapper);
        return new PageUtils<>(deptMapper.toBeSummaryDeptDto(deptIPage));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void save(CreateDeptVo createDeptVo, UserDetailsDto currentUser) {
        // 0.检测是否有重名的部门
        if (getOne(new QueryWrapper<SysDept>().eq(SysDept.COL_NAME,createDeptVo.getName())) != null) {
            throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(), "该部门名称重复！");
        }
        // 1.映射基本数据
        SysDept sysDept = deptMapper.toSysDept(createDeptVo);
        // 2.添加其他数据并保存: createBy....
        // 如果是顶级部门则忽略存储pid
        if (createDeptVo.getIsTop()){
            sysDept.setPid(null);
        }
        sysDept.setSubCount(0);
        sysDept.setCreateBy(currentUser.getUsername());
        sysDept.setCreateTime(new Date());
        this.save(sysDept);
        // 3.如果不是顶级部门则修改其pid对应的subCount
        if (!createDeptVo.getIsTop()){
            this.update(new UpdateWrapper<SysDept>()
                    .eq(SysDept.COL_DEPT_ID,sysDept.getPid())
                    .setSql(SysDept.COL_SUB_COUNT+" = " + SysDept.COL_SUB_COUNT+ " + 1")
            );
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void editDept(ValidList<UpdateDeptVo> updateDeptVos, List<BaseErrDto> errDtos, UserDetailsDto currentUser) {

        List<Long> updateDeptVoIds = new ArrayList<>();
        for (UpdateDeptVo updateDeptVo : updateDeptVos) {
            updateDeptVoIds.add(updateDeptVo.getId());
        }
        List<SysDept> originSysDept = list(new QueryWrapper<SysDept>().in(SysDept.COL_DEPT_ID, updateDeptVoIds));
        // 1.检测是不是一个层级的部门
        if (!checkIsSameLayer(originSysDept)){
            throw new BadRequestException(BizCodeEnum.Client_Error_CRUD.getCode(), BizCodeEnum.Client_Error_CRUD.getMsg());
        }


        // 2.isTop != null || pid != null  校验pid是否合理,收集要更新的dept: 因pid更新导致相应sub_count的更新 && 重名检测
        Map<Long, SysDept> allSysDeptMap = list().stream()
                .collect(Collectors.toMap(SysDept::getDeptId, (t) -> t));
        List<Long> errDtoIds = new ArrayList<>();
        List<UpdateWrapper<SysDept>> toUpdateSubCountSysDept = new ArrayList<>();

        for (UpdateDeptVo updateDeptVo : updateDeptVos) {
            Boolean isTop = updateDeptVo.getIsTop();
            Long pid = updateDeptVo.getPid();
            Long id = updateDeptVo.getId();

            String originSysDeptName = allSysDeptMap.get(id).getName();
            String newDeptName = updateDeptVo.getName();
            if (newDeptName != null){
                if (getOne(new QueryWrapper<SysDept>().eq(SysDept.COL_NAME,newDeptName)) != null){
                    errDtoIds.add(id);

                    BaseErrDto baseErrDto = new BaseErrDto();
                    baseErrDto.setId(id);
                    baseErrDto.setErrorVal(newDeptName);
                    baseErrDto.setErrorMsg("部门名重复");
                    baseErrDto.setErrorField("name");
                    errDtos.add(baseErrDto);
                }
            }

            if (isTop == null){
                if (pid != null){
                    // 判断要更改pid的是否存在依赖循环
                    boolean existCircle = false;

                    // 依赖信息
                    StringBuilder dependRelation = new StringBuilder(originSysDeptName +"->");
                    Long whilePid = pid;
                    while (true){
                        if (whilePid != null){
                            // 得到之前pid对应元素的pid
                            SysDept tempSysDept = allSysDeptMap.get(whilePid);
                            whilePid = tempSysDept.getPid();
                            dependRelation.append(tempSysDept.getName()).append("->");
                            if (id.equals(whilePid)){
                                dependRelation.append(originSysDeptName).append("↺");
                                existCircle = true;
                                break;
                            }
                        }else {
                            break;
                        }
                    }
                    if (existCircle){
                        //保存错误信息，记录错误id,循环结束后根据errDtoIds去除相关元素
                        BaseErrDto baseErrDto = new BaseErrDto();
                        baseErrDto.setId(id);
                        baseErrDto.setErrorField("pid");
                        baseErrDto.setErrorVal("PID:"+pid);
                        baseErrDto.setErrorMsg("存在依赖循环:"+dependRelation.toString());
                        errDtos.add(baseErrDto);
                        errDtoIds.add(id);
                    }else {
                        setUpdateSubCountInfo(toUpdateSubCountSysDept,allSysDeptMap, id,pid);
                    }
                }
            }else { // isTop != null
                if (isTop){
                    setUpdateSubCountInfo(toUpdateSubCountSysDept,allSysDeptMap, id,null);
                }else { // isTop == false
                    if (pid != null){
                        // 判断要更改pid的是否存在依赖循环
                        boolean existCircle = false;

                        // 依赖信息
                        StringBuilder dependRelation = new StringBuilder(originSysDeptName +"->");
                        Long whilePid = pid;
                        while (true){
                            if (whilePid != null){
                                // 得到之前pid对应元素的pid
                                SysDept tempSysDept = allSysDeptMap.get(whilePid);
                                whilePid = tempSysDept.getPid();
                                dependRelation.append(tempSysDept.getName()).append("->");
                                if (id.equals(whilePid)){
                                    dependRelation.append(originSysDeptName).append("↺");
                                    existCircle = true;
                                    break;
                                }
                            }else {
                                break;
                            }
                        }
                        if (existCircle){
                            //保存错误信息，记录错误id,循环结束后根据errDtoIds去除相关元素
                            BaseErrDto baseErrDto = new BaseErrDto();
                            baseErrDto.setId(id);
                            baseErrDto.setErrorField("pid");
                            baseErrDto.setErrorVal("PID:"+pid);
                            baseErrDto.setErrorMsg("存在依赖循环:"+dependRelation.toString());
                            errDtos.add(baseErrDto);
                            errDtoIds.add(id);
                        }else {
                            setUpdateSubCountInfo(toUpdateSubCountSysDept,allSysDeptMap, id,pid);
                        }
                    }else { // pid == null
                        BaseErrDto baseErrDto = new BaseErrDto();
                        baseErrDto.setId(id);
                        baseErrDto.setErrorField("pid");
                        baseErrDto.setErrorVal("null");
                        baseErrDto.setErrorMsg("isTop为false 但未提供pid");
                        errDtos.add(baseErrDto);
                        errDtoIds.add(id);
                    }
                }
            }


        }
        // 3.修改toUpdateSubCountSysDept
        for (UpdateWrapper<SysDept> updateWrapper : toUpdateSubCountSysDept) {
            this.update(updateWrapper);
        }

        // 4.剔除之前的不合理deptVos
        for (Long errDtoId : errDtoIds) {
            updateDeptVos.removeIf(t -> errDtoId.equals(t.getId()));
        }

        // 5.写入基本属性
        List<SysDept> toUpdateSysDeptList = deptMapper.toSysDepts(updateDeptVos);
        for (SysDept sysDept : toUpdateSysDeptList) {
            sysDept.setUpdateBy(currentUser.getUsername());
            sysDept.setUpdateTime(new Date());
        }

        // 6.修改SysDept基本信息
        this.updateBatchById(toUpdateSysDeptList);
    }

    private boolean checkIsSameLayer(List<SysDept> originSysDept) {
        for (int i = 0; i < originSysDept.size()-1; i++) {
            if (!Objects.equals(originSysDept.get(i).getPid(),originSysDept.get(i+1).getPid())){
                return false;
            }
        }
        return  true;
    }


    private void setUpdateSubCountInfo(List<UpdateWrapper<SysDept>> toUpdateSubCountSysDept, Map<Long, SysDept> allSysDeptMap, Long deptId, Long newPid) {
        // pid对应的记录 sub_count + 1
        if (newPid != null){
            toUpdateSubCountSysDept.add(
                    new UpdateWrapper<SysDept>()
                            .eq(SysDept.COL_DEPT_ID, newPid)
                            .setSql(SysDept.COL_SUB_COUNT+" = " + SysDept.COL_SUB_COUNT+ " + 1")
            );
        }else {
            if (deptId != null){
                toUpdateSubCountSysDept.add(
                        new UpdateWrapper<SysDept>()
                                .eq(SysDept.COL_DEPT_ID, deptId)
                                .setSql(SysDept.COL_PID+" =  null")
                );
            }

        }
        // deptId对应的记录 sub_count -1
        if (allSysDeptMap.get(deptId) != null && allSysDeptMap.get(deptId).getPid() != null){
            toUpdateSubCountSysDept.add(
                    new UpdateWrapper<SysDept>()
                            .eq(SysDept.COL_DEPT_ID,allSysDeptMap.get(deptId).getPid())
                            .setSql(SysDept.COL_SUB_COUNT+" = " + SysDept.COL_SUB_COUNT+ " - 1")
            );
        }
    }

    private Set<Long> getSonDeptId(DeptDto deptDto) {
        Objects.requireNonNull(deptDto);
        HashSet<Long> res = new HashSet<>();
        List<DeptDto> childrenDept = deptDto.getChildrenDept();

        if (childrenDept!= null){
            for (DeptDto child : childrenDept) {
                res.add(child.getDeptId());
                res.addAll(getSonDeptId(child));
            }
        }
        return res;
    }

    private List<Object> getSonDeptTree(SysDept dept, List<SysDept> all) {

        List<SysDept> childrenDepts = all
                .stream()
                .filter(sysDept -> dept.getDeptId().equals(sysDept.getPid()))
                .collect(Collectors.toList());
        // 排序
        childrenDepts.sort(comparator);

        ArrayList<Object> list = new ArrayList<>();

        if (!childrenDepts.isEmpty()){
            for (SysDept sysDept : childrenDepts) {
                HashMap<String, Object> temp = new HashMap<>();
                temp.put("title",sysDept.getName());
                temp.put("key",sysDept.getDeptId());
                temp.put("children",getSonDeptTree(sysDept,all));
                list.add(temp);
            }
        }

        return list;
    }

}
