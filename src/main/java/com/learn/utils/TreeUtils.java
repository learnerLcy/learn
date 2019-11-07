package com.learn.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:TreeUtils
 * @Description:
 * @Author:lvchunyang
 * @Date:15:19
 **/
public class TreeUtils {
    /**
     *@Author:lvchunyang
     *@Description: 根据pidKey为pidValue的值获取一级树信息
     *@Date:14:55 2019/7/8
     *@Para:[allDataList, pidKey, pidValue]
     *@Return:java.util.List<java.lang.Object>
     **/
    public static <T> List<T> getRootTreeInfo(Class<T> clazz, List<T> allDataList, String pidKey, String pidValue){
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        List<T> list = new ArrayList<>();
        for(T oneData : allDataList){
            Map<String,Object> map = ConventUtil.beanToMap(oneData);
            if(map.get(pidKey)==pidValue){
                resultList.add(map);
            }else if(pidValue!=null && pidValue.equals(map.get(pidKey))){
                resultList.add(map);
            }
        }
        try {
            list = ConventUtil.mapsToObjects(resultList,clazz);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  list;
    }
    /**
     *@Author:lvchunyang
     *@Description: 获取树完整的层级结构信息,实体类要有chrildrenList字段
     *@Date:9:29 2019/7/9
     *@Para:[clazz, allDataList, rootDataList, idKey, pidKey]
     *@Return:java.util.List<T>
     **/
    public static <T> List<T> getAllTreeInfo(Class<T> clazz,List<T> allDataList,List<T> rootDataList,String idKey,String pidKey) throws Exception{
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        List<T> list = new ArrayList<>();
        //子节点信息记录
        List<Map<String,Object>> chrildrenList;
        List<T> tList;
        //首先allDataList中去除rootDataList，减少遍历数据量
        allDataList.removeAll(rootDataList);
        //遍历rootDataList、allDataList
        for(T rootData : rootDataList){
            chrildrenList = new ArrayList<Map<String, Object>>();
            tList = new ArrayList<T>();

            Map<String,Object> rootDataMap = ConventUtil.beanToMap(rootData);
            for(T allData : allDataList){
                Map<String,Object> allDataMap = ConventUtil.beanToMap(allData);
                if(rootDataMap.get(idKey).equals(allDataMap.get(pidKey))){
                    chrildrenList.add(allDataMap);
                }
            }
            if(!chrildrenList.isEmpty()){
                tList = ConventUtil.mapsToObjects(chrildrenList,clazz);
                //去除以得到的,减少后面的遍历数据
                allDataList.removeAll(tList);
                tList = getAllTreeInfo(clazz,allDataList,tList,idKey,pidKey);
                rootDataMap.put("chrildrenList",ConventUtil.objectsToMaps(tList));
            }
            resultList.add(rootDataMap);
        }

        list = ConventUtil.mapsToObjects(resultList,clazz);
        return list;
    }
}
