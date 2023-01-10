package fun.yizhierha.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.yizhierha.operation.domain.OraDeployServer;

import java.util.List;

public interface OraDeployServerService extends IService<OraDeployServer> {
    boolean save(List<OraDeployServer> vo);

    void edit(List<OraDeployServer> editbeforvo, List<OraDeployServer> editaftervo);
}
