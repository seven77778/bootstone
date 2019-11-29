package com.lsh.demo.bootstone.dao.mysql;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lsh on 2019-11-29.
 */

@Repository
public interface DataService {

    List<Stu> selectAllStu();

}
