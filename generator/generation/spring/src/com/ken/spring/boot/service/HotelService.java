package com.ken.spring.boot.service;

import com.ken.spring.boot.dao.domain.Hotel;
import java.util.List;

public interface HotelService {
    int delete(Integer id);

    int insert(Hotel record);

    int update(Hotel record);

    Hotel select(Integer id);

    Hotel selectOne(Hotel record);

    List<Hotel> selectSelective(Hotel record);
}