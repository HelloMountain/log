package tqs.log.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tqs.log.base.ApiResponse;
import tqs.log.dao.AddrMapper;
import tqs.log.dao.BrowserMapper;
import tqs.log.dao.LogMapper;
import tqs.log.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/data")
public class DataController {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private BrowserMapper browserMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AddrMapper addrMapper;

    @GetMapping(value = "/code")
    public ApiResponse code(){
        List<CodeModel> code = logMapper.getCode();
        String[] array = new String[10];
        CodeModel codeModel = null;
        if (code != null && code.size() != 0){
            for (int i = 0; i < code.size(); i++){
                codeModel = code.get(i);
                array[i*2] = codeModel.getCode();
                array[i*2+1] = codeModel.getNum()+"";
            }
        }
        return new ApiResponse(200, array, "");
    }

    @GetMapping(value = "/topIp")
    public ApiResponse topIp(){
        String[] array = new String[10];
        TopIpModel topIpModel = null;
        List<TopIpModel> topIpModels = logMapper.getTopIp();
        if (topIpModels != null && topIpModels.size() != 0){
            for (int i = 0; i < topIpModels.size(); i++){
                topIpModel = topIpModels.get(i);
                array[i*2] = topIpModel.getClient();
                array[i*2+1] = topIpModel.getNum()+"";
            }
        }
        return new ApiResponse(200, array, "");
    }

    @GetMapping(value = "/map")
    public ApiResponse map(){

        List<MapModel> mapModels = addrMapper.getMap();
        HashMap<String, Integer> map = new HashMap<>();
        for (MapModel m : mapModels) {
            map.put(m.getAddr(), m.getNum());
        }
//        map.put("北京", 100);
//        map.put("上海", 200);
//        map.put("黑龙江", 300);
//        map.put("深圳", 400);
//        map.put("湖北", 500);
//        map.put("四川", 600);
        return new ApiResponse(200, map, "map");
    }

    @GetMapping(value = "/addr")
    public ApiResponse addr(@RequestParam(value = "ip")String ip){
        if (ip == null){
            return new ApiResponse(200, "ip为空", "addr");
        }
        String uri="http://freeapi.ipip.net/"+ip;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ArrayList<String>  strbody = restTemplate.exchange(uri, HttpMethod.GET, entity,ArrayList.class).getBody();
        System.out.println(strbody);
        return new ApiResponse(200, strbody, "addr");
    }

    @GetMapping(value = "/topUrl")
    public ApiResponse topUlr(){
        List<TopUrlModel> topUrlModels = logMapper.getTopUrl();
//        for (int i = 0; i < topUrlModels.size(); i++) {
//            String[] array = topUrlModels.get(i).getUrl().split("/?");
//            topUrlModels.get(i).setUrl(topUrlModels.get(i).getUrl().split("/?")[0]);
//            System.out.println(topUrlModels.get(i).getUrl());
//        }
        return new ApiResponse(200, topUrlModels, "topUrl");
    }

    @GetMapping(value = "/topResponse")
    public ApiResponse topResponse(){
        List<UrlTimeModel> topResponses = logMapper.getResponsetimeModel();
        return new ApiResponse(200, topResponses, "topResponse");
    }

    @GetMapping(value = "/errorUrl")
    public ApiResponse errorUrl(){
        List<ErrorUrlModel> errorUrlModels = logMapper.getErrorUlrModel();
        return new ApiResponse(200, errorUrlModels, "errorUrl");
    }

    @GetMapping(value = "/puv")
    public ApiResponse uv(@RequestParam(value = "date") String date){
        UVModel uvModel = logMapper.getUvModel(date);
        PVModel pvModel = logMapper.getPvModel(date);
        PUVModel puvModel = new PUVModel();
        if (uvModel != null){
            puvModel.setDay(uvModel.getDay());
            puvModel.setUvNum(uvModel.getNum());
        }
        if (pvModel != null){
            puvModel.setPvNum(pvModel.getNum());
        }
        return new ApiResponse(200, puvModel,"uv");
    }

    @GetMapping(value = "/pv")
    public ApiResponse pv(@RequestParam(value = "date") String date){
        PVModel pvModel = logMapper.getPvModel(date);
        return new ApiResponse(200, pvModel, "pv");
    }

    @GetMapping(value = "/browser")
    public ApiResponse browser(){
        List<BrowserModel> browserModels = browserMapper.getBrowser();
        return new ApiResponse(200, browserModels, "browser");
    }


}
