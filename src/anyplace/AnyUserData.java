package anyplace;

import java.util.ArrayList;

public class AnyUserData {
	public static class FakeResults {
		public ArrayList<LogRecord> records;
		public int heading;
	}

	public static FakeResults fakeScan() {
		return fakeFST01FL2();
	}

	private static FakeResults fakeFST01FL2() {
		FakeResults r = new FakeResults();
		ArrayList<LogRecord> latestScanList = new ArrayList<LogRecord>();

		latestScanList.add(new LogRecord("64:70:02:b5:0c:a2", -66));
		latestScanList.add(new LogRecord("18:64:72:20:ce:61", -87));
		
		r.records = latestScanList;
		r.heading = 270;
		return r;
	}
}
