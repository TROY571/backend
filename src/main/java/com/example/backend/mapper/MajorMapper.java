package com.example.backend.mapper;

import com.example.backend.model.Major;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MajorMapper {
    Major findByMajorId(Long majorId);

    List<Major> findAllMajors();

    void insertMajor(Major major);

    void updateMajor(Major major);

    void deleteMajor(Long majorId);
}
