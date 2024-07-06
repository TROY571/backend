package com.example.backend.mapper;

import com.example.backend.model.AssignmentFile;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AssignmentFileMapper {
    AssignmentFile findByFileId(Long fileId);

    List<AssignmentFile> findByAssignmentId(Long assignmentId);

    void insertAssignmentFile(AssignmentFile assignmentFile);

    void deleteAssignmentFile(Long fileId);
}
