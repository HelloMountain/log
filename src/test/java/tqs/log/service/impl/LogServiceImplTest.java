package tqs.log.service.impl;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;
import tqs.log.dao.LogMapper;
import tqs.log.model.PVModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class LogServiceImplTest extends LogApplicationTests {

    @Autowired
    private LogServiceImpl logService;

    @Autowired
    private LogMapper logMapper;

    @Test
    public void code() {
        logService.code();
    }

    @Test
    public void time()throws Exception{
//        String iso = "2019-04-03T22:40:59+08:00";
//        String d = "2018-05-14T03:51:50.153Z";
//        DateFormat sdf1 = new SimpleDateFormat("[yyyy-mm-dd'T'HH:mm:ss.SSS'Z']");
//        sdf1.setTimeZone(TimeZone.getTimeZone("UTC")); //获取时区
//        Date dt =  sdf1.parse(d);
//        Long eventTime = 0L;
////将ISO 8601 日期格式进行转换
//        DateTimeFormatter isoFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
//        DateTimeFormatter normalFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
//获取long类型的时间
//        System.out.println(isoFormat.parseDateTime(iso));
//        eventTime = dt.getMillis();
//        System.out.println(eventTime);
//        System.out.println(dt.toString(format));

//        DateTime time = DateTime.parse(iso, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        DateTime time = new DateTime("2019-04-03T22:40:59+08:00");
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");//"yyyy-MM-dd HH:mm:ss"
        String temp = dateFormat.format(time.toDate());
        System.out.println(temp);
    }

    @Test
    public void pvTest(){
        List<PVModel> result = logMapper.getPvModel();
        result.forEach(pvModel -> System.out.println(pvModel));
    }
}
