package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.common.utils.file.ExcelUtils;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.SysDictDetail;
import fun.yizhierha.modules.system.domain.vo.CreateDictVo;
import fun.yizhierha.modules.system.domain.vo.RetrieveDictVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDictVo;
import fun.yizhierha.modules.system.service.SysDictDetailService;
import fun.yizhierha.modules.system.service.dto.DictDto;
import fun.yizhierha.modules.system.service.dto.SummaryDictDto;
import fun.yizhierha.modules.system.service.mapstruct.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.domain.SysDict;
import fun.yizhierha.modules.system.mapper.SysDictMapper;
import fun.yizhierha.modules.system.service.SysDictService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService{

    @Autowired
    SysDictDetailService sysDictDetailService;


    @Autowired(required = false)
    DictMapper dictMapper;

    @Override
    public PageUtils<DictDto> listDict(RetrieveDictVo retrieveDictVo, Query.PageVo pageVo) {
        QueryWrapper<SysDict> wrapper = new QueryWrapper<>();
        String name = retrieveDictVo.getName();
        if (name != null){
            wrapper.like(SysDict.COL_NAME,name);
        }

        Page<DictDto> summaryDictDtoPage = dictMapper.toDictDto(baseMapper.selectPage(new Query<SysDict>().getPage(pageVo), wrapper));
        return new PageUtils<>(summaryDictDtoPage);
    }

    @Override
    public void saveDict(CreateDictVo createDictVo, UserDetailsDto currentUser) {
        // 1.查看是否重名
        if (!list(new QueryWrapper<SysDict>().eq(SysDict.COL_NAME,createDictVo.getName())).isEmpty()) {
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "字典名重复!"
            );
        }
        // 2.添加
        SysDict sysDict = new SysDict();
        sysDict.setName(createDictVo.getName());
        sysDict.setDescription(createDictVo.getDescription());
        sysDict.setCreateBy(currentUser.getUsername());
        sysDict.setCreateTime(new Date());

        save(sysDict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void editDict(ValidList<UpdateDictVo> updateDictVos, List<BaseErrDto> errDtoList, UserDetailsDto currentUser) {
        List<Long> toRemoveIds = new ArrayList<>();
        HashMap<String,Long> nameForIdMap = new HashMap<>();
        // 1.校验name是否重复

        for (UpdateDictVo updateDictVo : updateDictVos) {
            String name = updateDictVo.getName();
            Long id = updateDictVo.getId();
            if (name != null){
                // 1.1 与数据库中比对
                if (!list(new QueryWrapper<SysDict>().eq(SysDict.COL_NAME,name)).isEmpty()){
                    toRemoveIds.add(id);
                    BaseErrDto baseErrDto = new BaseErrDto();
                    baseErrDto.setErrorMsg("名称重复");
                    baseErrDto.setErrorField("name");
                    baseErrDto.setId(id);
                    baseErrDto.setErrorVal(name);
                    errDtoList.add(baseErrDto);
                }

                // 1.2 与修改中的数据比对
                if (nameForIdMap.containsKey(name)){
                    Long previousId = nameForIdMap.get(name);
                    toRemoveIds.add(previousId);
                    toRemoveIds.add(id);
                    BaseErrDto b1 = new BaseErrDto();
                    b1.setId(previousId);
                    b1.setErrorField("name");
                    b1.setErrorMsg("与本次修改的数据中名称重复");
                    b1.setErrorVal(name);
                    BaseErrDto b2 = new BaseErrDto();
                    b2.setId(id);
                    b2.setErrorField("name");
                    b2.setErrorMsg("与本次修改的数据中名称重复");
                    b2.setErrorVal(name);
                    errDtoList.add(b1);
                    errDtoList.add(b2);

                }else {
                    nameForIdMap.put(name,id);
                }
            }

        }

        // 2.去除不合理的记录
        for (Long toRemoveId : toRemoveIds) {
            updateDictVos.removeIf(t->toRemoveId.equals(t.getId()));
        }

        // 3.复制，修改基础属性
        ArrayList<SysDict> sysDictArrayList = new ArrayList<>();
        for (UpdateDictVo updateDictVo : updateDictVos) {
            SysDict sysDict = new SysDict();
            sysDict.setDictId(updateDictVo.getId());
            sysDict.setUpdateBy(currentUser.getUsername());
            sysDict.setUpdateTime(new Date());
            sysDict.setName(updateDictVo.getName());
            sysDict.setDescription(updateDictVo.getDescription());
            sysDictArrayList.add(sysDict);
        }


        // 4.写入数据库
        updateBatchById(sysDictArrayList);
    }

    @Override
    public void removeDict(Set<Long> dictIds) {
        if (dictIds == null || dictIds.isEmpty()){
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "要删除的字典id不能为空！"
            );
        }

        // 1.检测是否与sys_dict_detail存在关联
        if (!sysDictDetailService.list(new QueryWrapper<SysDictDetail>().in(SysDictDetail.COL_DICT_ID,dictIds)).isEmpty()) {
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "要删除的字典id存在关联，请先删除其下的字典键值对"
            );
        }

        // 2.删除
        removeBatchByIds(dictIds);

    }

    @Override
    public void download(HttpServletResponse response) {
        List<SummaryDictDto> list = baseMapper.selectSummaryDictDtoList();
        ExcelUtils.export(response,"字典表",list,SummaryDictDto.class);
    }
}
