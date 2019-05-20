package tqs.log.service.impl;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;
import tqs.log.dao.AddrMapper;
import tqs.log.dao.BrowserMapper;
import tqs.log.dao.LogMapper;
import tqs.log.entity.Addr;
import tqs.log.entity.Browser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LogServiceImplTest extends LogApplicationTests {

    @Autowired
    private LogServiceImpl logService;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private BrowserMapper browserMapper;

    @Autowired
    private AddrMapper addrMapper;

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
    public void timeTest(){
                String iso = "2019-05-02 20:55:45";//2019-05-02T20:55:46+08:00
        DateFormat sdf1 = new SimpleDateFormat("[yyyy-mm-dd'T'HH:mm:ss.SSS'Z']");
        try {
            Date dt =  sdf1.parse(iso);
            System.out.println(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pvTest(){
//        List<PVModel> result = logMapper.getPvModel();
//        result.forEach(pvModel -> System.out.println(pvModel));
    }

    //提取nginx日志ua字段中关于浏览器类型的字段
    @Test
    public void regex(){
        String string = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36";

        String[] split = string.split(" ");
        System.out.println(split[split.length - 1].split("/")[0]);

        String browser = split[split.length - 1].split("/")[0];
        if ("Safari".equals(browser)){
            System.out.println("Chrome");
        }
        Browser b = new Browser();
        b.setBrowser(browser);
        browserMapper.insert(b);
//        System.out.println(split[split.length - 1]);
//        String regex = "";
        //编译正则表达式
//        Pattern pattern = Pattern.compile(regex);
    }

    @Test
    public void string(){
        String newTime = "2019-05-02 20:55:45";
        String temp = newTime.replace(" ", "T") + "+08:00";
        System.out.println(temp);
    }

    @Test
    public void map(){
        String[] array = {"新疆","西藏","甘肃", "云南", "内蒙古", "黑龙江", "吉林", "辽宁",
                "青海", "宁夏", "陕西", "山西", "湖南", "湖北", "贵州", "重庆", "广西",
                "广东", "香港", "澳门", "海南", "台湾", "福建", "浙江", "上海", "安徽",
                "江苏", "河南", "山东", "河北", "天津", "北京","云南","四川", "江西"};//35
        Random random = new Random();
        Addr addr = new Addr();
        int num = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            addr.setAddr(array[random.nextInt(32)]);
            int num2 = random.nextInt(1)*100 + (random.nextInt(7)*10+3) + random.nextInt(10);
            for (int j = 0; j < num2; j++) {
                addr.setLogId(++num);
                addr.setIp(num + "");
                addrMapper.insert(addr);
            }
        }
//        for (int i = 0; i < 1000; i++) {
//            addr.setLogId(i);
//            addr.setIp(i+"");
//            addr.setAddr(array[random.nextInt(32)]);
//            addrMapper.insert(addr);
//        }
    }
}
