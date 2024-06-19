package cn.hjf.basics.baseClass;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@ApiOperation(value = "模板数据链路层")
@NoRepositoryBean
public interface HjfBaseDao<E, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
    @Override
    E getById(ID id);
}
