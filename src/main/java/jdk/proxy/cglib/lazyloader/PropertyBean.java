package jdk.proxy.cglib.lazyloader;

import lombok.Data;
import lombok.ToString;

/**
 * @author nibnait
 * @version $Id: PropertyBean.java, v 0.1 2019-09-02 下午4:35 nibnait Exp $$
 */
@Data
@ToString
public class PropertyBean {
    private String key;
    private Object value;

}