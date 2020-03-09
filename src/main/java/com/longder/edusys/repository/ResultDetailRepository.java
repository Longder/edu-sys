package com.longder.edusys.repository;

import com.longder.edusys.entity.po.ResultDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDetailRepository {


    void insert(@Param("detailList") List<ResultDetail> detailList);
}
