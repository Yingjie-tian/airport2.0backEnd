package com.bjtu.airport.mapper;


import com.bjtu.airport.entity.ParkingMdl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 田英杰
 * @since 2020-12-03
 */
@Mapper
@Repository
public interface ParkingMdlMapper extends BaseMapper<ParkingMdl>{

    //    ****  Following is the airport2.0 code ****

     /**
      * @Author: 田英杰
      * @Description: 根据机场名称获取机位机型限制信息
      * @Date 2020/12/3 17:08
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Select("SELECT * FROM `PARKING_MDL` WHERE airportname = #{airportname} and userId = #{userId}")
    List<ParkingMdl> getInfoByAN(@Param("airportname") String airportname, @Param("userId") String userId);


     /**
      * @Author: 田英杰
      * @Description: 插入新的机位机型限制信息
      * @Date 2020/12/3 20:09
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Insert({"insert into PARKING_MDL(mdl, parkinggate, airportname, restriction, userId, updateTime) " +
            "values(#{mdl},#{parkinggate},#{airportname}, #{restriction}, #{userId},#{updateTime})"})
    int insterNewInfo(@Param("mdl") String mdl,
                   @Param("parkinggate") String  parkinggate,
                   @Param("airportname") String airportname,
                   @Param("userId") String userId,
                   @Param("restriction") Integer restriction,
                   @Param("updateTime") String updateTime);


     /**
      * @Author: 田英杰
      * @Description: 批量删除机位机型限制信息
      * @Date 2020/12/3 20:38
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Delete({"DELETE FROM PARKING_MDL WHERE id = #{id}"})
    int batchDelete(@Param("id") Integer id);


     /**
      * @Author: 田英杰
      * @Description: 修改机位机型限制信息
      * @Date 2020/12/4 15:20
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Update({"update PARKING_MDL set mdl = #{mdl}, parkinggate = #{parkinggate}, userId = #{userId}, updateTime = #{updateTime}, restriction = #{restriction} where id = #{id}"})
    int updateInfo(@Param("id") Integer id,
                        @Param("mdl") String mdl,
                        @Param("parkinggate") String  parkinggate,
                        @Param("userId") String userId,
                        @Param("restriction") Integer restriction,
                        @Param("updateTime") String updateTime);



}
