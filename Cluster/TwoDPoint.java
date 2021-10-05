import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoDPoint implements Clusterable<TwoDPoint>{
	double x;
	double y;

	public TwoDPoint(String str){
		String  points[]= str.split(",");
		x= Double.parseDouble(points[0]);
		y=Double.parseDouble(points[1]);
	}

	public TwoDPoint(double _x, double _y) {
		x = _x;
		y = _y;

	}

	@Override
	public double distance(TwoDPoint other) {
		double _distance = Math.pow(Math.pow(other.x-this.x,2)+Math.pow(other.y-this.y,2),0.5);
		return _distance;
	}

	public static Set<TwoDPoint> readClusterableSet(String path) throws IOException{
		Stream<TwoDPoint> line= Files.lines(Paths.get(path)).map(TwoDPoint::new);
		return line.collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
	public boolean equals(Object other) {
		TwoDPoint otherP = (TwoDPoint) other;
		return (otherP.x == x && otherP.y == y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
