package com.ken.spring.boot.dao;

import com.ken.spring.boot.dao.domain.Hotel;
import java.util.List;

public interface HotelDao {
    int delete(Integer id);

    int insert(Hotel record);

    Hotel select(Integer id);

    int update(Hotel record);

    Hotel selectOne(Hotel record);

    List<Hotel> selectSelective(Hotel record);
}