package com.mf.feel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mf.feel.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
