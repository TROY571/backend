package com.example.backend.mapper;

import com.example.backend.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User findBySchoolId(Long schoolId);

    User findByUserId(Long userId);

    List<User> findByMajorId(Long majorId);

    List<User> findByRole(@Param("role") User.Role role);

    void insertUser(User user);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUser(Long userId);

    List<User> findByRoleAndMajor(Map<String, Object> params);
}
