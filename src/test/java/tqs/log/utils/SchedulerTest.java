package tqs.log.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;

import static org.junit.Assert.*;

public class SchedulerTest extends LogApplicationTests {

    @Autowired
    private Scheduler scheduler;

    @Test
    public void esToSqlTask() {
        scheduler.esToSqlTask();
    }

    @Test
    public void addBrowser() {
    }

    @Test
    public void addAddr() {
        scheduler.getAddr("118.28.8.8");
    }
}
