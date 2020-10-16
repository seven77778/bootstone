package com.lsh.demo.bootstone.dao.mapstruct;


import org.mapstruct.Mapper;

/**
 * Created by lsh on 2020-09-02.
 *
 * processer包必不可少
 */
@Mapper
public interface MapSOrderMapper {

    MapSOrderQueryParam entity2queryParam(MapSOrder order);

}
