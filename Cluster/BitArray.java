import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BitArray implements Clusterable<BitArray>{
	private ArrayList<Boolean> bits;

	public BitArray(String str){
		bits = (ArrayList<Boolean>)
				Arrays.stream(str.split(","))
						.map(Boolean::parseBoolean)
						.collect(Collectors.toList());
	}
	public BitArray(boolean[] bits) {
		this(Arrays.toString(bits));
	}

	@Override
	public double distance(BitArray other) {
		int distance;
		distance = IntStream.range(0,bits.size())
				.map(d->(bits.get(d).equals(other.bits.get(d))? 0:1))
				.sum();

		return distance;
	}

	public static Set<BitArray> readClusterableSet(String path) throws IOException {
		Stream<BitArray> line = Files.lines(Paths.get(path))
				.filter(b -> b.split(",").length == 7)
				.map(BitArray::new);
		try {
			return line.collect(Collectors.toSet());
		} finally {
			line.close();
		}
	}

	@Override
	public String toString() {
		return bits.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BitArray bitArray = (BitArray) o;
		return bits.equals(bitArray.bits);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bits);
	}
}
