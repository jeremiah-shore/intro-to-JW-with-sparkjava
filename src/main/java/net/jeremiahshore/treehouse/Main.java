package net.jeremiahshore.treehouse;

import static  spark.Spark.get;

/**
 * Created by Jeremiah Shore at 3/4/2018 10:13 PM for course-ideas-rebuild.
 **/
public class Main {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World!");
    }
}
