package com.xsg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsg.entity.ImportExportInfoTempEntity;
import com.xsg.entity.dto.IESearchDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: liangcf
 * @CreateTime: 2025-03-19
 * @Description: dao实现类
 */
@Mapper
public interface ImportExportInfoMapper extends BaseMapper<ImportExportInfoTempEntity> {
    IPage<IESearchDTO> searchListPage(Page<Object> objPage);
}
