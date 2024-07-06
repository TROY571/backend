package com.example.backend.mapper;

import com.example.backend.model.Assignment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AssignmentMapper {
    Assignment findByAssignmentId(Long assignmentId);

    List<Assignment> findByCourseId(Long courseId);

    void insertAssignment(Assignment assignment);

    void updateAssignment(Assignment assignment);

    void deleteAssignment(Long assignmentId);
}
