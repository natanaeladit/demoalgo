package console;

import java.io.File;
import java.util.ArrayList;

import anyplace.Algorithms;
import anyplace.AnyUserData;
import anyplace.AnyUserData.FakeResults;
import anyplace.LogRecord;
import anyplace.RadioMap;
import android.anyplace.*;

public class main {
	public static void main(String[] args) {
		int algorithm_choice = 1;
		TestServerAlgorithm(algorithm_choice);
		TestAndroidAlgorithm(algorithm_choice);
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