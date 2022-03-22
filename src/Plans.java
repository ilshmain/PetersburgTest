public class Plans implements Comparable<Plans> {
    private Double importance;
    private Double time;
    private String place;

    public Plans(Double importance, Double time, String place) {
        this.importance = importance;
        this.time = time;
        this.place = place;
    }

    public double valueOneWeight() {
        return importance/time;
    }

    public Double getImportance() {
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
        int res = this.time.compareTo(another.time);
        if (res == 0)
            res = this.importance.compareTo(another.importance);
        return res;
    }

    @Override
    public String toString() {
        return "Plans{" +
                "Time=" + time +
                ", importance=" + importance +
//                ", place='" + place + '\'' +
                ", %= " + importance/time+
                
                '}';
    }
}
