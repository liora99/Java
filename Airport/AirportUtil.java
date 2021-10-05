import java.util.ArrayList;
import java.util.Arrays;

public class AirportUtil {
    public static void sortTransport(Comparable[] transport) {
        Arrays.sort(transport);
    }

    static String reportAll (Movable[] movables) {
        ArrayList<String> buffer = new ArrayList<>();
        for (Movable m: movables) {
            String str;
            if (m.getType() == "Plane")
            {
                str= "Plane " + m.getId() + " going from " + m.getSource() + " to " +m.getDestination() + ". Currently in " + m.getCurrentLocation();
                buffer.add(str);
            }
            else if (m.getType() == "Train")
            {
                str= "Train " + m.getId() + " going from " + m.getSource() + " to " +m.getDestination() + ". Currently in " + m.getCurrentLocation();
                buffer.add(str);
            }


        }
        return String.join("\n",buffer);
    }
}

