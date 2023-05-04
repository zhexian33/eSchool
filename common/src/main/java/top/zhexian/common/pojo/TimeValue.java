package top.zhexian.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeValue {
    private String id;
    private String userId;
    private Integer totalValue;
    private Integer usefulValue;
    private Integer todayValue;
    private Integer yesterdayValue;
}
