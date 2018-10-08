package console;

import java.io.File;
import java.util.ArrayList;

import anyplace.Algorithms;
import anyplace.AnyUserData;
import anyplace.AnyUserData.FakeResults;
import anyplace.KalmanFilter;
import anyplace.LogRecord;
import anyplace.RadioMap;

public class main {
    public static void main(String[] args) {
        int algorithm_choice = 1;
        //TestServerAlgorithm(algorithm_choice);
        //TestAndroidAlgorithm(algorithm_choice);
        TestKalmanFilter(algorithm_choice);
        //TestKalmanFilter();
        //TestKNN();
        //TestWKNN();
        //TestMAP();
        //TestMMSE();
    }

    private static void TestKNN() {
        System.out.println("Start KNN Algorithm");

        String radioMapPath = "C:\\radiomap_simple.txt";
        File radioMapFile = new File(radioMapPath);
        RadioMap radioMap = null;
        try {
            radioMap = new RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScanSimple();
        latestScanList = r.records;

        String result = Algorithms.ProcessingAlgorithms(latestScanList, radioMap, 1);
        System.out.println(result);
        System.out.println("Finish");
    }

    private static void TestWKNN() {
        System.out.println("Start WKNN Algorithm");

        String radioMapPath = "C:\\radiomap_simple.txt";
        File radioMapFile = new File(radioMapPath);
        RadioMap radioMap = null;
        try {
            radioMap = new RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScanSimple();
        latestScanList = r.records;

        String result = Algorithms.ProcessingAlgorithms(latestScanList, radioMap, 2);
        System.out.println(result);
        System.out.println("Finish");
    }

    private static void TestMAP() {
        System.out.println("Start MAP Algorithm");

        String radioMapPath = "C:\\radiomap_simple.txt";
        File radioMapFile = new File(radioMapPath);
        RadioMap radioMap = null;
        try {
            radioMap = new RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScanSimple();
        latestScanList = r.records;

        String result = Algorithms.ProcessingAlgorithms(latestScanList, radioMap, 3);
        System.out.println(result);
        System.out.println("Finish");
    }

    private static void TestMMSE(){
        System.out.println("Start MMSE Algorithm");

        String radioMapPath = "C:\\radiomap_simple.txt";
        File radioMapFile = new File(radioMapPath);
        RadioMap radioMap = null;
        try {
            radioMap = new RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScanSimple();
        latestScanList = r.records;

        String result = Algorithms.ProcessingAlgorithms(latestScanList, radioMap, 4);
        System.out.println(result);
        System.out.println("Finish");
    }

    private static void TestKalmanFilter(int algorithm_choice) {
        System.out.println("Start Server Algorithm");

        String radioMapPath = "C:\\radiomap_simple.txt";
        File radioMapFile = new File(radioMapPath);
        RadioMap radioMap = null;
        try {
            radioMap = new RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScanSimple();
        latestScanList = r.records;

        String result = Algorithms.ProcessingAlgorithms(latestScanList, radioMap, algorithm_choice);
        System.out.println(result);
        System.out.println("Finish");

        String[] temp = result.split(" ");
        double lat = Double.parseDouble(temp[1]);
        double lng = Double.parseDouble(temp[0]);

        double newLat = 1.6;
        double newLng = 1.6;
        System.out.println("Random new location:");
        System.out.println(Double.toString(newLng) + " " + Double.toString(newLat));

        KalmanFilter kalmanFilter = new KalmanFilter(lat, lng);
        // kalmanFilter.reset(lat, lng);

        System.out.println("Kalman Filter: ");
        String pos = kalmanFilter.update(newLat, newLng);
        System.out.println(pos);

        newLat = 1.7;
        newLng = 1.7;
        System.out.println("Random new location:");
        System.out.println(Double.toString(newLng) + " " + Double.toString(newLat));

        System.out.println("Kalman Filter: ");
        pos = kalmanFilter.update(newLat, newLng);
        System.out.println(pos);
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
            KalmanFilter.Point p = new KalmanFilter.Point(0, 0);
            filter.update(wifi[0], wifi[1]);

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
        android.anyplace.RadioMap radioMap = null;
        try {
            radioMap = new android.anyplace.RadioMap(radioMapFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<LogRecord> latestScanList = new ArrayList<>();
        FakeResults r = AnyUserData.fakeScan75AyerRajah();
        latestScanList = r.records;

        String result = android.anyplace.Algorithms.ProcessingAlgorithms(latestScanList, radioMap, algorithm_choice);
        System.out.println(result);
        System.out.println("Finish");
    }
}