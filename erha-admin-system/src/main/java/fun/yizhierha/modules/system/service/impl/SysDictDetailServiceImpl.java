package fun.yizhierha.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.yizhierha.common.base.BaseErrDto;
import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.common.exception.BizCodeEnum;
import fun.yizhierha.common.utils.PageUtils;
import fun.yizhierha.common.utils.Query;
import fun.yizhierha.common.utils.ValidList;
import fun.yizhierha.modules.security.service.dto.UserDetailsDto;
import fun.yizhierha.modules.system.domain.vo.CreateDictDetailVo;
import fun.yizhierha.modules.system.domain.vo.UpdateDictDetailVo;
import fun.yizhierha.modules.system.service.dto.DictDetailDto;
import fun.yizhierha.modules.system.service.mapstruct.DictDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.modules.system.mapper.SysDictDetailMapper;
import fun.yizhierha.modules.system.domain.SysDictDetail;
import fun.yizhierha.modules.system.service.SysDictDetailService;
@Service
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements SysDictDetailService{

    @Autowired(required = false)
    DictDetailMapper dictDetailMapper;

    @Override
    public List<SysDictDetail> listByDictName(String dictName) {
        return baseMapper.selectByDictName(dictName);
    }

    @Override
    public PageUtils<DictDetailDto> listDictDetail(Long dictId, Query.PageVo pageVo) {
        Objects.requireNonNull(dictId);
        Objects.requireNonNull(pageVo);

        IPage<SysDictDetail> detailIPage = baseMapper.selectPage(
                new Query<SysDictDetail>().getPage(pageVo),
                new QueryWrapper<SysDictDetail>().eq(SysDictDetail.COL_DICT_ID, dictId)
        );

        Page<DictDetailDto> dictDetailDtoPage = dictDetailMapper.toDictDetailDto(detailIPage);
        // 排序
        dictDetailDtoPage.getRecords().sort(Comparator.comparing(DictDetailDto::getDictSort));
        return new PageUtils<>(dictDetailDtoPage);
    }

    @Override
    public void saveDictDetail(CreateDictDetailVo createDictDetailVo, UserDetailsDto currentUser) {
        // 1. 检测名称是否重复
        if (!list(new QueryWrapper<SysDictDetail>().eq(SysDictDetail.COL_LABEL,createDictDetailVo.getLabel())).isEmpty()) {
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "标签重复！"
            );
        }
        // 2. 复制，修改基础属性
        SysDictDetail sysDictDetail = dictDetailMapper.toSysDictDetail(createDictDetailVo);
        sysDictDetail.setCreateBy(currentUser.getUsername());
        sysDictDetail.setCreateTime(new Date());

        // 3. 添加
        save(sysDictDetail);
    }

    @Override
    public synchronized void updateDictDetail(ValidList<UpdateDictDetailVo> updateDictDetailVos, List<BaseErrDto> errDtoList, UserDetailsDto currentUser) {
        List<Long> toRemoveIds = new ArrayList<>();
        Map<String,Long> labelForId = new HashMap<>();
        // 1.检验label是否重复

        for (UpdateDictDetailVo updateDictDetailVo : updateDictDetailVos) {
            Long id = updateDictDetailVo.getId();
            String label = updateDictDetailVo.getLabel();
            if (label != null){
                // 1.1 数据库中
                if (!list(new QueryWrapper<SysDictDetail>().eq(SysDictDetail.COL_LABEL,label)).isEmpty()) {
                    toRemoveIds.add(id);
                    BaseErrDto baseErrDto = new BaseErrDto();
                    baseErrDto.setId(id);
                    baseErrDto.setErrorVal(label);
                    baseErrDto.setErrorField(SysDictDetail.COL_LABEL);
                    baseErrDto.setErrorMsg("字典标签重复");
                    errDtoList.add(baseErrDto);
                }
                // 1.2 要更新的数据中
                if (labelForId.containsKey(label)) {
                    Long previousId = labelForId.get(label);
                    BaseErrDto b1 = new BaseErrDto();
                    BaseErrDto b2 = new BaseErrDto();
                    b1.setId(previousId);
                    b1.setErrorField(SysDictDetail.COL_LABEL);
                    b1.setErrorVal(label);
                    b1.setErrorMsg("与本次修改的数据中名称重复");
                    b2.setId(id);
                    b2.setErrorField(SysDictDetail.COL_LABEL);
                    b2.setErrorVal(label);
                    b2.setErrorMsg("与本次修改的数据中名称重复");
                    errDtoList.add(b1);
                    errDtoList.add(b2);

                }else {
                    labelForId.put(label,id);
                }

            }
        }

        // 2.去除不合理的记录
        for (Long toRemoveId : toRemoveIds) {
            updateDictDetailVos.removeIf(t-> toRemoveId.equals(t.getId()));
        }

        // 3.复制，修改基础属性
        List<SysDictDetail> sysDictDetails = dictDetailMapper.toSysDictDetailList(updateDictDetailVos);
        for (SysDictDetail sysDictDetail : sysDictDetails) {
            sysDictDetail.setUpdateBy(currentUser.getUsername());
            sysDictDetail.setUpdateTime(new Date());
        }
        // 4.修改
        updateBatchById(sysDictDetails);

    }

    @Override
    public void removeDictDetail(Set<Long> dictDetailIds) {
        if (dictDetailIds == null || dictDetailIds.isEmpty()) {
            throw new BadRequestException(
                    BizCodeEnum.Client_Error_CRUD.getCode(),
                    "要删除的字典详情id不能为空"
            );
        }
        removeBatchByIds(dictDetailIds);
    }
}
