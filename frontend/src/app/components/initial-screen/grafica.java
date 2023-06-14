public class grafica {

    private Array<String> labels = new ArrayList<>();

    public Array<String> generateLabels(List<Movie> movies) {
        for(Movie mv: movies){
            this.labels.add(mv);
        }
        return this.labels;
    }
}
