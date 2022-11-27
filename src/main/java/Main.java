import java.util.concurrent.ForkJoinPool;

public class Main {
  public static void main(String[] args) {
    int arraySize = 1_000_000;
    int[] array = new int[arraySize];
    fillArray(array, arraySize);
    long startTime = System.currentTimeMillis();
    System.out.println(singleThreadArraySummary(array));
    long endTime = System.currentTimeMillis();
    long time = endTime - startTime;
    System.out.println("Расчет занял(мс): " + time);

    MultiThreadSummary counter = new MultiThreadSummary(array);
    startTime = System.currentTimeMillis();
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    System.out.println(forkJoinPool.invoke(counter));
    endTime = System.currentTimeMillis();
    time = endTime - startTime;
    System.out.println("Расчет занял(мс): " + time);
  }

  public static void fillArray(int[] array, int arraySize) {
    for (int i = 0; i < arraySize; i++) {
      array[i] = (int) (Math.random() * 100);
    }
  }

  public static long singleThreadArraySummary(int[] array) {
    long result = 0;
    for (int i : array) {
      result = result + i;
    }
    return result;
  }
}
