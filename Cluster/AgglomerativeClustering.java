import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// ----------------- Cluster algomerative Algorithme -------------------------



public class AgglomerativeClustering <T extends Clusterable<T>> implements Clustering<T>{

	double threshold;

	/** class Element to Help me */

	public class Element<Element_1, Element_2>
	{
		public final Element_1 elem1;
		public final Element_2 elem2;

		public Element(Element_1 elem1, Element_2 elem2)
		{
			this.elem1 = elem1;
			this.elem2 = elem2;
		}
	}

	/** use this fonction to add set2 to set1 */
	private void Add(Set<T> Set_1, Set<T> Set_2) {
		Set_1.addAll(Set_2);
	}


	public AgglomerativeClustering(double threshold) {
		this.threshold = threshold;
	}
	public Set<Set<T>> clusterSet(Set<T> elements) {

		/** Add all the element to the Set */
		Set<Set<T>> sets = elements.stream()
				.map(elm->{Set<T> set= new HashSet<T>();set.add(elm);return set;})
				.collect(Collectors.toSet());

		/** while the set is not empty */
		while (sets.size()!=1){
			Element<Set<T>, Element<Set<T>, Double>> nearest =
					sets.stream().
							map(s -> new Element<Set<T>, Element<Set<T>, Double>>
									(
											s,
											sets.stream()
													.filter(s1 -> s1 != s)
													.map(s1 -> new Element<Set<T>, Double>(s1, myDistance(s, s1)))
													.min(Comparator.comparing(element -> element.elem2))
													.get()

									)
							)
							.min(Comparator.comparing(pair -> pair.elem2.elem2))
							.get();

			if (nearest.elem2.elem2 > threshold)
				return sets;

			Set<T> c1 = nearest.elem1, c2 = nearest.elem2.elem1;
			sets.remove(c2);
			sets.remove(c1);
			Add(c1,c2);
			sets.add(c1);

		}
		return sets;
	}

	private Double myDistance(Set<T> s, Set<T> s1) {
		return s.stream()
				.flatMap(d1 -> s1.stream().map(d2 -> d1.distance(d2)))
				.mapToDouble(d1 -> d1)
				.min()
				.getAsDouble();
	}


}