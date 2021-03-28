package com.bjtu.airport.service;

import com.bjtu.airport.entity.ParkinggatePriority;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenYakun
 * @since 2020-11-07
 */
public interface IParkinggatePriorityService extends IService<ParkinggatePriority> {

    /* **
     * @Author: chenyakun
     * @Description: 根据用户名和机场名称请求航班类型
     * @Date 2020/11/9 14:17
     * @Param airportname
     * @param userId
     * @return java.util.List<net.sf.json.JSONObject>
     * @throws:
     **/
    List<JSONObject> selectType(String airportname, String userId);

    /* **
     * @Author: lx
     * @Description: 根据用户名和机场名称请求航班类型
     * @Date 2020/11/9 14:17
     * @Param airportname
     * @param userId
     * @return java.util.List<net.sf.json.JSONObject>
     * @throws:
     **/
    List<JSONObject> select22(String airportname, String userId);

    /* **
     * @Author: chenyakun
     * @Description: 根据用户名、机场名称、航班类型查询数据
     * @Date 2020/11/9 14:11
     * @Param null
     * @return
     * @throws:
     **/
    List<JSONObject> selectByAirportnameAndUserIdAndType(String airportname,String flighttype, String userId);

    /* **
     * @Author: chenyakun
     * @Description: 根据机场名称查询该机场有哪些机位号
     * @Date 2020/11/10 10:20
     * @Param null
     * @return
     * @throws:
     **/
    List<JSONObject> selectParkinggate(String airportname);

    /* **
     * @Author: chenyakun
     * @Description: 根据groupid、航班类型、机场名称、用户名删除数据
     * @Date 2020/11/10 19:30
     * @Param null
     * @return
     * @throws:
     **/
    int deleteParkinggate(String airportname,Integer groupid, String flighttype,String userId);



}
