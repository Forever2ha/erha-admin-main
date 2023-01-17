package fun.yizhierha.operation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.yizhierha.operation.domain.OraDeploy;
import fun.yizhierha.operation.domain.OraDeployServer;
import fun.yizhierha.operation.mapper.OraDeployServerMapper;
import fun.yizhierha.operation.service.OraDeployServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OraDeployServerServiceImpl extends ServiceImpl<OraDeployServerMapper, OraDeployServer> implements OraDeployServerService {
    @Override
    public boolean save(List<OraDeployServer> vo) {
        this.saveBatch(vo);
        return true;
    }

}
