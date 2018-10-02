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

	public static FakeResults fakeScan75AyerRajah() {
		return fake75AyerRajah();
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

	private static FakeResults fake75AyerRajah() {
		FakeResults r = new FakeResults();
		ArrayList<LogRecord> latestScanList = new ArrayList<LogRecord>();

		latestScanList.add(new LogRecord("a8:bd:27:a7:24:33", -58));
		latestScanList.add(new LogRecord("a8:bd:27:a7:24:32", -58));
		latestScanList.add(new LogRecord("a8:bd:27:a7:24:31", -58));
		latestScanList.add(new LogRecord("88:5b:dd:a8:5c:64", -110));
		latestScanList.add(new LogRecord("f0:5c:19:50:cd:92", -87));
		latestScanList.add(new LogRecord("f0:5c:19:50:cd:91", -87));
		latestScanList.add(new LogRecord("f0:5c:19:50:cd:93", -88));
		latestScanList.add(new LogRecord("94:b4:0f:c7:9d:92", -72));
		latestScanList.add(new LogRecord("94:b4:0f:c7:9d:91", -71));
		latestScanList.add(new LogRecord("94:b4:0f:c7:9d:93", -71));

		latestScanList.add(new LogRecord("f0:5c:19:4f:85:13", -110));
		latestScanList.add(new LogRecord("f0:5c:19:4f:85:11", -110));
		latestScanList.add(new LogRecord("f0:79:59:77:02:38", -65));
		latestScanList.add(new LogRecord("2c:56:dc:82:e4:20", -110));
		latestScanList.add(new LogRecord("2c:56:dc:82:e4:24", -110));
		latestScanList.add(new LogRecord("60:45:cb:8f:7b:ac", -110));
		latestScanList.add(new LogRecord("00:1a:8c:0a:ca:01", -110));
		latestScanList.add(new LogRecord("a8:bd:27:a7:23:73", -73));
		latestScanList.add(new LogRecord("26:18:1d:28:28:c3", -110));
		latestScanList.add(new LogRecord("94:b4:0f:c7:9d:81", -110));

		latestScanList.add(new LogRecord("a8:bd:27:a7:20:a2", -68));
		latestScanList.add(new LogRecord("a8:bd:27:a7:20:a1", -68));
		latestScanList.add(new LogRecord("94:b4:0f:c7:9d:83", -110));
		latestScanList.add(new LogRecord("a8:bd:27:a7:20:a3", -68));
		latestScanList.add(new LogRecord("94:b4:0f:c7:9d:82", -110));
		latestScanList.add(new LogRecord("88:d7:f6:82:38:5c", -110));
		latestScanList.add(new LogRecord("82:2a:a8:a1:99:04", -110));
		latestScanList.add(new LogRecord("f0:79:59:77:02:3c", -67));
		latestScanList.add(new LogRecord("a8:bd:27:a7:20:b3", -71));
		latestScanList.add(new LogRecord("a8:bd:27:a7:20:b2", -71));

		latestScanList.add(new LogRecord("64:d1:54:4f:34:51", -110));
		latestScanList.add(new LogRecord("a8:bd:27:a7:20:b1", -71));
		latestScanList.add(new LogRecord("18:60:24:37:60:ab", -110));
		latestScanList.add(new LogRecord("e0:8e:3c:35:97:d5", -110));
		latestScanList.add(new LogRecord("e0:8e:3c:35:97:d4", -110));
		latestScanList.add(new LogRecord("a8:bd:27:a6:ff:b1", -110));
		latestScanList.add(new LogRecord("a8:bd:27:a6:ff:b2", -110));
		latestScanList.add(new LogRecord("a8:bd:27:a6:ff:b3", -110));
		latestScanList.add(new LogRecord("a8:bd:27:a7:24:23", -58));
		latestScanList.add(new LogRecord("a8:bd:27:a7:24:22", -110));

		latestScanList.add(new LogRecord("a8:bd:27:a7:24:21", -59));
		latestScanList.add(new LogRecord("18:d6:c7:d8:52:a4", -59));
		latestScanList.add(new LogRecord("4c:ed:fb:b1:37:b0", -110));
		latestScanList.add(new LogRecord("18:d6:c7:d8:52:a3", -73));
		latestScanList.add(new LogRecord("18:d6:c7:d8:52:a2", -110));
		latestScanList.add(new LogRecord("f4:f2:6d:94:78:c2", -110));
		latestScanList.add(new LogRecord("4c:ed:fb:b1:37:b4", -110));
		latestScanList.add(new LogRecord("18:d6:c7:d8:54:af", -60));
		latestScanList.add(new LogRecord("88:d7:f6:bd:ca:c4", -110));
		latestScanList.add(new LogRecord("a8:bd:27:a7:23:f3", -110));

		latestScanList.add(new LogRecord("a0:04:60:69:67:de", -87));
		latestScanList.add(new LogRecord("8a:15:04:bc:02:af", -110));
		latestScanList.add(new LogRecord("7c:8b:ca:39:02:b2", -110));
		latestScanList.add(new LogRecord("d2:6e:0e:af:d1:f7", -110));
		latestScanList.add(new LogRecord("e0:8e:3c:36:fc:89", -84));
		latestScanList.add(new LogRecord("00:1a:8c:82:4c:37", -89));
		latestScanList.add(new LogRecord("94:b4:0f:c7:2b:71", -110));
		latestScanList.add(new LogRecord("94:b4:0f:c7:2b:73", -110));
		latestScanList.add(new LogRecord("94:b4:0f:c7:2b:72", -86));

		latestScanList.add(new LogRecord("00:1a:8c:82:4c:38", -90));
		latestScanList.add(new LogRecord("94:b4:0f:c7:aa:03", -110));
		latestScanList.add(new LogRecord("94:b4:0f:c7:11:a3", -81));
		latestScanList.add(new LogRecord("fe:ec:da:8b:fd:8c", -90));
		latestScanList.add(new LogRecord("6c:72:20:11:b4:1a", -87));
		latestScanList.add(new LogRecord("f0:5c:19:50:ce:53", -87));

		r.records = latestScanList;
		r.heading = 270;
		return r;
	}
}
