package console;

import java.io.File;
import java.util.ArrayList;

import anyplace.Algorithms;
import anyplace.AnyUserData;
import anyplace.AnyUserData.FakeResults;
import anyplace.LogRecord;
import anyplace.RadioMap;

public class main {
	public static void main(String[] args) {
		System.out.println("Start");

		String radioMapPath = "C:\\radiomap.txt";
		File radioMapFile = new File(radioMapPath);
		RadioMap radioMap = null;
		try {
			radioMap = new RadioMap(radioMapFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int algorithm_choice = 1;

		ArrayList<LogRecord> latestScanList = new ArrayList<>();
		FakeResults r = AnyUserData.fakeScan();
		latestScanList = r.records;

		String result = Algorithms.ProcessingAlgorithms(latestScanList, radioMap, algorithm_choice);
		System.out.println(result);
		System.out.println("Finish");
	}
}