package com.baway.historydays.bean;

import java.util.List;

/**
 * 1. 类的用途
 * 2. @author： 李小兵
 * 3. @date：2017/3/18 9:14
 */

public class GirlBean {


    /**
     * error : false
     * results : [{"_id":"58b76cb9421aa90f131785e4","createdAt":"2017-03-02T08:52:09.811Z","desc":"3-02","publishedAt":"2017-03-02T11:51:29.672Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-02-17077393_314135475655975_7855162741030387712_n.jpg","used":true,"who":"代码家"},{"_id":"58b60881421aa90efc4fb627","createdAt":"2017-03-01T07:32:17.106Z","desc":"3-1","publishedAt":"2017-03-01T12:03:57.461Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-28-15057157_446684252387131_4267811446148038656_n.jpg","used":true,"who":"daimajia"},{"_id":"58b4b764421aa90efc4fb61b","createdAt":"2017-02-28T07:33:56.976Z","desc":"2-28","publishedAt":"2017-02-28T11:45:44.815Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-27-tumblr_om1aowIoKa1qbw5qso1_540.jpg","used":true,"who":"daimajia"},{"_id":"58b372a7421aa90f033450e0","createdAt":"2017-02-27T08:28:23.976Z","desc":"2-27","publishedAt":"2017-02-27T11:31:40.141Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-27-002809.jpg","used":true,"who":"daimajia"},{"_id":"58af7dca421aa957f9dd6dcc","createdAt":"2017-02-24T08:26:50.512Z","desc":"2-24","publishedAt":"2017-02-24T11:47:11.416Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-24-16906635_1749384985376684_7563808952991875072_n.jpg","used":true,"who":"daimajia"},{"_id":"58ae32e1421aa957f9dd6dbe","createdAt":"2017-02-23T08:54:57.835Z","desc":"2-23","publishedAt":"2017-02-23T11:50:23.116Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-23-16906361_1846962082218545_7116720067412230144_n.jpg","used":true,"who":"daimajia"},{"_id":"58acdc37421aa93d376f74fd","createdAt":"2017-02-22T08:32:55.801Z","desc":"2-22","publishedAt":"2017-02-22T11:43:57.286Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-22-16789541_749566998525174_1194252851069583360_n.jpg","used":true,"who":"daimajia "},{"_id":"58ab0187421aa93d3d15aa3b","createdAt":"2017-02-20T22:47:35.276Z","desc":"2-21","publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-20-16788512_385322578500460_8844315265740046336_n.jpg","used":true,"who":"daimajia"},{"_id":"58a9752b421aa93d3d15aa31","createdAt":"2017-02-19T18:36:27.16Z","desc":"2-20","publishedAt":"2017-02-20T11:56:22.616Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-19-16789884_666922016823652_4719569931841044480_n.jpg","used":true,"who":"daimajia"},{"_id":"58a641a4421aa9662f429734","createdAt":"2017-02-17T08:19:48.413Z","desc":"2-17","publishedAt":"2017-02-17T11:31:19.996Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-17-16464474_721724277990542_654863958657728512_n.jpg","used":true,"who":"daimajia"},{"_id":"58a504d1421aa966366d05ce","createdAt":"2017-02-16T09:48:01.526Z","desc":"2-16","publishedAt":"2017-02-16T10:07:37.13Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-16-16790004_607292222809583_5160406710837313536_n.jpg","used":true,"who":"daimajia"}]
     */

    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean {
        /**
         * _id : 58b76cb9421aa90f131785e4
         * createdAt : 2017-03-02T08:52:09.811Z
         * desc : 3-02
         * publishedAt : 2017-03-02T11:51:29.672Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-02-17077393_314135475655975_7855162741030387712_n.jpg
         * used : true
         * who : 代码家
         */

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}
