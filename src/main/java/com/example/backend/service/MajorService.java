package com.example.backend.service;

import com.example.backend.mapper.MajorMapper;
import com.example.backend.model.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MajorService {

    @Autowired
    private MajorMapper majorMapper;

    public Major findByMajorId(Long majorId) {
        return majorMapper.findByMajorId(majorId);
    }

    public List<Major> findAllMajors() {
        return majorMapper.findAllMajors();
    }

    public void insertMajor(Major major) {
        major.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        major.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        majorMapper.insertMajor(major);
    }

    public void updateMajor(Major major) {
        major.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        majorMapper.updateMajor(major);
    }

    public void deleteMajor(Long majorId) {
        majorMapper.deleteMajor(majorId);
    }
}
