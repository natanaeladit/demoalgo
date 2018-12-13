package console;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import iplace.*;
import iplace.AnyUserData.FakeResults;

public class main {
    public static void main(String[] args) {
        int algorithm_choice = 4;
        // TestServerAlgorithm(algorithm_choice);
        // TestAndroidAlgorithm(algorithm_choice);
        // TestKalmanFilter(algorithm_choice);
        // TestKalmanFilter();

        // FIND
        // PostSensorDataToFind();

        // Filter Prediction
        // filteringLocationPrediction();
    }

    private static void filteringLocationPrediction(){
        double lat1 = 1.296744;
        double lon1 = 103.786171;

        double lat2 = 1.29675;
        double lon2 = 103.78612;

        float d = distance(lat1, lon1, lat2, lon2);
        //System.out.println(d);

        d = distance_b(lat1, lon1, lat2, lon2);
        //System.out.println(d);

        float deg = degree(lat1, lon1, lat2, lon2);
        System.out.println(deg);

        newLoc(lat1, lon1, 100, 360);
    }

    private static float distance(double lat_a, double lng_a, double lat_b, double lng_b) {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b - lat_a);
        double lngDiff = Math.toRadians(lng_b - lng_a);
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff / 2) * Math.sin(lngDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        int meterConversion = 1609;

        return new Float(distance * meterConversion).floatValue();
    }

    private static void newLoc(double lat1, double lon1, float d, float brng) {
        System.out.println(lat1 + ", " + lon1);

        double R = 6371e3;
        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(d / R) + Math.cos(lat1) * Math.sin(d / R) * Math.cos(brng));
        double lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(d / R) * Math.cos(lat1), Math.cos(d / R) - Math.sin(lat1) * Math.sin(lat2));

        System.out.println(lat2 + ", " + lon2);
    }

    private static float degree(double lat1, double lon1, double lat2, double lon2) {
        double y = Math.sin(lon2 - lon1) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) -
                Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1);
        double brng = Math.toDegrees((Math.atan2(y, x)));

        // convert to 0 - 360
        //double finalbrng = (brng+360) % 360;

        return new Float(brng).floatValue();
    }

    private static float distance_b(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371e3; // metres
        double a1 = Math.toRadians(lat1);
        double a2 = Math.toRadians(lat2);
        double da = Math.toRadians(lat2 - lat1);
        double db = Math.toRadians(lon2 - lon1);

        double a = Math.sin(da / 2) * Math.sin(da / 2) +
                Math.cos(a1) * Math.cos(a2) *
                        Math.sin(db / 2) * Math.sin(db / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return new Float(d).floatValue();
    }

    private static void PostSensorDataToFind() {

        String radioMapPath = "D:\\radiomap2610.txt";
        File radioMapFile = new File(radioMapPath);
        RadioMap radioMap = null;

        try {
            radioMap = new RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boolean isLocal = true;
        String url = "https://cloud.internalpositioning.com/data";
        if (isLocal)
            url = "http://localhost:8003/data";

        String jsonStart = "{  \n" +
                "   \"d\":\"device1\",\n" +
                "   \"f\":\"75ayer\",\n";
        String labelAfter = "   \"s\":{" +
                "      \"wifi\":{ ";
        String jsonMiddle = "}\n" +
                "   },\n" +
                "   \"gps\":{";
        String jsonFooter = " }\n" +
                "}";
        String jsonEmpty = "{}";

        try {
            for (String location : radioMap.getLocationRSS_HashMap().keySet()) {

                ArrayList<String> rss = radioMap.getLocationRSS_HashMap().get(location);
                ArrayList<String> mac = radioMap.getMacAdressList();

                String strRSS = "";
                for (int i = 0; i < mac.size(); i++) {
                    if (i == mac.size() - 1) { // last index
                        if (!rss.get(i).equalsIgnoreCase("-110"))
                            strRSS += "\"" + mac.get(i) + "\":" + rss.get(i);
                        else
                            strRSS = strRSS.substring(0, strRSS.length() - 1);
                    } else {
                        if (!rss.get(i).equalsIgnoreCase("-110"))
                            strRSS += "\"" + mac.get(i) + "\":" + rss.get(i) + ",";
                    }
                }

                String[] LocationArray = new String[2];
                LocationArray = location.split(" ");
                String lat = LocationArray[0].trim().substring(0, 7);
                String lon = LocationArray[1].trim().substring(0, 9);

                String label = "   \"l\":\"" + lat + "_" + lon + "\",\n";

                String xy = "\"lat\":" + lat + "," + "\"lon\":" + lon + "," + "\"alt\":10";

                String jsonReq = jsonStart + label + labelAfter + strRSS + jsonMiddle + xy + jsonFooter;

                String result = NetworkUtils.downloadHttpClientJsonPost(url, jsonReq, 2000);
                System.out.println(result);
                System.out.println(jsonReq);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void TestKalmanFilter(int algorithm_choice) {
        System.out.println("Start Server Algorithm");

        String radioMapPath = "D:\\radiomap.txt";
        File radioMapFile = new File(radioMapPath);
        RadioMap radioMap = null;
        try {
            radioMap = new RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScan75AyerRajah();
        latestScanList = r.records;

        String result = Algorithms.ProcessingAlgorithms(latestScanList, radioMap, algorithm_choice);
        System.out.println(result);
        System.out.println("Finish");
//
//		String[] temp = result.split(" ");
//		double lat = Double.parseDouble(temp[1]);
//		double lng = Double.parseDouble(temp[0]);

        double newLat = 103.78644921565798;
        double newLng = 1.2973656024776129;
//		System.out.println("Random new location:");
//		System.out.println(Double.toString(newLng) + " " + Double.toString(newLat));
//
//		KalmanFilter kalmanFilter = new KalmanFilter(lat, lng);
//		// kalmanFilter.reset(lat, lng);
//
//		System.out.println("Kalman Filter: ");
//		String pos = kalmanFilter.update(newLat, newLng);
//		System.out.println(pos);
    }

    private static void TestKalmanFilter() {
        double[][] wifis = new double[10][];

        wifis[0] = new double[]{-0.1752, 1.6722};
        wifis[1] = new double[]{0.2784, 3.2999};
        wifis[2] = new double[]{1.8177, 3.2585};
        wifis[3] = new double[]{3.4643, 3.1955};
        wifis[4] = new double[]{3.4026, 5.7257};
        wifis[5] = new double[]{5.9359, 5.6577};
        wifis[6] = new double[]{6.1601, 8.0462};
        wifis[7] = new double[]{8.0159, 7.5123};
        wifis[8] = new double[]{8.7819, 10.0279};
        wifis[9] = new double[]{9.6048, 8.2946};

        double[][] kfl = new double[10][];

        kfl[0] = new double[]{-0.1752, 1.6722};
        kfl[1] = new double[]{0.2706, 3.2720};
        kfl[2] = new double[]{1.6280, 3.5296};
        kfl[3] = new double[]{3.2086, 3.5331};
        kfl[4] = new double[]{3.8219, 5.0287};
        kfl[5] = new double[]{5.4215, 5.7390};
        kfl[6] = new double[]{6.3998, 7.2287};
        kfl[7] = new double[]{7.7391, 7.8945};
        kfl[8] = new double[]{8.8665, 9.2587};
        kfl[9] = new double[]{9.8840, 9.5473};

        KalmanFilter filter = new KalmanFilter(wifis[0][0], wifis[0][1]);

        for (int i = 1; i < wifis.length; i++) {
            double[] wifi = wifis[i];
            KalmanFilter.Point p = new KalmanFilter.Point(0, 0); // = filter.update(wifi[0], wifi[1]);

            System.out.println(String.format("V%d: %4.3f. %4.3f", i, p.x, p.y));
            System.out.println(String.format("V%d: %4.3f. %4.3f", i, kfl[i][0], kfl[i][1]));
            if (Math.abs(p.x - kfl[i][0]) > 0.001 || Math.abs(p.y - kfl[i][1]) > 0.001) {
                System.out.println(String.format("Error"));
            }
        }

        System.out.println("Done");
    }

    private static void TestServerAlgorithm(int algorithm_choice) {
        System.out.println("Start Server Algorithm");

        String radioMapPath = "C:\\radiomap.txt";
        File radioMapFile = new File(radioMapPath);
        RadioMap radioMap = null;
        try {
            radioMap = new RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScan75AyerRajah();
        latestScanList = r.records;

        String result = Algorithms.ProcessingAlgorithms(latestScanList, radioMap, algorithm_choice);
        System.out.println(result);
        System.out.println("Finish");
    }

    private static void TestAndroidAlgorithm(int algorithm_choice) {
        System.out.println("Start Android Algorithm");

        String radioMapPath = "C:\\radiomap.txt";
        File radioMapFile = new File(radioMapPath);
        android.iplace.RadioMap radioMap = null;
        try {
            radioMap = new android.iplace.RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScan75AyerRajah();
        latestScanList = r.records;

        String result = android.iplace.Algorithms.ProcessingAlgorithms(latestScanList, radioMap, algorithm_choice);
        System.out.println(result);
        System.out.println("Finish");
    }
}