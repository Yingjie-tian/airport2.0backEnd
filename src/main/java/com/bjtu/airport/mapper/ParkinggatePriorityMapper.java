package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.ParkinggatePriority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ChenYakun
 * @since 2020-11-07
 */
@Mapper
@Repository
public interface ParkinggatePriorityMapper extends BaseMapper<ParkinggatePriority> {

    /* **
     * @Author: chenyakun
     * @Description: 根据用户名和机场名称请求航班类型
     * @Date 2020/11/9 14:17
     * @Param airportname
     * @param userId
     * @return java.util.List<net.sf.json.JSONObject>
     * @throws:
     **/
    @Select({"SELECT DISTINCT flighttype FROM parkinggate_priority where airportname = #{airportname} AND userId = #{userId}"})
    List<JSONObject> selectType(@Param("airportname") String airportname, @Param("userId") String userId);

    /* **
     * @Author: lixiang
     * @Description: 根据用户名和机场名请求数据
     * @Date 2020/11/9 14:17
     * @Param airportname
     * @param userId
     * @return java.util.List<net.sf.json.JSONObject>
     * @throws:
     **/
    @Select({"SELECT * FROM PARKINGGATE_PRIORITY where airportname = #{airportname} AND userId = #{userId}"})
    List<JSONObject> select22(@Param("airportname") String airportname, @Param("userId") String userId);

    /* **
     * @Author: chenyakun
     * @Description: 根据用户名、机场名称、航班类型查询数据
     * @Date 2020/11/9 14:11
     * @Param null
     * @return
     * @throws:
     **/
    @Select({"SELECT * FROM parkinggate_priority where airportname = #{airportname} AND flighttype = #{flighttype} AND userId = #{userId} ORDER BY groupid,businesskey"})
    List<JSONObject> selectByAirportnameAndUserIdAndType(
            @Param("airportname") String airportname,
            @Param("flighttype") String flighttype,
            @Param("userId") String userId);

    /* **
     * @Author: chenyakun
     * @Description: 根据机场名称查询该机场有哪些机位号
     * @Date 2020/11/10 10:20
     * @Param null
     * @return
     * @throws:
     **/
    @Select({"SELECT parkinggate FROM GATE_INFO where airportname = #{airportname}"})
    List<JSONObject> selectParkinggate(@Param("airportname") String airportname);

    /* **
     * @Author: chenyakun
     * @Description: 根据groupid、航班类型、机场名称、用户名删除数据
     * @Date 2020/11/10 19:30
     * @Param null
     * @return
     * @throws:
     **/
    @Insert({"DELETE FROM parkinggate_priority where airportname = #{airportname} AND groupid = #{groupid}" +
            "AND flighttype = #{flighttype} AND userId = #{userId} "})
    int deleteParkinggate(
            @Param("airportname") String airportname,
            @Param("groupid") Integer groupid,
            @Param("flighttype") String flighttype,
            @Param("userId") String userId);

}
