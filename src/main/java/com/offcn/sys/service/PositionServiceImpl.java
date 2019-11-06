package com.offcn.sys.service;

import com.offcn.sys.bean.Position;
import com.offcn.sys.bean.PositionExample;
import com.offcn.sys.mapper.PositionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<Position> selectPositionList() {
        return positionMapper.selectByExample(new PositionExample());
    }
}
