package fun.yizhierha.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.yizhierha.operation.domain.OraServer;
import org.apache.ibatis.annotations.Mapper;

/** generated by EH-Admin
* @author xaopohi
* @date Wed Dec 21 13:25:19 CST 2022
**/
@Mapper
public interface OraServerMapper extends BaseMapper<OraServer>{
    public OraServer findByIp(String ip);
}