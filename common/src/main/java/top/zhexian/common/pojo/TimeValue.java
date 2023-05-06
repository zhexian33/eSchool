package top.zhexian.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeValue implements Serializable {
    private String id;
    private Integer totalValue;
    private Integer usefulValue;
    private Integer todayValue;
    private Integer yesterdayValue;
}
