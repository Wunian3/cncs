package cn.hjf.study.entity;

import cn.hjf.basics.baseClass.HjfBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_assignment")
@TableName("a_assignment")
@ApiModel(value = "课程作业")
public class Assignment extends HjfBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private String curriculumId;

    @ApiModelProperty(value = "课程名称")
    private String curriculumName;

    @ApiModelProperty(value = "作业名称")
    private String title;

    @ApiModelProperty(value = "作业附件")
    private String file1;

    @ApiModelProperty(value = "完成附件")
    private String file2;

    @ApiModelProperty(value = "学生ID")
    private String userId;

    @ApiModelProperty(value = "学生姓名")
    private String userName;
}