package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.IoareaConflict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Repository
public interface IoareaConflictMapper extends BaseMapper<IoareaConflict> {
    @Select("select * from IOAREA_CONFLICT")
    List<IoareaConflict> getinfo();


    @Select({"SELECT areaid, parkinggate FROM IOAREA_CONFLICT where airportname = #{airportname}"})
    List<IoareaConflict> getRuleInfo(@Param("airportname") String airportname,@Param("userId")String userId);

    @Select({"SELECT areaid, parkinggate FROM IOAREA_CONFLICT where airportname = #{airportname} AND IOAREA_CONFLICT.userId = #{userId}"})
    List<IoareaConflict> getRuleInfoByuserId(@Param("airportname") String airportname, @Param("userId") String userId);


    @Select("SELECT\n" +
            "\tareaid,\n" +
            "\tGROUP_CONCAT(parkinggate separator '/') AS parkinggate\n" +
            "FROM\n" +
            "\t`IOAREA_CONFLICT`\n" +
            "WHERE\n" +
            "\tairportname = #{airportname} AND IOAREA_CONFLICT.userId = #{userId}\n" +
            "GROUP BY\n" +
            "\tareaid")
    List<IoareaConflict> getParkingGate(@Param("airportname") String airportname, @Param("userId") String userId);








    @Update({"update IOAREA_CONFLICT set areaid = #{areaid} where id = #{id} AND userId = #{userId}"})
    int updateinfo1(@Param("id") Integer id,
                    @Param("areaid") String areaid,
                    @Param("userId") String userId);


    @Update({"update IOAREA_CONFLICT set parkinggate = #{parkinggate} where id = #{id} AND userId = #{userId}"})
    int updateinfo2(@Param("id") Integer id,
                    @Param("parkinggate") String parkinggate,
                    @Param("userId") String userId);


    /**
     * 根据机场的名称和停机位号码删除数据表IOAREA_CONFLICT中的数据
     * @param airportname 传入机场名称
     * @param parkinggate 传入停机位号
     * @return
     */
    @Delete("DELETE FROM `IOAREA_CONFLICT` WHERE airportname = #{airportname} AND parkinggate = #{parkinggate} AND userId = #{userId}")
    int deleteParkingGate(@Param("airportname") String airportname,
                          @Param("parkinggate") String parkinggate,
                          @Param("userId") String userId);


//    ****  Following is the airport2.0 code ****

     /**
      * @Author: 田英杰
      * @Description: 根据id删除机场区域限停机位数据
      * @Date 2020/11/6 14:16
      * @Param  * @param null
      * @return 删除数据的条数
      * @throws:
      **/
    @Delete("DELETE FROM `IOAREA_CONFLICT` WHERE id = #{id}")
    int deleteArea(@Param("id") Integer id);



     /**
      * @Author: 田英杰
      * @Description: 根据机场名称筛选区域和机位冲突信息
      * @Date 2020/11/5 19:50
      * @Param  airportname
      * @return
      * @throws:
      **/
    @Select("SELECT * FROM `IOAREA_CONFLICT` WHERE airportname = #{airportname} and userId = #{userId}")
    List<IoareaConflict> getInfoByAN(@Param("airportname") String airportname, @Param("userId") String userId);


     /**
      * @Author: 田英杰
      * @Description: 查询滑入推出冲突表中所有数据
      * @Date 2020/11/6 14:12
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Select("SELECT * FROM `IOAREA_CONFLICT`")
    List<IoareaConflict> getAll();

     /**
      * @Author: 田英杰
      * @Description: 根据机场名称获取有几个区域
      * @Date 2020/11/6 16:48
      * @Param  * @param null 机场
      * @return
      * @throws:
      **/
    @Select("SELECT disdinct areaid FROM `IOAREA_CONFLICT` WHERE airportname = #{airportname}")
    List<IoareaConflict> getAreaid(@Param("airportname") String airportname);

     /**
      * @Author: 田英杰
      * @Description: 根据机场名称和区域编号获取区域内有几个停机位
      * @Date 2020/11/8 17:56
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Select("SELECT DISTINCT parkinggate FROM `IOAREA_CONFLICT` WHERE airportname = #{airportname} and areaid = #{areaid}")
    List<IoareaConflict> getParkinggate(@Param("airportname") String airportname, @Param("areaid") String areaid);


     /**
      * @Author: 田英杰
      * @Description: 插入新的机场区域机位限停约束
      * @Date 2020/11/8 18:16
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Insert({"insert into IOAREA_CONFLICT(areaid, parkinggate, airportname, userId, updateTime) " +
            "values(#{areaid},#{parkinggate},#{airportname},#{userId},#{updateTime})"})
    int insertinfo(@Param("areaid") String areaid,
                   @Param("parkinggate") String  parkinggate,
                   @Param("airportname") String airportname,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);


     /**
      * @Author: 田英杰
      * @Description: 根据数据id更改机场区域限停区域和机位号
      * @Date 2020/11/8 21:33
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Update({"update IOAREA_CONFLICT set areaid = #{areaid}, parkinggate = #{parkinggate}, userId = #{userId}, updateTime = #{updateTime} where id = #{id}"})
    int updateLimitInfo(@Param("id") Integer id,
                        @Param("areaid") String areaid,
                        @Param("parkinggate") String  parkinggate,
                        @Param("userId") String userId,
                        @Param("updateTime") String updateTime);


     /**
      * @Author: 田英杰
      * @Description: 批量删除机场区域限停机位信息
      * @Date 2020/12/2 15:32
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Delete({"DELETE FROM IOAREA_CONFLICT WHERE id = #{id}"})
    int batchDelete(@Param("id") Integer id);



}