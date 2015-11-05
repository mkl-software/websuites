package com.mkl.websuites.internal.command.impl.flow.repeat;

import java.util.List;
import java.util.Map;

public interface RepeatDataProvider {


    /**
     * Each entry in the list represents a map [parameter_name=value]
     * 
     * TODO: usually it will bring data overhead, because parameter names will be the same across
     * all data rows. Think how to represent this more efficiently in the returning type.
     * 
     * @return
     */
    List<Map<String, String>> provideData();
}
