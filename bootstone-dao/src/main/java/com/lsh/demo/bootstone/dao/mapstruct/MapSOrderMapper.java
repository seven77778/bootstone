package com.lsh.demo.bootstone.dao.mapstruct;


import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * Created by lsh on 2020-09-02.
 * <p>
 * processer包必不可少
 */
@Mapper
public interface MapSOrderMapper {

    MapSOrderMapper INSTANCE = Mappers.getMapper(MapSOrderMapper.class);

    @Mappings({
            @Mapping(target = "orderSn123", source = "orderSn"),
            @Mapping(target = "constantName", constant = "Lsh-constant"),
            @Mapping(target = "test", source = "testInteger", qualifiedByName = "convertTest")
    })
    MapSOrderQueryParam entity2queryParam(MapSOrder order);


    @Named("convertTest")
    default String convertTest(Integer status) {
        if (null == status) {
            return null;
        }
        switch (status) {
            case 1:
                return "ok";
            case 2:
                return "ana";
            case 3:
                return "fail";
            default:
                return null;
        }
    }

    static void main(String[] args) {

    }
}
