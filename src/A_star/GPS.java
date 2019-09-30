package A_star;

public class GPS {
    GraphLinked graph;
    GraphLocation graphLocation;
    GraphLogLat graphLogLat;

    String startLog = "";

    public GPS(String filenameGraph, String filenameLocations, String filenameLogLat) {
        long startTime = System.currentTimeMillis();
        graph = GraphFileReader.readGraphFile(filenameGraph);
        startLog += "Loaded graph file '" + filenameGraph + "' in " + (System.currentTimeMillis()-startTime) + "ms.\n";

        long startTime2 = System.currentTimeMillis();
        graphLocation = GraphFileReader.readGraphLocationFile(filenameLocations);
        startLog += "Loaded locations file '" + filenameLocations + "' in " + (System.currentTimeMillis()-startTime2) + "ms.\n";

        long startTime3 = System.currentTimeMillis();
        graphLogLat = GraphFileReader.readGraphLogLatFile(filenameLogLat);
        startLog += "Loaded locations file '" + filenameLogLat + "' in " + (System.currentTimeMillis()-startTime3) + "ms.\n";
    }

    public String navigateA(String from, String to) {
        String string = "";
        long startTime = System.currentTimeMillis();
        int[][] nodes = graph.aStar(graphLocation.getNode(from), graphLocation.getNode(to), graphLogLat);
        string += "Det er " + (nodes[0].length-1) + " noder mellom " + from + " og " + to + ".\n";
        string += "Tid: " + Math.floor(nodes[1][0] / 6000.0) + " min (" + Math.floor(nodes[1][0] / 36000.0) / 10 + " timer)\n";
        string += "Utregningstid " + (System.currentTimeMillis()-startTime) + " ms.";

        string += "Ruten er: \n";
        for (int i = 0;i < nodes[0].length;i++) {
            String name = graphLocation.getName(nodes[0][i]);
            if (name != null) string += name + "\n";
        }

        makeMap(nodes[0]);

        return string;
    }

    public String navigateD(String from, String to) {
        String string = "";
        long startTime = System.currentTimeMillis();
        int[][] nodes = graph.dijkstra(graphLocation.getNode(from), graphLocation.getNode(to));
        string += "Der er " + (nodes[0].length-1) + " node mellom " + from + " og " + to + ".\n";
        string += "tid: " + Math.floor(nodes[1][0] / 6000.0) + " min (" + Math.floor(nodes[1][0] / 36000.0) / 10 + " hours)\n";
        string += "Utregningstid " + (System.currentTimeMillis()-startTime) + " ms.";

        string += "Ruten er: \n";
        for (int i = 0;i < nodes[0].length;i++) {
            String name = graphLocation.getName(nodes[0][i]);
            if (name != null) string += name + "\n";
        }

        makeMap(nodes[0]);

        return string;
    }

    public void makeMap(int[] nodes) {
        double[][] points = new double[nodes.length][];
        for (int i = 0; i < points.length; i++) {
            points[i] = graphLogLat.getLogLat(nodes[i]);
            System.out.println(points[i][0] + ", " + points[i][1]);
        }

        //Window window = new Window(1600, 900);
        //window.updatePoints(points);

    }

    public String getStartLog() {
        return startLog;
    }
}
