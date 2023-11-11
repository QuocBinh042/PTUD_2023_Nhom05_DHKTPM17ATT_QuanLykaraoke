package dao;

public class MaTuDong {
	public String formatMa(String s) {
		StringBuilder result = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				break;
			}
			result.append(c);
		}
		int so = Integer.parseInt(s.substring(result.length()));
		so++;
		String stringSo = String.format("%04d", so);
		result.append(stringSo);
		return result.toString();
	}
}
