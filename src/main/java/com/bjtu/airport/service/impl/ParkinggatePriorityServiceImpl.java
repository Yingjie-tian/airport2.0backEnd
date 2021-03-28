package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.ParkinggatePriority;
import com.bjtu.airport.mapper.ParkinggatePriorityMapper;
import com.bjtu.airport.service.IParkinggatePriorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ChenYakun
 * @since 2020-11-07
 */
@Service
public class ParkinggatePriorityServiceImpl extends ServiceImpl<ParkinggatePriorityMapper, ParkinggatePriority> implements IParkinggatePriorityService {
    @Autowired
    private ParkinggatePriorityMapper parkinggatePriorityMapper;

    /* **
     * @Author: chenyakun
     * @Description: 根据用户名和机场名称请求航班类型
     * @Date 2020/11/9 14:17
     * @Param airportname
     * @param userId
     * @return java.util.List<net.sf.json.JSONObject>
     * @throws:
     **/
    @Override
    public List<JSONObject> selectType(String airportname, String userId) {
        List<JSONObject> info = parkinggatePriorityMapper.selectType(airportname, userId);
        return info;
    }

    /* **
    * @Author: chenyakun
    * @Description: 根据用户名和机场名请求数据
    * @Date 2020/11/9 14:17
    * @Param airportname
    * @param userId
    * @return java.util.List<net.sf.json.JSONObject>
    * @throws:
    **/
    @Override
    public List<JSONObject> select22(String airportname, String userId) {
        List<JSONObject> getinfo = parkinggatePriorityMapper.select22(airportname, userId);
        return getinfo;
    }


    /* **
     * @Author: chenyakun
     * @Description: 根据用户名、机场名称、航班类型查询数据
     * @Date 2020/11/9 14:11
     * @Param null
     * @return
     * @throws:
     **/
    @Override
    public List<JSONObject> selectByAirportnameAndUserIdAndType(String airportname, String flighttype, String userId) {
        List<JSONObject> info = parkinggatePriorityMapper.selectByAirportnameAndUserIdAndType(airportname, flighttype, userId);

        //封装数据
        //获取哪个模板
        int temp = info.get(0).getInt("template");
        //根据groupid计算有几条数据
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < info.size(); i++) {
            set.add(info.get(i).getInt("groupid"));
        }
        ;
        int row = set.size();
        //创建一个列表编数据
        List<JSONObject> res = new ArrayList<JSONObject>();
        for (int i = 0; i < set.size(); i++) {
            Map map = new HashMap();
            if (temp == 0) {
//                System.out.println("不划分，每两条后端对应一条前端");
                map.put("id", 2 * i);
                map.put("airportname", info.get(2 * i).getString("airportname"));
                map.put("groupid", info.get(2 * i).getInt("groupid"));
                map.put("flighttype", info.get(2 * i).getString("flighttype"));
                map.put("flightkey", info.get(2 * i).getString("flightkey"));
                map.put("template", info.get(2 * i).getInt("template"));
                map.put("parkinggate", info.get(2 * i).getInt("businessvalue"));
                map.put("priority", info.get(2 * i + 1).getInt("businessvalue"));
                JSONObject json = JSONObject.fromObject(map);
                res.add(json);
            } else if (temp == 1) {
//                System.out.println("据停靠时间划分");
                map.put("id", 4 * i);
                map.put("airportname", info.get(4 * i).getString("airportname"));
                map.put("groupid", info.get(4 * i).getInt("groupid"));
                map.put("flighttype", info.get(4 * i).getString("flighttype"));
                map.put("flightkey", info.get(4 * i).getString("flightkey"));
                map.put("template", info.get(4 * i).getInt("template"));
                map.put("parkinggate", info.get(4 * i).getInt("businessvalue"));
                map.put("priority", info.get(4 * i + 1).getInt("businessvalue"));
                map.put("stoptimeless", info.get(4 * i + 2).getInt("businessvalue"));
                map.put("stoptimemore", info.get(4 * i + 3).getInt("businessvalue"));
                JSONObject json = JSONObject.fromObject(map);
                res.add(json);
            } else if (temp == 2) {
//                System.out.println("根据进出港时间划分");
            map.put("id", 6 * i);
            map.put("airportname", info.get(6 * i).getString("airportname"));
            map.put("groupid", info.get(6 * i).getInt("groupid"));
            map.put("flighttype", info.get(6 * i).getString("flighttype"));
            map.put("flightkey", info.get(6 * i).getString("flightkey"));
            map.put("template", info.get(6 * i).getInt("template"));
            map.put("parkinggate", info.get(6 * i).getInt("businessvalue"));
            map.put("priority", info.get(6 * i + 1).getInt("businessvalue"));
            map.put("atimeless", info.get(6 * i + 2).getInt("businessvalue"));
            map.put("atimemore", info.get(6 * i + 3).getInt("businessvalue"));
            map.put("dtimeless", info.get(6 * i + 4).getInt("businessvalue"));
            map.put("dtimemore", info.get(6 * i + 5).getInt("businessvalue"));
            JSONObject json = JSONObject.fromObject(map);
            res.add(json);
            }
        }
        //返回数据
        return res;
    }

    /* **
     * @Author: chenyakun
     * @Description: 根据机场名称查询该机场有哪些机位号
     * @Date 2020/11/10 10:20
     * @Param null
     * @return
     * @throws:
     **/
    @Override
    public List<JSONObject> selectParkinggate(String airportname) {
        List<JSONObject> info = parkinggatePriorityMapper.selectParkinggate(airportname);
        //创建一个列表编数据
        List<JSONObject> res = new ArrayList<JSONObject>();
        for (int i = 0; i < info.size(); i++) {
            Map map = new HashMap();
            map.put("id", i+1);
            map.put("parkinggate", info.get(i).getInt("parkinggate"));
            JSONObject json = JSONObject.fromObject(map);
            res.add(json);
        }
//        System.out.println(res);
        return res;
    }

    /* **
     * @Author: chenyakun
     * @Description: 根据groupid、航班类型、机场名称、用户名删除数据
     * @Date 2020/11/10 19:30
     * @Param null
     * @return
     * @throws:
     **/
    @Override
    public int deleteParkinggate(String airportname,Integer groupid, String flighttype,String userId) {
        int info = parkinggatePriorityMapper.deleteParkinggate(airportname,groupid, flighttype,userId);
        return info;
    }

}
