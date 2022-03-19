public class Plans implements Comparable<Plans> {
    private Integer importance;
    private Double time;
    private String place;

    public Plans(Integer importance, Double time, String place) {
        this.importance = importance;
        this.time = time;
        this.place = place;
    }

    public Integer getImportance() {
        return importance;
    }

    public Double getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public int compareTo(Plans another) {
        if (this.importance == another.importance)
            return 0;
        else if (this.importance < another.importance)
            return -1;
        else
            return 1;
    }

    @Override
    public String toString() {
        return "Plans{" +
                "importance=" + importance +
                ", time=" + time +
                ", place='" + place + '\'' +
                '}';
    }
}
