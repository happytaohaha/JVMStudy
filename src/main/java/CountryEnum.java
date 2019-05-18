import lombok.Getter;

/**
 * @Classname CountryEnum
 * @Author 严涛
 * @Date 2019/5/17
 * @Description 灭国统一华夏问题的 枚举类
 *    2.也可以使用数组
 *
 *    枚举相当于数据库
 *    mysql dbName = CountryEnum
 *
 *    总结出以后的枚举 template
 */

public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");
    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for(CountryEnum element : myArray){
            if(index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
