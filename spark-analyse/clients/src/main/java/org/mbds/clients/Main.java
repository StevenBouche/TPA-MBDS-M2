package org.mbds.clients;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;
import org.mbds.clients.tasks.*;
import org.mbds.share.interfaces.IClientsSparkAction;

import java.util.*;

public class Main {

    private static final Map<String, IClientsSparkAction> mapActionClient = new HashMap<>();

    public static void main(String[] args) {

        mapActionClient.put("datalake", DatalakeClientsTask::task);
        mapActionClient.put("dba", DbaClientsTask::task);

        SparkSession spark = getSession();

        if(args.length > 0){
            IClientsSparkAction sparkactionclient = mapActionClient.get(args[0]);
            if(sparkactionclient != null){
                sparkactionclient.handle(spark, CommonClientsTask::task);
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
