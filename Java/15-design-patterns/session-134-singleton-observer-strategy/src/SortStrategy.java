// SortStrategy.java — Strategy pattern برای sorting algorithms
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class SortStrategy {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(5, 2, 8, 1, 9, 3));

        Sorter sorter = new Sorter();

        // Bubble sort
        sorter.setStrategy(new BubbleSort());
        List<Integer> result1 = new ArrayList<>(numbers);
        sorter.sort(result1);
        System.out.println("Bubble: " + result1);

        // Quick sort (using built-in)
        sorter.setStrategy(new QuickSort());
        List<Integer> result2 = new ArrayList<>(numbers);
        sorter.sort(result2);
        System.out.println("Quick: " + result2);
    }

    interface SortAlgorithm {
        void sort(List<Integer> list);
    }

    static class BubbleSort implements SortAlgorithm {
        @Override public void sort(List<Integer> list) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 0; j < list.size() - i - 1; j++) {
                    if (list.get(j) > list.get(j + 1)) {
                        Collections.swap(list, j, j + 1);
                    }
                }
            }
        }
    }

    static class QuickSort implements SortAlgorithm {
        @Override public void sort(List<Integer> list) {
            list.sort(Integer::compareTo);
        }
    }

    static class Sorter {
        private SortAlgorithm strategy;

        public void setStrategy(SortAlgorithm strategy) {
            this.strategy = strategy;
        }

        public void sort(List<Integer> list) {
            if (strategy == null) {
                throw new IllegalStateException("No sort strategy set");
            }
            strategy.sort(list);
        }
    }
}
