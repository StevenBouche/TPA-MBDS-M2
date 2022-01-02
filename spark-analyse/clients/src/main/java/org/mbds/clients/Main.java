package org.mbds.clients;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;
import org.mbds.clients.interfaces.ISparkAction;
import org.mbds.clients.tasks.CommonTask;
import org.mbds.clients.tasks.DatalakeTask;
import org.mbds.clients.tasks.DbaTask;
import java.util.*;

public class Main {

    private static final Map<String, ISparkAction> mapAction = new HashMap<>();

    public static void main(String[] args) {

        mapAction.put("datalake", DatalakeTask::task);
        mapAction.put("dba", DbaTask::task);

        SparkSession spark = getSession();

        if(args.length > 0){
            ISparkAction sparkaction = mapAction.get(args[0]);
            if(sparkaction != null){
                sparkaction.handle(spark, CommonTask::task);
            }
        }

        spark.stop();
    }

    private static SparkSession getSession(){
        SparkConf configuration = new SparkConf()
                .setAppName("Cars-job")
                .setMaster("spark://spark:7077")
                .set("spark.submit.deployMode", "client")
                .set("spark.ui.showConsoleProgress", "true")
                .set("spark.eventLog.enabled", "false")
                .set("spark.logConf", "false")
                .set("spark.driver.bindAddress", "0.0.0.0")
                .set("spark.driver.host", "spark");

        return SparkSession.builder().config(configuration).getOrCreate();
    }
}
