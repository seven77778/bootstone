package biz.limit.controller;

import biz.limit.RateConfig;

import java.util.List;

public abstract class AbstractConfiger {


    protected List<RateConfig> addedConfigs;
    protected List<RateConfig> deletedConfigs;


    protected abstract void afterAdd();

    protected abstract void afterDelete();


    public synchronized void addConfigs(List<RateConfig> configs) {
        this.addedConfigs = configs;
        afterAdd();

    }

    public synchronized void deleteConfigs(List<RateConfig> configs) {
        this.deletedConfigs = configs;
        afterDelete();

    }



}
