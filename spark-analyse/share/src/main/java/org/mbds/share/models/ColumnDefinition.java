package org.mbds.share.models;

import org.apache.spark.sql.types.DataType;

public class ColumnDefinition {
    public String sourceName;
    public String finalName;
    public DataType type;

    public ColumnDefinition(String sourceName, String finalName, DataType type){
        this.sourceName = sourceName;
        this.finalName = finalName;
        this.type = type;
    }
}
