package Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NameInfo {

    public static List<NameInfo> names = new ArrayList<>();
    public String ethcty;
    public String name;
    public String gender;
    public Integer count;
    public Integer rank;

    public NameInfo(String name, String gender, String ethcty, String count, String rank) {
        this.name = name;
        this.gender = gender;
        this.ethcty = ethcty;
        this.count = Integer.parseInt(count);
        this.rank = Integer.parseInt(rank);
    }

    public static void addInfo(NameInfo info) {
        names.add(info);
    }

    public static void check(NameInfo info) {
        for (int i = 0; i < names.size(); i++) {
            if (Objects.equals(names.get(i).name.toLowerCase(), info.name.toLowerCase())) {
                names.set(i, info);
                return;
            }
        }
        addInfo(info);
    }
}
