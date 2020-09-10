package com.lsh.demo.bootstone.dao.mapstruct;


import org.mapstruct.Mapper;

/**
 * Created by lsh on 2020-09-02.
 */
@Mapper
public interface MapSOrderMapper {

    MapSOrderQueryParam entity2queryParam(MapSOrder order);

}
